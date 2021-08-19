package com.freelearners.ibtha.views.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.freelearners.ibtha.R;
import com.freelearners.ibtha.model.ProductModel;
import com.freelearners.ibtha.viewmodels.ProductViewModel;
import com.freelearners.ibtha.views.adapter.ProductAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ShopFragment extends Fragment {
    public ProductAdapter productAdapter;
    public ArrayList<ProductModel> productModelArrayList = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_shop, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        productAdapter = new ProductAdapter(productModelArrayList, getContext());
        RecyclerView recyclerView = view.findViewById(R.id.shop_recyclerview);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
//        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(productAdapter);

        ProductViewModel productViewModel = ViewModelProviders.of(requireActivity()).get(ProductViewModel.class);
        productViewModel.getProductListObserver().observe(getViewLifecycleOwner(), productModels -> {
            if (productModels != null){
                productAdapter.setProducts(productModels);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                
//                ProductViewModel productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
                productViewModel.makeApiCall(getContext());

                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}