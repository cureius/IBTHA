package com.freelearners.ibtha.network;

import com.freelearners.ibtha.model.ProductModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {
    @POST("/api/product/getProducts")
    Call<List<ProductModel>> getProductList();


}
