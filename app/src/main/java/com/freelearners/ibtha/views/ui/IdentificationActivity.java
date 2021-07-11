package com.freelearners.ibtha.views.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.freelearners.ibtha.model.LogInRes;
import com.freelearners.ibtha.model.ProductModel;
import com.freelearners.ibtha.model.User;
import com.freelearners.ibtha.server.Constants;
import com.freelearners.ibtha.R;
import com.freelearners.ibtha.server.data.ServerClass;
import com.freelearners.ibtha.server.data.ServerResponseCallback;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class IdentificationActivity extends AppCompatActivity {
    final static String TAG = IdentificationActivity.class.getName();

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);

        frameLayout = findViewById(R.id.frame_layout_identification);
        setFragment(new LogInFragment());

    }

    public void setFragment(Fragment Fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(), Fragment);
        fragmentTransaction.commit();
    }

    public void getRequest() {
        new ServerClass().sendGETRequestToServer(getApplicationContext(), Constants.BASE_URL,
                new ServerResponseCallback() {
                    @Override
                    public void onJSONResponse(JSONObject jsonObject) {

                    }

                    @Override
                    public void onJSONArrayResponse(JSONArray jsonArray) {

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
    }

    public void loginRequest(String email, String password, String endpoint) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("password", password);
        } catch (JSONException je) {
            Log.e(TAG, "postRequest: ", je);
            Toast.makeText(getApplicationContext(), "JSON Exception", Toast.LENGTH_SHORT).show();
        }
        new ServerClass().sendPOSTRequestToServer(getApplicationContext(),
                jsonObject,
                Constants.BASE_URL + endpoint,
                new ServerResponseCallback() {
                    @Override
                    public void onJSONResponse(JSONObject jsonObject) {
                        Log.d(TAG, "onJSONResponse: " + jsonObject.toString());
                        Toast.makeText(IdentificationActivity.this, " Logged in Successfully", Toast.LENGTH_SHORT).show();
                        LogInRes user = new Gson().fromJson(String.valueOf(jsonObject), LogInRes.class);

                        SharedPreferences sharedPreferences = getSharedPreferences("identification", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("identified", true);
                        editor.putString("token", user.getToken());
                        editor.apply();
//                        editor.commit();
                        Toast.makeText(IdentificationActivity.this, "token " + user.getToken(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onJSONArrayResponse(JSONArray jsonArray) {

                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e(TAG, "onError: ", e);
                        Toast.makeText(IdentificationActivity.this, "try again", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void signupRequest(String name, String email, String password, String endpoint) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("firstName", name);
            jsonObject.put("lastName", name);
            jsonObject.put("email", email);
            jsonObject.put("password", password);
        } catch (JSONException je) {
            Log.e(TAG, "postRequest: ", je);
            Toast.makeText(getApplicationContext(), "JSON Exception", Toast.LENGTH_SHORT).show();
        }
        new ServerClass().sendPOSTRequestToServer(getApplicationContext(),
                jsonObject,
                Constants.BASE_URL + endpoint,
                new ServerResponseCallback() {
                    @Override
                    public void onJSONResponse(JSONObject jsonObject) {
                        Log.d(TAG, "onJSONResponse: " + jsonObject.toString());
                        Toast.makeText(IdentificationActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();

                        loginRequest(email, password,"/api/signin");
//                        SharedPreferences sharedPreferences = getSharedPreferences("identification", MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putBoolean("identified", true);
//                        editor.apply();
//
//                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                        startActivity(intent);
//                        finish();
                    }

                    @Override
                    public void onJSONArrayResponse(JSONArray jsonArray) {

                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e(TAG, "onError: ", e);
                        Toast.makeText(IdentificationActivity.this, "try again", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}