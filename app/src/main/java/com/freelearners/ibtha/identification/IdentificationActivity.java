package com.freelearners.ibtha.identification;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.freelearners.ibtha.LoginResult;
import com.freelearners.ibtha.R;
import com.freelearners.ibtha.RetrofitInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IdentificationActivity extends AppCompatActivity {

    private RetrofitInterface retrofitInterface;
    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);


        frameLayout = findViewById(R.id.frame_layout_identification);
        setFragment(new LogInFragment());

        String BASE_URL = "http://192.168.43.145:3000";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

    }

    public void setFragment(Fragment Fragment) {
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),Fragment);
        fragmentTransaction.commit();
    }

    public void LOGIN(String email , String password) {

                HashMap<String, String> map = new HashMap<>();

                map.put("email", email);
                map.put("password", password);

                Call<LoginResult> call = retrofitInterface.executeLogin(map);

                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {

                        if (response.code() == 200) {

                            LoginResult result = response.body();
                            Toast.makeText(IdentificationActivity.this, "Successfully logged in "+result,
                                    Toast.LENGTH_LONG).show();

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

    public void SIGNUP(String email ,String phone , String password) {

                HashMap<String, String> map = new HashMap<>();

                map.put("phone", phone);
                map.put("email", email);
                map.put("password", password);

                Call<Void> call = retrofitInterface.executeSignup(map);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if (response.code() == 200) {
                            Toast.makeText(IdentificationActivity.this,
                                    "Signed up successfully", Toast.LENGTH_LONG).show();
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
}