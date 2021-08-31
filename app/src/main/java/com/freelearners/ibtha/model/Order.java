package com.freelearners.ibtha.model;

import java.util.ArrayList;

public class Order {

    String addressId;
    int totalAmount;
    ArrayList<Item> items;
    String paymentStatus;
    String paymentType;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public String toString() {
        return "Order{" +
                "addressId='" + addressId + '\'' +
                ", totalAmount=" + totalAmount +
                ", items=" + items +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", paymentType='" + paymentType + '\'' +
                '}';
    }
}
