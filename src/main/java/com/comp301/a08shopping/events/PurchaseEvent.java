package com.comp301.a08shopping.events;

import com.comp301.a08shopping.Product;
import com.comp301.a08shopping.Store;

public class PurchaseEvent extends StoreEventImpl {
  public PurchaseEvent(Product product, Store store) {
    super(product, store);
  }
}
