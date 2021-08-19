package com.freelearners.ibtha.views.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.freelearners.ibtha.R;
import com.freelearners.ibtha.model.Address;
import com.freelearners.ibtha.viewmodels.CartViewModel;

public class OrderSummaryFragment extends Fragment {
    private TextView totalItem, subTotal, deliveryFee, totalPrice;
    private TextView name, mobile, addressFull, pin;
    private Button confirmOrder, changeAddress;

    public OrderSummaryFragment() {
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
        return inflater.inflate(R.layout.fragment_order_summary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Intent intent = getActivity().getIntent();
        Address address = intent.getParcelableExtra("address");


        name = view.findViewById(R.id.name_address);
        mobile = view.findViewById(R.id.mobile_number);
        addressFull = view.findViewById(R.id.address_full);
        pin = view.findViewById(R.id.pin_address);
        totalItem = view.findViewById(R.id.item_checkout);
        subTotal = view.findViewById(R.id.sub_total_checkout);
        deliveryFee = view.findViewById(R.id.delivery_fee_checkout);
        totalPrice = view.findViewById(R.id.total_price_checkout);
        confirmOrder = view.findViewById(R.id.confirmOrder);
        changeAddress = view.findViewById(R.id.deliver_here);
        changeAddress.setText("Change Address");
        CartViewModel cartViewModel = ViewModelProviders.of(requireActivity()).get(CartViewModel.class);
        cartViewModel.makeApiCall(getContext());
        cartViewModel.getItemCount().observe(getViewLifecycleOwner(), integer -> totalItem.setText(String.valueOf(integer)));
        cartViewModel.getPayable().observe(getViewLifecycleOwner(), integer -> {
            subTotal.setText(String.valueOf(integer));
            deliveryFee.setText("Free");
            totalPrice.setText(String.valueOf(integer));
        });

        name.setText(address.getName());
        mobile.setText(address.getMobileNumber());
        addressFull.setText(address.getAddress());
        pin.setText(address.getPinCode());

        confirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Order Placed", Toast.LENGTH_SHORT).show();

            }
        });
        changeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }
}