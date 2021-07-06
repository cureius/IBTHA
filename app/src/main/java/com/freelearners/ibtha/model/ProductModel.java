package com.freelearners.ibtha.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductModel implements Parcelable {
    String _id;
    String name;
    String slug;
    int price;
    String description;
    int quantity;
    Category category;
    List<ProductPicture> productPictures;
    List<Review> reviews;

    public String TAG = ProductModel.class.getName();
    public ProductModel() {
    }

    protected ProductModel(Parcel in) {
        _id = in.readString();
        name = in.readString();
        slug = in.readString();
        price = in.readInt();
        description = in.readString();
        quantity = in.readInt();
        category = new Gson().fromJson(in.readString(), Category.class);
        Type productPicType = new TypeToken<ArrayList<ProductPicture>>(){}.getType();
        Type productReviewType = new TypeToken<ArrayList<Review>>(){}.getType();
        productPictures = new Gson().fromJson(in.readString(), productPicType);
        reviews = new Gson().fromJson(in.readString(), productReviewType);
        Log.d(TAG, "ProductModel: " + "READING");
    }

    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel in) {
            return new ProductModel(in);
        }

        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ProductPicture> getProductPictures() {
        return productPictures;
    }

    public void setProductPictures(List<ProductPicture> productPictures) {
        this.productPictures = productPictures;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @NotNull
    @Override
    public String toString() {
        return "ProductModel{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", category=" + category +
                ", productPictures=" + productPictures +
                ", reviews=" + reviews +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(name);
        dest.writeString(slug);
        dest.writeInt(price);
        dest.writeString(description);
        dest.writeInt(quantity);
        dest.writeString(new Gson().toJson(category));
        dest.writeString(new Gson().toJson(productPictures));
        dest.writeString(new Gson().toJson(reviews));
        Log.d(TAG, "ProductModel: " + "WRITING");

    }
}
