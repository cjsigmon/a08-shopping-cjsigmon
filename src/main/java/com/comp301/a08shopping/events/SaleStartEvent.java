package com.comp301.a08shopping.events;

import com.comp301.a08shopping.Product;
import com.comp301.a08shopping.Store;

public class SaleStartEvent extends StoreEventImpl {
  public SaleStartEvent(Product product, Store store) {
    super(product, store);
  }

  @Override
  public String getEventType() {
    return "SaleStartEvent";
  }
}
