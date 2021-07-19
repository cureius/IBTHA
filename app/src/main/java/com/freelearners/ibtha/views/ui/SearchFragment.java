package com.freelearners.ibtha.views.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.freelearners.ibtha.R;
import com.freelearners.ibtha.model.ProductModel;
import com.freelearners.ibtha.viewmodels.ProductViewModel;
import com.freelearners.ibtha.views.adapter.SearchAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    public SearchView searchView;
    public SearchAdapter searchAdapter;
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

        return view;

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchAdapter = new SearchAdapter(productModelArrayList, getContext());
        RecyclerView recyclerView = view.findViewById(R.id.search_recyclerview);
        searchView = view.findViewById(R.id.search);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(searchAdapter);

        ProductViewModel productViewModel = ViewModelProviders.of(requireActivity()).get(ProductViewModel.class);
        productViewModel.getProductListObserver().observe(getViewLifecycleOwner(), productModels -> {
            if (productModels != null){
                searchAdapter.setProducts(productModels);

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        Toast.makeText(getContext(), newText, Toast.LENGTH_SHORT).show();
                        searchAdapter.getFilter().filter(newText);
                        return true;
                    }
                });

            }
        });
    }
}