package com.freelearners.ibtha.views.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.freelearners.ibtha.R;
import com.freelearners.ibtha.viewmodels.CartViewModel;
import com.freelearners.ibtha.viewmodels.MainViewModel;
import com.freelearners.ibtha.viewmodels.ProductViewModel;

public class MainActivity extends AppCompatActivity {

    private final int ID_HOME = 1;
    private final int ID_SEARCH = 2;
    private final int ID_CART = 3;
    private final int ID_ACCOUNT = 4;
    public MeowBottomNavigation bottomNavigation;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        FragmentContainerView fragmentContainerView = findViewById(R.id.fragmentContainerView);


        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        ProductViewModel productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        CartViewModel cartViewModel = ViewModelProviders.of(this).get(CartViewModel.class);

        productViewModel.makeApiCall(this);
        cartViewModel.makeApiCall(this);
        cartViewModel.getItemCount().observe(this, integer -> bottomNavigation.setCount(ID_CART, String.valueOf(integer)));


        TextView appName = findViewById(R.id.toolbar_title);
        appName.setText("Indian Black Tea");
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_SEARCH, R.drawable.ic_baseline_search_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_CART, R.drawable.ic_outline_shopping_cart_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_ACCOUNT, R.drawable.ic_account));

//        bottomNavigation.show(ID_HOME, true);
        replace(new ShopFragment());

        mainViewModel.getTabLive().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                bottomNavigation.show(integer, true);
                switch (integer) {
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
            }
        });

        bottomNavigation.setOnClickMenuListener(model -> {
            mainViewModel.setTab(model.getId());
            return null;
        });
    }

    private void replace(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.home_frame_layout, fragment);
        fragmentTransaction.commit();
    }
}