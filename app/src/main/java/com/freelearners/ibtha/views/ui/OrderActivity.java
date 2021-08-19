package com.freelearners.ibtha.views.ui;

import static com.freelearners.ibtha.views.ui.IdentificationActivity.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.freelearners.ibtha.R;
import com.freelearners.ibtha.repository.AddressRepository;

import org.json.JSONException;
import org.json.JSONObject;

public class OrderActivity extends AppCompatActivity {
    private FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        frameLayout = findViewById(R.id.checkout_fragmentContainerView);
        setFragment(new AddressFragment());

    }

    public void setFragment(Fragment Fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(), Fragment);
        fragmentTransaction.commit();
    }

    public void addUserAddress(String name,
                               String mobileNumber,
                               String pinCode,
                               String locality,
                               String address,
                               String cityDistrictTown,
                               String landmark,
                               String alternatePhone,
                               String addressType) {

        AddressRepository addressRepository = new AddressRepository();
        JSONObject jsonObject = new JSONObject();
        JSONObject addressObj = new JSONObject();
        JSONObject payload = new JSONObject();

        try {
            addressObj.put("name", name);
            addressObj.put("mobileNumber", mobileNumber);
            addressObj.put("pinCode", pinCode);
            addressObj.put("locality", locality);
            addressObj.put("address", address);
            addressObj.put("cityDistrictTown", cityDistrictTown);
            addressObj.put("landmark", landmark);
            addressObj.put("alternatePhone", alternatePhone);
            addressObj.put("addressType", addressType);
        } catch (JSONException je) {
            Log.e(TAG, "postRequest: ", je);
            Toast.makeText(getApplicationContext(), "JSON Exception", Toast.LENGTH_SHORT).show();
        }

        try {
            payload.put("address", addressObj);

        } catch (JSONException je) {
            Log.e(TAG, "postRequest: ", je);
            Toast.makeText(getApplicationContext(), "JSON Exception", Toast.LENGTH_SHORT).show();
        }

        try {
            jsonObject.put("payload", payload);

        } catch (JSONException je) {
            Log.e(TAG, "postRequest: ", je);
            Toast.makeText(getApplicationContext(), "JSON Exception", Toast.LENGTH_SHORT).show();
        }
        addressRepository.addAddress(this, jsonObject);
        setFragment(new CheckOutFragment());

    }
}