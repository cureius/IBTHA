package com.freelearners.ibtha.viewmodels;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.freelearners.ibtha.database.remote.server.Constants;
import com.freelearners.ibtha.database.remote.server.data.ServerClass;
import com.freelearners.ibtha.database.remote.server.data.ServerResponseCallback;
import com.freelearners.ibtha.model.Address;
import com.freelearners.ibtha.model.AddressPOJO;
import com.freelearners.ibtha.model.Cart;
import com.freelearners.ibtha.model.CartItem;
import com.freelearners.ibtha.model.UserAddress;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AddressViewModel extends ViewModel {
    private static final String TAG = "AddressViewModel";

    private final MutableLiveData<ArrayList<Address>> addresses;

    public AddressViewModel() {
        addresses = new MutableLiveData<>();
    }

    public LiveData<ArrayList<Address>> getAddresses() {
        return addresses;
    }

    public void makeApiCall(Context context) {
        SharedPreferences getSharedPreferences = context.getSharedPreferences("identification", MODE_PRIVATE);
        String token = getSharedPreferences.getString("token", null);

        new ServerClass().sendPOSTRequestToServerWithHeader(context,
                null,
                Constants.BASE_URL + "/api/user/getaddress",
                token,
                new ServerResponseCallback() {
                    @Override
                    public void onJSONResponse(JSONObject jsonObject) {
                        AddressPOJO address = new Gson().fromJson(String.valueOf(jsonObject), AddressPOJO.class);
                        Log.d(TAG, "onJSONResponse: " + address.getUserAddress().getAddress().toString());
                        addresses.postValue(address.getUserAddress().getAddress());
                    }

                    @Override
                    public void onJSONArrayResponse(JSONArray jsonArray) {

                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e(TAG, "onError: ", e);
                        Toast.makeText(context, "fail to get addresses", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
