package com.freelearners.ibtha.model;

import org.jetbrains.annotations.NotNull;

public class CartItem {
    private int quantity;
    private String _id;
    private ProductModel product;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    @NotNull
    @Override
    public String toString() {
        return "CartItem{" +
                "quantity=" + quantity +
                ", _id='" + _id + '\'' +
                ", product=" + product +
                '}';
    }
}
