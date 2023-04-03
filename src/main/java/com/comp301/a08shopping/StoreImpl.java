package com.comp301.a08shopping;

import java.util.List;
public class StoreImpl implements Store{
    private String name;
    public StoreImpl(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return null;
    }

    @Override public void addObserver(StoreObserver observer) {

    }

    @Override public void removeObserver(StoreObserver observer) {

    }
    @Override public List<Product> getProducts() {
    return null;
    }
    @Override public Product createProduct(String name, double basePrice, int inventory) {
    return null;
    }
    @Override public ReceiptItem purchaseProduct(Product product) {
    return null;
    }
    @Override public void restockProduct(Product product, int numItems) {

    }
    @Override public void startSale(Product product, double percentOff) {

    }
    @Override public void endSale(Product product) {

    }
    @Override public int getProductInventory(Product product) {
    return 0;
    }
    @Override public boolean getIsInStock(Product product) {
    return false;
    }
    @Override public double getSalePrice(Product product) {
    return 0;
    }
    @Override public boolean getIsOnSale(Product product) {
    return false;
    }


}
