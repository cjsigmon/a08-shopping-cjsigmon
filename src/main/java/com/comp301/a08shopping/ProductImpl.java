package com.comp301.a08shopping;

public class ProductImpl implements Product{
    String name;
    double basePrice;

    public ProductImpl(String name, double basePrice) {
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
}
