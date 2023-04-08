package com.comp301.a08shopping;

import com.comp301.a08shopping.events.StoreEvent;
import com.comp301.a08shopping.events.StoreEventImpl;
import com.comp301.a08shopping.exceptions.OutOfStockException;
import com.comp301.a08shopping.exceptions.ProductNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CustomerImpl implements Customer {
  private final String name;
  private double budget;
  private final List<ReceiptItem> purchaseHistory;

  public CustomerImpl(String name, double budget) {
    if (name == null || budget < 0.00) {
      throw new IllegalArgumentException();
    }
    this.name = name;
    this.budget = budget;
    this.purchaseHistory = new ArrayList<>();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public double getBudget() {
    return budget;
  }

  @Override
  public void purchaseProduct(Product product, Store store) {
//    if (store == null || product == null) {
//      throw new IllegalArgumentException();
//    }
    if (((ProductImpl) product).getDiscountedPrice() > budget) {
      throw new IllegalStateException();
    }

    budget -= ((ProductImpl) product).getDiscountedPrice();

    ReceiptItem receipt = store.purchaseProduct(product);

    purchaseHistory.add(receipt);
  }

  @Override
  public List<ReceiptItem> getPurchaseHistory() {
    List<ReceiptItem> copy = new ArrayList<>();
    for (ReceiptItem receipt : purchaseHistory) {
      copy.add(receipt);
    }
    return copy;
  }

  @Override
  public void update(StoreEvent event) {
    switch (((StoreEventImpl) event).getEventType()) {
      case "BackInStockEvent":
        System.out.println(
            event.getProduct().getName() + " is back in stock at " + event.getStore().getName());
        break;

      case "OutOfStockEvent":
        System.out.println(
            event.getProduct().getName() + " is now out of stock at " + event.getStore().getName());
        break;
      case "PurchaseEvent":
        System.out.println(
            "Someone purchased "
                + event.getProduct().getName()
                + " at "
                + event.getStore().getName());
        break;

      case "SaleEndEvent":
        System.out.println(
            "The sale for "
                + event.getProduct().getName()
                + " at "
                + event.getStore().getName()
                + " has ended");
        break;
      case "SaleStartEvent":
        System.out.println(
            "New sale for "
                + event.getProduct().getName()
                + " at "
                + event.getStore().getName()
                + "!");
        break;
    }
  }
}
