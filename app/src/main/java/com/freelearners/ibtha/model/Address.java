package com.freelearners.ibtha.model;

import androidx.annotation.NonNull;

import java.util.List;

public class Address {
    private String _id;
    private String name;
    private String mobileNumber;
    private String pinCode;
    private String locality;
    private String address;
    private String cityDistrictTown;
    private String landmark;
    private String alternatePhone;
    private String addressType;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityDistrictTown() {
        return cityDistrictTown;
    }

    public void setCityDistrictTown(String cityDistrictTown) {
        this.cityDistrictTown = cityDistrictTown;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getAlternatePhone() {
        return alternatePhone;
    }

    public void setAlternatePhone(String alternatePhone) {
        this.alternatePhone = alternatePhone;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    @NonNull
    @Override
    public String toString() {
        return "Address{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", locality='" + locality + '\'' +
                ", address='" + address + '\'' +
                ", cityDistrictTown='" + cityDistrictTown + '\'' +
                ", landmark='" + landmark + '\'' +
                ", alternatePhone='" + alternatePhone + '\'' +
                ", addressType='" + addressType + '\'' +
                '}';
    }
}
