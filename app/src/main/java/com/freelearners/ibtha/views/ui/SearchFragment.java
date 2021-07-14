package com.freelearners.ibtha.views.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.freelearners.ibtha.R;
import com.freelearners.ibtha.model.ProductModel;
import com.freelearners.ibtha.viewmodels.ProductViewModel;
import com.freelearners.ibtha.views.adapter.ProductAdapter;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    public SearchView searchView;
    public ProductAdapter productAdapter;
    public ArrayList<ProductModel> productModelArrayList = new ArrayList<>();


    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);


        productAdapter = new ProductAdapter(productModelArrayList, getContext());
        RecyclerView recyclerView = view.findViewById(R.id.search_recyclerview);
        searchView = view.findViewById(R.id.search);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerView.setAdapter(productAdapter);
//
//        ProductViewModel productViewModel = ViewModelProviders.of(requireActivity()).get(ProductViewModel.class);
//        productViewModel.getProductListObserver().observe(getViewLifecycleOwner(), productModels -> {
//            if (productModels != null){
//                productAdapter.setProducts(productModels);
//            }
//        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                productAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return view;

    }
}