package com.freelearners.ibtha.home.marketPlace;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.util.ArrayList;

public class ProductModel implements Parcelable {
    String _id;
    String name;
    String slug;
    int price;
    String description;
    JSONArray productPictures;
    JSONArray reviews;
    String category;
    int quantity;
    String createdBy;

    public ProductModel(String _id, String name, String slug, int price, String description, JSONArray productPictures, JSONArray reviews, String category, int quantity, String createdBy) {
        this._id = _id;
        this.name = name;
        this.slug = slug;
        this.price = price;
        this.description = description;
        this.productPictures = productPictures;
        this.reviews = reviews;
        this.category = category;
        this.quantity = quantity;
        this.createdBy = createdBy;
    }

    protected ProductModel(Parcel in) {
        _id = in.readString();
        name = in.readString();
        slug = in.readString();
        price = in.readInt();
        description = in.readString();
        category = in.readString();
        quantity = in.readInt();
        createdBy = in.readString();
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

    public ProductModel() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(name);
        dest.writeString(slug);
        dest.writeInt(price);
        dest.writeString(description);
        dest.writeString(category);
        dest.writeInt(quantity);
        dest.writeString(createdBy);
    }

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

    public JSONArray getProductPictures() {
        return productPictures;
    }

    public void setProductPictures(JSONArray productPictures) { this.productPictures = productPictures; }

    public JSONArray getReviews() {
        return reviews;
    }

    public void setReviews(JSONArray reviews) {
        this.reviews = reviews;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public static Creator<ProductModel> getCREATOR() {
        return CREATOR;
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
                ", productPictures=" + productPictures +
                ", reviews=" + reviews +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
