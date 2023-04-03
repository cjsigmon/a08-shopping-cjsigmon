package com.comp301.a08shopping.events;

import com.comp301.a08shopping.Product;
import com.comp301.a08shopping.Store;

public class StoreEventImpl implements StoreEvent {
    private final Product product;
    private final Store store;

    public StoreEventImpl(Product product, Store store) {
        this.product = product;
        this.store = store;
    }

  @Override
  public Product getProduct() {
    return product;
  }

  @Override
  public Store getStore() {
    return store;
  }

}
