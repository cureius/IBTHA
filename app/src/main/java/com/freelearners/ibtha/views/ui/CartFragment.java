package com.freelearners.ibtha.views.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.freelearners.ibtha.R;
import com.freelearners.ibtha.model.Cart;
import com.freelearners.ibtha.model.CartItem;
import com.freelearners.ibtha.server.Constants;
import com.freelearners.ibtha.server.data.ServerClass;
import com.freelearners.ibtha.server.data.ServerResponseCallback;
import com.freelearners.ibtha.viewmodels.CartViewModel;
import com.freelearners.ibtha.views.adapter.CartItemAdapter;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class CartFragment extends Fragment {

    private static final String TAG = "CartFragment";
    public CartItemAdapter cartitemAdapter;

    public CartFragment() {
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
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.cart_recycler_view);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(cartitemAdapter);

//        CartViewModel cartViewModel = ViewModelProviders.of(requireActivity()).get(CartViewModel.class);
//        cartViewModel.makeApiCall(requireActivity().getApplicationContext());
//
//        cartViewModel.getCartItemListObserver().observe(getViewLifecycleOwner(), new Observer<ArrayList<CartItem>>() {
//            @Override
//            public void onChanged(ArrayList<CartItem> cartItems) {
//                if (cartItems != null){
//                    cartitemAdapter.setCartItems(cartItems);
//                }
//            }
//        });

        SharedPreferences getSharedPreferences = requireContext().getSharedPreferences("identification", MODE_PRIVATE);
        String token = getSharedPreferences.getString("token", null);

        new ServerClass().sendPOSTRequestToServerWithHeader(getContext(),
                null,
                Constants.BASE_URL + "/api/user/getCartProducts",
                token,
                new ServerResponseCallback() {
                    @Override
                    public void onJSONResponse(JSONObject jsonObject) {
//                        Toast.makeText(getContext(), "cart " + jsonObject.toString(), Toast.LENGTH_SHORT).show();
                        Cart cart = new Gson().fromJson(String.valueOf(jsonObject), Cart.class);
                        Log.d(TAG, "onJSONResponse: " + cart.toString());
                        Log.d(TAG, "onJSONResponse: " + cart.getCartItems());
//                        cartItemList.postValue(cart.getCartItems());
                        cartitemAdapter = new CartItemAdapter(cart.getCartItems(), getContext());
                        recyclerView.setAdapter(cartitemAdapter);

                        cartitemAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onJSONArrayResponse(JSONArray jsonArray) throws JSONException {

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getContext(), "fail to get cart", Toast.LENGTH_SHORT).show();
//                        cartItemList.postValue(null);

                    }
                });

        return view;
    }
}