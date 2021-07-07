package com.freelearners.ibtha.trash.network;

import com.freelearners.ibtha.model.ProductModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.POST;

public interface APIService {
    @POST("/api/product/getProducts")
    Call<ArrayList<ProductModel>> getProductList();


}
