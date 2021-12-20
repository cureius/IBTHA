package com.freelearners.ibtha.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

public class ProductPicture implements Parcelable {
    String _id;
    String data;
    String contentType;


    public ProductPicture(String _id, String data, String contentType) {
        this._id = _id;
        this.data = data;
        this.contentType = contentType;
    }

    public ProductPicture() {
    }

    protected ProductPicture(Parcel in) {
        _id = in.readString();
        data = in.readString();
        contentType = in.readString();
    }

    public static final Creator<ProductPicture> CREATOR = new Creator<ProductPicture>() {
        @Override
        public ProductPicture createFromParcel(Parcel in) {
            return new ProductPicture(in);
        }

        @Override
        public ProductPicture[] newArray(int size) {
            return new ProductPicture[size];
        }
    };

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @NonNull
    @Override
    public String toString() {
        return "ProductPicture{" +
                "_id='" + _id + '\'' +
                ", data='" + data + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(data);
        dest.writeString(contentType);
    }
}
