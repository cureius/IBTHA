package com.freelearners.ibtha.views.ui;

import static android.content.Context.MODE_PRIVATE;
import static com.freelearners.ibtha.views.ui.IdentificationActivity.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.freelearners.ibtha.R;
import com.freelearners.ibtha.database.remote.server.Constants;
import com.freelearners.ibtha.database.remote.server.data.ServerClass;
import com.freelearners.ibtha.database.remote.server.data.ServerResponseCallback;
import com.freelearners.ibtha.model.Address;
import com.freelearners.ibtha.model.Item;
import com.freelearners.ibtha.viewmodels.CartViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class OrderSummaryFragment extends Fragment {
    private TextView totalItem, subTotal, deliveryFee, totalPrice;
    private TextView name, mobile, addressFull, pin;
    private Button confirmOrder, changeAddress;
    private int totalAmount;

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

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CartViewModel cartViewModel = ViewModelProviders.of(requireActivity()).get(CartViewModel.class);
        cartViewModel.makeApiCall(requireContext());
        ArrayList<Item> items = new ArrayList<>();

        Intent intent = requireActivity().getIntent();
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
        cartViewModel.getItemCount().observe(getViewLifecycleOwner(), integer -> totalItem.setText(String.valueOf(integer)));
        cartViewModel.getPayable().observe(getViewLifecycleOwner(), integer -> {
            totalAmount = integer;
            subTotal.setText(String.valueOf(integer));
            deliveryFee.setText("Free");
            totalPrice.setText(String.valueOf(integer));
        });

        name.setText(address.getName());
        mobile.setText(address.getMobileNumber());
        addressFull.setText(address.getAddress());
        pin.setText(address.getPinCode());

        JSONObject orderJsonObject = new JSONObject();
        try {
            orderJsonObject.put("addressId", address.get_id());
            orderJsonObject.put("totalAmount", totalAmount);
            orderJsonObject.put("items", items);
            orderJsonObject.put("paymentStatus", "pending");
            orderJsonObject.put("paymentType", "cod");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        confirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences getSharedPreferences = requireContext().getSharedPreferences("identification", MODE_PRIVATE);
                String token = getSharedPreferences.getString("token", null);

                new ServerClass().sendPOSTRequestToServerWithHeader(getContext(), orderJsonObject, Constants.BASE_URL + "/api/addOrder", token, new ServerResponseCallback() {
                    @Override
                    public void onJSONResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(), "order added", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "onJSONResponse: " + jsonObject);
                    }

                    @Override
                    public void onJSONArrayResponse(JSONArray jsonArray) {
                        Toast.makeText(getContext(), " array response", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getContext(), "fail to add order", Toast.LENGTH_SHORT).show();
                    }
                });

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