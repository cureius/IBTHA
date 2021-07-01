package com.freelearners.ibtha.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.freelearners.ibtha.R;
import com.freelearners.ibtha.home.marketPlace.ProductAdapter;
import com.freelearners.ibtha.home.marketPlace.ProductModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int ID_HOME = 1;
    private final int ID_MESSAGE = 2;
    private final int ID_NOTIFICATION = 3;
    private final int ID_ACCOUNT = 4;
    private final String TAG = MainActivity.class.getName();
    ArrayList<ProductModel> productModels = new ArrayList<>();
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MeowBottomNavigation bottomNavigation = findViewById(R.id.bottom_navigation);

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
                    replace(new NotificationFragment());
                    break;

                case ID_ACCOUNT:
                    replace(new ProfileFragment());
                    break;

            }
            return null;
        });

    }

    private void replace(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.home_frame_layout, fragment);
        fragmentTransaction.commit();
    }

}