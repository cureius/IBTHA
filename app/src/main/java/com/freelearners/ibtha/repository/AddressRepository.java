package com.freelearners.ibtha.repository;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.freelearners.ibtha.database.remote.server.Constants;
import com.freelearners.ibtha.database.remote.server.data.ServerClass;
import com.freelearners.ibtha.database.remote.server.data.ServerResponseCallback;
import com.freelearners.ibtha.views.ui.AddAddressFragment;
import com.freelearners.ibtha.views.ui.IdentificationActivity;
import com.freelearners.ibtha.views.ui.MainActivity;
import com.freelearners.ibtha.views.ui.OrderActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class AddressRepository {

    public void addAddress(Context context, JSONObject addressJsonObject){
        SharedPreferences getSharedPreferences = context.getSharedPreferences("identification", MODE_PRIVATE);
        String token = getSharedPreferences.getString("token", null);

        new ServerClass().sendPOSTRequestToServerWithHeader(context, addressJsonObject, Constants.BASE_URL + "/api/user/address/create", token, new ServerResponseCallback() {
            @Override
            public void onJSONResponse(JSONObject jsonObject) {
                Toast.makeText(context, "address added", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onJSONArrayResponse(JSONArray jsonArray) {
                Toast.makeText(context, " array response", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(context, "fail to add address", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
