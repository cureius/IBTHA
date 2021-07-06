package com.freelearners.ibtha.views.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.freelearners.ibtha.server.Constants;
import com.freelearners.ibtha.R;
import com.freelearners.ibtha.server.ServerClass;
import com.freelearners.ibtha.server.ServerResponseCallback;
import com.freelearners.ibtha.views.adapter.ProductAdapter;
import com.freelearners.ibtha.model.ProductModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int ID_HOME = 1;
    private final int ID_MESSAGE = 2;
    private final int ID_NOTIFICATION = 3;
    private final int ID_ACCOUNT = 4;
    private TextView appName;
    private final String TAG = MainActivity.class.getName();
    ArrayList<ProductModel> productModels = new ArrayList<>();
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MeowBottomNavigation bottomNavigation = findViewById(R.id.bottom_navigation);
        appName = findViewById(R.id.toolbar_title);
        appName.setText("Indian Black Tea");
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_MESSAGE, R.drawable.ic_message));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_NOTIFICATION, R.drawable.ic_notifications));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_ACCOUNT, R.drawable.ic_account));

        bottomNavigation.setCount(ID_NOTIFICATION, "4");
        bottomNavigation.show(ID_HOME, true);
        replace(new ShopFragment());
        bottomNavigation.setOnClickMenuListener(model -> {
            switch (model.getId()) {
                case ID_HOME:
                    replace(new ShopFragment());
                    break;

                case ID_MESSAGE:
                    replace(new SearchFragment());
                    break;

                case ID_NOTIFICATION:
                    replace(new CartFragment());
                    break;

                case ID_ACCOUNT:
                    replace(new ProfileFragment());
                    break;

            }
            return null;
        });

//        Log.d(TAG, "onCreate: "+ getAllProducts().toString());
    }

    private void replace(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.home_frame_layout, fragment);
        fragmentTransaction.commit();
    }

//    public ArrayList<ProductModel> getAllProducts(){
//
//        new ServerClass().sendPOSTArrayRequestToServer(getApplicationContext(),
//                Constants.BASE_URL + "/api/product/getProducts",
//                new ServerResponseCallback() {
//                    @Override
//                    public void onJSONResponse(JSONObject jsonObject) {
//                        Log.d(TAG, "onJSONResponse: " + jsonObject.toString());
//                    }
//
//                    @Override
//                    public void onJSONArrayResponse(JSONArray jsonArray) {
//                        productModels.clear();
//                        Type productType = new TypeToken<ArrayList<ProductModel>>(){}.getType();
//                        productModels = new Gson().fromJson(String.valueOf(jsonArray), productType);
//                        Toast.makeText(getApplicationContext(), "got main " + Integer.toString(productModels.size()) + " Products", Toast.LENGTH_SHORT).show();
//                        Log.d(TAG, "onJSONArrayResponse: " + productModels.toString());
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//                        Log.e(TAG, "onError: ", e);
//                        Toast.makeText(getApplicationContext(), "try again", Toast.LENGTH_SHORT).show();
//                    }
//                });
//        return productModels;
//    }
}