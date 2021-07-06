package com.freelearners.ibtha.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.freelearners.ibtha.model.ProductModel;
import com.freelearners.ibtha.network.APIService;
import com.freelearners.ibtha.network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewModel extends ViewModel {
    private MutableLiveData<List<ProductModel>> productList;

    public ProductViewModel(){
        productList = new MutableLiveData<>();
    }
    public MutableLiveData<List<ProductModel>> getProductListObserver(){
        return productList;
    }
    public void makeApiCall(){
        APIService apiService = RetroInstance.getRetroClint().create(APIService.class);
        Call<List<ProductModel>> call = apiService.getProductList();
        call.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                productList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                productList.postValue(null);
            }
        });
    }
}
