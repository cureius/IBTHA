package com.freelearners.ibtha.model;

public class AddressPOJO {
    private UserAddress userAddress;

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public String toString() {
        return "AddressPOJO{" +
                "userAddress=" + userAddress +
                '}';
    }
}
