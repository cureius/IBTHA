package com.freelearners.ibtha.views.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.freelearners.ibtha.R;
import com.freelearners.ibtha.model.CartItem;
import com.freelearners.ibtha.viewmodels.CartViewModel;
import com.freelearners.ibtha.views.adapter.CartItemAdapter;

import java.util.ArrayList;

public class CheckOutFragment extends Fragment {

    public ArrayList<CartItem> cartItemArrayList = new ArrayList<>();
    private TextView totalItem, subTotal, deliveryFee, totalPrice;
    private Button checkout;

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

        totalItem = view.findViewById(R.id.item_checkout);
        subTotal = view.findViewById(R.id.sub_total_checkout);
        deliveryFee = view.findViewById(R.id.delivery_fee_checkout);
        totalPrice = view.findViewById(R.id.total_price_checkout);
        checkout = view.findViewById(R.id.checkout_btn);
        TextView back = view.findViewById(R.id.toolbar_back_tv);

        back.setOnClickListener(v -> requireActivity().onBackPressed());
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
        cartViewModel.getItemCount().observe(getViewLifecycleOwner(), integer -> totalItem.setText(String.valueOf(integer)));
        cartViewModel.getPayable().observe(getViewLifecycleOwner(), integer -> {
            subTotal.setText(String.valueOf(integer));
            totalPrice.setText(String.valueOf(integer));
        });

    }
}