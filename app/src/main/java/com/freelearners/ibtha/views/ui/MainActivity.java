package com.freelearners.ibtha.views.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.freelearners.ibtha.R;
import com.freelearners.ibtha.viewmodels.CartViewModel;
import com.freelearners.ibtha.viewmodels.ProductViewModel;

public class MainActivity extends AppCompatActivity {

    private final int ID_HOME = 1;
    private final int ID_SEARCH = 2;
    private final int ID_CART = 3;
    private final int ID_ACCOUNT = 4;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MeowBottomNavigation bottomNavigation = findViewById(R.id.bottom_navigation);

        ProductViewModel productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.makeApiCall(this);

        CartViewModel cartViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        cartViewModel.makeApiCall(this);
        cartViewModel.getCartItemCount(this);
        cartViewModel.getItemCount().observe(this, integer -> bottomNavigation.setCount(ID_CART, String.valueOf(integer)));

        TextView appName = findViewById(R.id.toolbar_title);
        appName.setText("Indian Black Tea");
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_SEARCH, R.drawable.ic_baseline_search_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_CART, R.drawable.ic_outline_shopping_cart_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_ACCOUNT, R.drawable.ic_account));

        bottomNavigation.show(ID_HOME, true);
        replace(new ShopFragment());
        bottomNavigation.setOnClickMenuListener(model -> {
            switch (model.getId()) {
                case ID_HOME:
                    replace(new ShopFragment());
                    break;

                case ID_SEARCH:
                    replace(new SearchFragment());
                    break;

                case ID_CART:
                    replace(new CartFragment());
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
    public void addToCart() {

//            CartViewModel cartViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
//            cartViewModel.addItem(this, jsonObject);
//
    }

}