package com.freelearners.ibtha.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class Address implements Parcelable {
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

    protected Address(Parcel in) {
        _id = in.readString();
        name = in.readString();
        mobileNumber = in.readString();
        pinCode = in.readString();
        locality = in.readString();
        address = in.readString();
        cityDistrictTown = in.readString();
        landmark = in.readString();
        alternatePhone = in.readString();
        addressType = in.readString();
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(name);
        dest.writeString(mobileNumber);
        dest.writeString(pinCode);
        dest.writeString(locality);
        dest.writeString(address);
        dest.writeString(cityDistrictTown);
        dest.writeString(landmark);
        dest.writeString(alternatePhone);
        dest.writeString(addressType);
    }
}
