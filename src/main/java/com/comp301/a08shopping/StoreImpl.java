package com.comp301.a08shopping;

import com.comp301.a08shopping.events.*;import com.comp301.a08shopping.exceptions.OutOfStockException;import com.comp301.a08shopping.exceptions.ProductNotFoundException;import java.util.ArrayList;import java.util.List;
public class StoreImpl implements Store{
    private String name;
    private List<StoreObserver> observers;
    private List<Product> products;
    public StoreImpl(String name) {
        if (name == null) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        observers = new ArrayList<>();
        products = new ArrayList<>();

    }
    @Override
    public String getName() {
        return name;
    }

    @Override public void addObserver(StoreObserver observer) {
        observers.add(observer);
    }

    @Override public void removeObserver(StoreObserver observer) {
        observers.remove(observer);
    }

    @Override public List<Product> getProducts() {
        return (List<Product>) ((ArrayList<Product>) products).clone();
    }
    @Override public Product createProduct(String name, double basePrice, int inventory) {
        if (inventory < 0) {
            throw new IllegalArgumentException("inventory must be positive");
        }
        Product newProd = new ProductImpl(name, basePrice);
        ((ProductImpl)newProd).setInventory(inventory);
        return newProd;
    }
    @Override public ReceiptItem purchaseProduct(Product product) {
        if (!(products.contains(product))) {
            throw new ProductNotFoundException();
        }
        if (product == null) {
            throw new IllegalArgumentException();
        }
        if (((ProductImpl)product).getInventory() == 0) {
            throw new OutOfStockException();
        }
        ReceiptItemImpl receipt = new ReceiptItemImpl(product.getName(), ((ProductImpl)product).getDiscountedPrice(), getName());
        ((ProductImpl) product).setInventory(-1);

        PurchaseEvent purchase = new PurchaseEvent(product, this);
        notify(purchase);

        return receipt;
    }
    @Override public void restockProduct(Product product, int numItems) {
        if (!(products.contains(product))) {
            throw new ProductNotFoundException();
        }
        if (product == null || numItems < 0) {
            throw new IllegalArgumentException();
        }
        ((ProductImpl)product).setInventory(numItems);
        if (!getIsInStock(product)) {
            BackInStockEvent back = new BackInStockEvent(product, this);
            notify(back);
        }
    }
    @Override public void startSale(Product product, double percentOff) {
        if (!(products.contains(product))) {
            throw new ProductNotFoundException();
        }
        if (product == null || !(percentOff > 0.0 && percentOff < 1.0)) {
            throw new IllegalArgumentException();
        }
        ((ProductImpl)product).setPercentOff(percentOff);
        SaleStartEvent newSale = new SaleStartEvent(product, this);
        notify(newSale);
    }
    @Override public void endSale(Product product) {
        if (!(products.contains(product))) {
            throw new ProductNotFoundException();
        }
        if (product == null) {
            throw new IllegalArgumentException();
        }
        ((ProductImpl)product).setPercentOff(0);
        SaleEndEvent endEvent = new SaleEndEvent(product, this);
        notify(endEvent);
    }
    @Override public int getProductInventory(Product product) {
        if (!(products.contains(product))) {
            throw new ProductNotFoundException();
        }
        if (product == null) {
            throw new IllegalArgumentException();
        }
        return ((ProductImpl)product).getInventory();
    }
    @Override public boolean getIsInStock(Product product) {
        if (!(products.contains(product))) {
            throw new ProductNotFoundException();
        }
        if (product == null) {
            throw new IllegalArgumentException();
        }
        return ((ProductImpl)product).getInStock();
    }
    @Override public double getSalePrice(Product product) {
        if (!(products.contains(product))) {
            throw new ProductNotFoundException();
        }
        if (product == null) {
            throw new IllegalArgumentException();
        }
        return ((ProductImpl)product).getDiscountedPrice();
    }
    @Override public boolean getIsOnSale(Product product) {
        if (!(products.contains(product))) {
            throw new ProductNotFoundException();
        }
        if (product == null) {
            throw new IllegalArgumentException();
        }
        return ((ProductImpl)product).hasDiscount();
    }

    private void notify(StoreEventImpl storeEvent) {
        for (StoreObserver looker : observers) {
            looker.update(storeEvent);
        }
    }


}
