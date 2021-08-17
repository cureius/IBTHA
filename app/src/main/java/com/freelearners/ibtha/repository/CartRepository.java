package com.freelearners.ibtha.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.freelearners.ibtha.database.remote.server.Constants;
import com.freelearners.ibtha.database.remote.server.data.ServerClass;
import com.freelearners.ibtha.database.remote.server.data.ServerResponseCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

public class CartRepository {

    public void addToCart(Context context, JSONObject cartJsonObject){
        SharedPreferences getSharedPreferences = context.getSharedPreferences("identification", MODE_PRIVATE);
        String token = getSharedPreferences.getString("token", null);

        new ServerClass().sendPOSTRequestToServerWithHeader(context, cartJsonObject, Constants.BASE_URL + "/api/user/cart/addtocart", token, new ServerResponseCallback() {
            @Override
            public void onJSONResponse(JSONObject jsonObject) {
                Toast.makeText(context, "added to cart", Toast.LENGTH_LONG).show();
//                        StyleableToast.makeText(context, "added to cart", R.style.toast).show();
            }

            @Override
            public void onJSONArrayResponse(JSONArray jsonArray) {
                Toast.makeText(context, "Item added to your cart", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(context, "fail to add to cart", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
