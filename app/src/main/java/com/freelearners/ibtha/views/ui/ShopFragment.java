package com.freelearners.ibtha.views.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.freelearners.ibtha.R;
import com.freelearners.ibtha.model.ProductModel;
import com.freelearners.ibtha.viewmodels.ProductViewModel;
import com.freelearners.ibtha.views.adapter.ProductAdapter;

import java.util.ArrayList;

public class ShopFragment extends Fragment {
    public ProductAdapter productAdapter;
    public ArrayList<ProductModel> productModelArrayList = new ArrayList<>();

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

        ProductViewModel productViewModel = ViewModelProviders.of(requireActivity()).get(ProductViewModel.class);
        productViewModel.getProductListObserver().observe(getViewLifecycleOwner(), productModels -> {
            if (productModels != null){
                productAdapter.setProducts(productModels);
            }
        });
        return view;
    }
}