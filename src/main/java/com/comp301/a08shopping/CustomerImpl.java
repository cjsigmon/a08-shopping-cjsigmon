package com.comp301.a08shopping;

import com.comp301.a08shopping.events.StoreEvent;
import java.util.List;

public class CustomerImpl implements Customer{
    private String name;
    private double budget;
    public CustomerImpl(String name, double budget) {
        this.name = name;
        this.budget = budget;
    }
    @Override
    public String getName() {
        return null;
    }
    @Override
    public double getBudget() {
        return 0;
    }
    @Override
    public void purchaseProduct(Product product, Store store) {

    }
    @Override
    public List<ReceiptItem> getPurchaseHistory() {
        return null;
    }

    @Override
    public void update(StoreEvent event) {

    }



}
