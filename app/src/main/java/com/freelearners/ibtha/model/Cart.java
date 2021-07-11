package com.freelearners.ibtha.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Cart {
    private String _id;
    private String user;
    private ArrayList<CartItem> cartItems;
    private String createdAt;
    private String updatedAt;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @NotNull
    @Override
    public String toString() {
        return "Cart{" +
                "_id='" + _id + '\'' +
                ", user='" + user + '\'' +
                ", cartItems=" + cartItems +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
