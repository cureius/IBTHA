package com.freelearners.ibtha.views.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freelearners.ibtha.R;
import com.freelearners.ibtha.model.CartItem;
import com.freelearners.ibtha.viewmodels.CartViewModel;
import com.freelearners.ibtha.views.adapter.CartItemAdapter;

import java.util.ArrayList;

public class CheckOutFragment extends Fragment {

    public ArrayList<CartItem> cartItemArrayList = new ArrayList<>();

    public CheckOutFragment() {
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
        return inflater.inflate(R.layout.fragment_check_out, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CartItemAdapter cartitemAdapter = new CartItemAdapter(cartItemArrayList, getContext());
        RecyclerView recyclerView = view.findViewById(R.id.checkout_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(cartitemAdapter);
        CartViewModel cartViewModel = ViewModelProviders.of(requireActivity()).get(CartViewModel.class);
        cartViewModel.makeApiCall(requireContext());

        cartViewModel.getCartItemListObserver().observe(getViewLifecycleOwner(), cartItems -> {
            if (cartItems != null) {
                cartitemAdapter.setCartItems(cartItems);
            }
        });
    }
}