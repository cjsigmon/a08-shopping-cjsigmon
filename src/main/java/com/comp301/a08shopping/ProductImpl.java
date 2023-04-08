package com.comp301.a08shopping;

public class ProductImpl implements Product {
  private final String name;
  private final double basePrice;
  private int inventory = 0;
  private double percentOff;

  public ProductImpl(String name, double basePrice) {
    if (basePrice <= 0.00) {
      throw new IllegalArgumentException("base must be positive amount");
    }
    this.name = name;
    this.basePrice = basePrice;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public double getBasePrice() {
    return basePrice;
  }

  public void setInventory(int amount) {
    inventory = inventory + amount;
  }

  public void remInventory() {
    inventory--;
  }

  public int getInventory() {
    return inventory;
  }

  public void setPercentOff(double percentOff) {
    this.percentOff = percentOff;
  }

  public double getDiscountedPrice() {
    double price = basePrice - (percentOff * basePrice);
    double roundedPrice = Math.round(price * 100.0) / 100.0;
    return roundedPrice;
  }

  public boolean hasDiscount() {
    return percentOff > 0.00;
  }

  public boolean getInStock() {
    return inventory > 0;
  }
}
