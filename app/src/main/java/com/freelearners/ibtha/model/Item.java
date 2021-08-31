package com.freelearners.ibtha.model;

public class Item {
    String productId;
    int payablePrice;
    int purchasedQty;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getPayablePrice() {
        return payablePrice;
    }

    public void setPayablePrice(int payablePrice) {
        this.payablePrice = payablePrice;
    }

    public int getPurchasedQty() {
        return purchasedQty;
    }

    public void setPurchasedQty(int purchasedQty) {
        this.purchasedQty = purchasedQty;
    }

    @Override
    public String toString() {
        return "Item{" +
                "productId='" + productId + '\'' +
                ", payablePrice=" + payablePrice +
                ", purchasedQty=" + purchasedQty +
                '}';
    }
}
