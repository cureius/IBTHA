package com.freelearners.ibtha.views.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.freelearners.ibtha.R;
import com.freelearners.ibtha.viewmodels.ProductViewModel;
import com.freelearners.ibtha.views.adapter.ProductAdapter;
import com.freelearners.ibtha.model.ProductModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShopFragment extends Fragment {
    public static final String TAG = ShopFragment.class.getName();
    public ProductAdapter productAdapter;
    public ArrayList<ProductModel> productModelArrayList = new ArrayList<>();
    private ProductViewModel productViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        productAdapter = new ProductAdapter(productModelArrayList, getContext());
        RecyclerView recyclerView = view.findViewById(R.id.shop_recyclerview);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(productAdapter);

        productViewModel = ViewModelProviders.of(requireActivity()).get(ProductViewModel.class);
        productViewModel.getProductListObserver().observe(getViewLifecycleOwner(), productModels -> {
            if (productModels != null){
                productModelArrayList = (ArrayList<ProductModel>) productModels;
                productAdapter.setProducts((ArrayList<ProductModel>) productModels);
            }
        });
        productViewModel.makeApiCall();

//        new ServerClass().sendPOSTArrayRequestToServer(getContext(),
//                Constants.BASE_URL + "/api/product/getProducts",
//                new ServerResponseCallback() {
//                    @Override
//                    public void onJSONResponse(JSONObject jsonObject) {
//                        Log.d(TAG, "onJSONResponse: " + jsonObject.toString());
//                    }
//
//                    @Override
//                    public void onJSONArrayResponse(JSONArray jsonArray) {
//                        productModels.clear();
//                        Type productType = new TypeToken<ArrayList<ProductModel>>(){}.getType();
//                        productModels = new Gson().fromJson(String.valueOf(jsonArray), productType);
//                        Toast.makeText(getContext(), "got " + Integer.toString(productModels.size()) + " Products", Toast.LENGTH_SHORT).show();
//                        productAdapter = new ProductAdapter(productModels, getContext());
//                        recyclerView.setAdapter(productAdapter);
//                        productAdapter.notifyDataSetChanged();
//                        Log.d(TAG, "onJSONArrayResponse: " + productModels.toString());
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//                        Log.e(TAG, "onError: ", e);
//                        Toast.makeText(getContext(), "try again", Toast.LENGTH_SHORT).show();
//                    }
//                });

        return view;
    }
}