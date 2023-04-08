package com.comp301.a08shopping;

import com.comp301.a08shopping.events.*;
import com.comp301.a08shopping.exceptions.OutOfStockException;
import com.comp301.a08shopping.exceptions.ProductNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class StoreImpl implements Store {
  private final String name;
  private final List<StoreObserver> observers;
  private final List<Product> products;

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

  @Override
  public void addObserver(StoreObserver observer) {
    if (observer == null) {
      throw new IllegalArgumentException();
    }
    observers.add(observer);
  }

  @Override
  public void removeObserver(StoreObserver observer) {
    if (observer == null) {
      throw new IllegalArgumentException();
    }
    observers.remove(observer);
  }

  @Override
  public List<Product> getProducts() {
    ArrayList<Product> copy = new ArrayList<>();
    for (Product prod : products) {
      copy.add(prod);
    }

    return copy;
  }

  @Override
  public Product createProduct(String name, double basePrice, int inventory) {
    if (name == null || basePrice <= 0.00 || inventory < 0) {
      throw new IllegalArgumentException("inventory must be positive");
    }
    Product newProd = new ProductImpl(name, basePrice);
    ((ProductImpl) newProd).setInventory(inventory);
    products.add(newProd);
    return newProd;
  }

  @Override
  public ReceiptItem purchaseProduct(Product product) {
    validateProduct(product);

    if (!((ProductImpl) product).getInStock()) {
      throw new OutOfStockException();
    }

    ReceiptItem receipt =
        new ReceiptItemImpl(
            product.getName(), ((ProductImpl) product).getDiscountedPrice(), getName());
    ((ProductImpl) product).remInventory();
    PurchaseEvent purchase = new PurchaseEvent(product, this);
    notify(purchase);

    if (!((ProductImpl) product).getInStock()) {
      OutOfStockEvent out = new OutOfStockEvent(product, this);
      notify(out);
    }

    return receipt;
  }

  @Override
  public void restockProduct(Product product, int numItems) {
    if (numItems < 0 || product == null) {
      throw new IllegalArgumentException();
    }
    if (!(products.contains(product))) {
      throw new ProductNotFoundException();
    }
    if (!getIsInStock(product)) {
      BackInStockEvent back = new BackInStockEvent(product, this);
      notify(back);
    }
    ((ProductImpl) product).setInventory(numItems);
  }

  @Override
  public void startSale(Product product, double percentOff) {
    validateProduct(product);
    if (!(percentOff > 0.0 && percentOff < 1.0)) {
      throw new IllegalArgumentException();
    }
    ((ProductImpl) product).setPercentOff(percentOff);
    SaleStartEvent newSale = new SaleStartEvent(product, this);
    notify(newSale);
  }

  @Override
  public void endSale(Product product) {
    validateProduct(product);
    ((ProductImpl) product).setPercentOff(0);
    SaleEndEvent endEvent = new SaleEndEvent(product, this);
    notify(endEvent);
  }

  @Override
  public int getProductInventory(Product product) {
    validateProduct(product);
    return ((ProductImpl) product).getInventory();
  }

  @Override
  public boolean getIsInStock(Product product) {
    validateProduct(product);
    return ((ProductImpl) product).getInventory() > 0;
  }

  @Override
  public double getSalePrice(Product product) {
    validateProduct(product);
    return ((ProductImpl) product).getDiscountedPrice();
  }

  @Override
  public boolean getIsOnSale(Product product) {
    if (product == null) {
      throw new IllegalArgumentException();
    }
    if (!(products.contains(product))) {
      throw new ProductNotFoundException();
    }
    return ((ProductImpl) product).hasDiscount();
  }

  private void notify(StoreEventImpl storeEvent) {
    for (StoreObserver looker : observers) {
      looker.update(storeEvent);
    }
  }

  private void validateProduct(Product product) {
    if (product == null) {
      throw new IllegalArgumentException();
    }
    boolean fails = true;
    for (Product pr : products) {
      if (pr.equals(product)) {
        fails = false;
        break;
      }
    }
    if (fails) {
      throw new ProductNotFoundException();
    }
  }
}
