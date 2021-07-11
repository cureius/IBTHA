package com.freelearners.ibtha.viewmodels;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.freelearners.ibtha.model.Cart;
import com.freelearners.ibtha.model.CartItem;
import com.freelearners.ibtha.server.Constants;
import com.freelearners.ibtha.server.data.ServerClass;
import com.freelearners.ibtha.server.data.ServerResponseCallback;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class CartViewModel extends ViewModel {
    private static final String TAG = "CartViewModel";
    private final MutableLiveData<ArrayList<CartItem>> cartItemList;

    public CartViewModel() {
        cartItemList = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<CartItem>> getCartItemListObserver() {
        return cartItemList;
    }

    public void makeApiCall(Context context) {
        SharedPreferences getSharedPreferences = context.getSharedPreferences("identification", MODE_PRIVATE);
        String token = getSharedPreferences.getString("token", null);

        new ServerClass().sendPOSTRequestToServerWithHeader(context,
                null,
                Constants.BASE_URL + "/api/user/getCartProducts",
                token,
                new ServerResponseCallback() {
                    @Override
                    public void onJSONResponse(JSONObject jsonObject) {
                        Toast.makeText(context, "cart " + jsonObject.toString(), Toast.LENGTH_SHORT).show();
                        Cart cart = new Gson().fromJson(String.valueOf(jsonObject), Cart.class);
                        Log.d(TAG, "onJSONResponse: " + cart.toString());
                        cartItemList.postValue(cart.getCartItems());

                    }

                    @Override
                    public void onJSONArrayResponse(JSONArray jsonArray) throws JSONException {

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(context, "fail to get cart", Toast.LENGTH_SHORT).show();
                        cartItemList.postValue(null);

                    }
                });
    }
}
