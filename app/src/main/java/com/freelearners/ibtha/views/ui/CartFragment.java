package com.freelearners.ibtha.views.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.freelearners.ibtha.R;
import com.freelearners.ibtha.model.CartItem;
import com.freelearners.ibtha.viewmodels.CartViewModel;
import com.freelearners.ibtha.views.adapter.CartItemAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    private TextView totalCalculate;
    private TextView totalPay;


    public CartItemAdapter cartitemAdapter;
    public ArrayList<CartItem> cartItemArrayList = new ArrayList<>();

    public CartFragment() {
//         Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        totalCalculate = view.findViewById(R.id.total_item_cart);
        totalPay = view.findViewById(R.id.total_price_cart);

        cartitemAdapter = new CartItemAdapter(cartItemArrayList, getContext());
        RecyclerView recyclerView = view.findViewById(R.id.cart_recycler_view);
//        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(cartitemAdapter);
//
        CartViewModel cartViewModel = ViewModelProviders.of(requireActivity()).get(CartViewModel.class);
        cartViewModel.getTotalPayable(requireContext());
        cartViewModel.makeApiCall(requireContext());
        cartViewModel.getItemCount().observe(getViewLifecycleOwner(), integer -> totalCalculate.setText("Total(" + String.valueOf(integer) + ")"));


//        cartViewModel.makeApiCall(requireActivity().getApplicationContext());

        cartViewModel.getCartItemListObserver().observe(getViewLifecycleOwner(), cartItems -> {
            if (cartItems != null) {
                cartitemAdapter.setCartItems(cartItems);
            }
        });
        cartViewModel.getPayable().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                totalPay.setText(String.valueOf(integer));
            }
        });

    }
}