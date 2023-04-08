package com.comp301.a08shopping;

import static org.junit.Assert.assertTrue;

import com.comp301.a08shopping.events.BackInStockEvent;import com.comp301.a08shopping.events.StoreEvent;import com.comp301.a08shopping.events.StoreEventImpl;import org.junit.Test;

/** Unit test for simple App. */
public class AppTest {
  /** Rigorous Test :-) */
  @Test
  public void shouldAnswerWithTrue() {
    Product myProd = new ProductImpl("food", 1.0);
    Store myStore = new StoreImpl("hardeees");
    StoreEventImpl myEvn = new BackInStockEvent(myProd, myStore);
    System.out.println("My event type: " + myEvn.toString());
    assertTrue(true);
  }
}
