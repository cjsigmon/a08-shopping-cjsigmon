package com.comp301.a08shopping;

import com.comp301.a08shopping.events.StoreEvent;
import java.util.ArrayList;import java.util.List;

public class CustomerImpl implements Customer{
    private String name;
    private double budget;
    private List<ReceiptItem> purchaseHistory;
    public CustomerImpl(String name, double budget) {
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
        purchaseHistory.add(store.purchaseProduct(product));
    }
    @Override
    public List<ReceiptItem> getPurchaseHistory() {
        return null;
    }

    @Override
    public void update(StoreEvent event) {

    }



}
