package com.freelearners.ibtha.identification;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.freelearners.ibtha.R;
import com.freelearners.ibtha.RetrofitInterface;
import com.freelearners.ibtha.home.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IdentificationActivity extends AppCompatActivity {

    private RetrofitInterface retrofitInterface;
    private FrameLayout frameLayout;
    public String BASE_URL = "http://192.168.0.104:2000"; // in home  hotspot
//        String BASE_URL = "http://192.168.43.145:2000"; // in mobile hotspot


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);


        frameLayout = findViewById(R.id.frame_layout_identification);
        setFragment(new LogInFragment());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
//                "https://jsonplaceholder.typicode.com/todos/1",
//                null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            Log.d("myApp", "the response is "+ response.getString("title"));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d("myApp", "something went wrong");
//                    }
//                });
//
//        requestQueue.add(jsonObjectRequest);
    }

    public void setFragment(Fragment Fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(), Fragment);
        fragmentTransaction.commit();
    }

    public void LOGIN(String email, String password) {
        HashMap<String, String> map = new HashMap<>();

        map.put("email", email);
        map.put("password", password);

        Call<LoginResult> call = retrofitInterface.executeLogin(map);

        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, retrofit2.Response<LoginResult> response) {

                if (response.code() == 200) {

                    LoginResult result = response.body();
                    Toast.makeText(IdentificationActivity.this, "Successfully logged in " + result,
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(IdentificationActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else if (response.code() == 404) {
                    Toast.makeText(IdentificationActivity.this, "Wrong Credentials",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Toast.makeText(IdentificationActivity.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void loginWithVolley(String endpoint, String email, String password) {
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                BASE_URL + endpoint,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("myApp", "the response is " + response);
                        Intent intent = new Intent(IdentificationActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("myApp", "something went wrong");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("email", email);
                data.put("password", password);
                return data;
            }
        };

        requestQueue.add(jsonObjectRequest);
    }

    public void SIGNUP(String name, String email, String password) {
        HashMap<String, String> map = new HashMap<>();

        map.put("firstName", name);
        map.put("lastName", name);
        map.put("email", email);
        map.put("password", password);

        Call<Void> call = retrofitInterface.executeSignup(map);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {

                if (response.code() == 200) {
                    Toast.makeText(IdentificationActivity.this,
                            "Signed up successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(IdentificationActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else if (response.code() == 400) {
                    Toast.makeText(IdentificationActivity.this,
                            "Already registered", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(IdentificationActivity.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    public void signupWithVolley(String endpoint, String name, String email, String password) {
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                BASE_URL + endpoint,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("myApp", "the response is " + response.getString("title"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("myApp", "something went wrong");
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }
}