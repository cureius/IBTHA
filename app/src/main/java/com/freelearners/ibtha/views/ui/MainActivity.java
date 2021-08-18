package com.freelearners.ibtha.views.ui;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.freelearners.ibtha.R;
import com.freelearners.ibtha.viewmodels.CartViewModel;
import com.freelearners.ibtha.viewmodels.MainViewModel;
import com.freelearners.ibtha.viewmodels.ProductViewModel;

import java.lang.ref.WeakReference;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

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
        TextView back = findViewById(R.id.toolbar_back_tv);

        appName.setText("Indian Black Tea");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.super.onBackPressed();
            }
        });
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_SEARCH, R.drawable.ic_baseline_search_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_CART, R.drawable.ic_outline_shopping_cart_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_ACCOUNT, R.drawable.ic_account));

        bottomNavigation.show(mainViewModel.getTab(), true);
        switch (mainViewModel.getTab()) {
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

        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                startAsyncTask(model.getId());
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
            }
        });
    }

    public void startAsyncTask(int id){
        ExampleAsyncTask task = new ExampleAsyncTask(this);
        task.execute(id);
    }

    private static class ExampleAsyncTask extends AsyncTask<Integer, Integer, String> {

        private final WeakReference<MainActivity> activityWeakReference;
        ExampleAsyncTask(MainActivity activity){
            activityWeakReference = new WeakReference<MainActivity>(activity);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Integer... integers) {
            MainActivity activity = activityWeakReference.get();
            if(activity == null || activity.isFinishing()){
                return "Tab Can't be changed";
            }
            @SuppressLint("WrongThread") MainViewModel mainViewModel = ViewModelProviders.of(activity).get(MainViewModel.class);
            mainViewModel.setTab(integers[0]);
            return "Tab Changed";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {

        }

    }


    private void replace(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.home_frame_layout, fragment);
        fragmentTransaction.commit();
    }
}