package com.comp301.a08shopping;

public class ProductImpl implements Product{
    private String name;
    private double basePrice;

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
}
