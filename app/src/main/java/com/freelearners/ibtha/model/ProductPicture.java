package com.freelearners.ibtha.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.NotNull;

public class ProductPicture implements Parcelable {
    String _id;
    String img;


    public ProductPicture(String _id, String img) {
        this._id = _id;
        this.img = img;
    }

    public ProductPicture() {
    }

    protected ProductPicture(Parcel in) {
        _id = in.readString();
        img = in.readString();
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @NotNull
    @Override
    public String toString() {
        return "productPicture{" +
                "_id='" + _id + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(img);
    }
}
