package com.freelearners.ibtha.views.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.freelearners.ibtha.R;
import com.freelearners.ibtha.views.adapter.ProductDetailsAdapter;
import com.freelearners.ibtha.views.adapter.ProductImagesAdapter;
import com.freelearners.ibtha.model.ProductModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    private ViewPager productImageViewpager;
    private TabLayout viewpagerIndicator;
    private ViewPager productDetailsViewpager;
    private TabLayout productDetailsTabLayout;
    private TextView productName, productPrice, units, productCategory, productDescription;
    private ImageButton plus, minus;
    private final String TAG = ProductActivity.class.getName();
    private int unit = 1;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        productImageViewpager = findViewById(R.id.product_images_viewpager);
        viewpagerIndicator = findViewById(R.id.viewpager_indicator);
        productDetailsViewpager = findViewById(R.id.product_details_viewpager);
        productDetailsTabLayout = findViewById(R.id.product_details_tabLayout);
        productName = findViewById(R.id.product_name);
        productPrice = findViewById(R.id.product_price);
        units = findViewById(R.id.selected_units);
        productCategory = findViewById(R.id.toolbar_title);
        productDescription = findViewById(R.id.product_description);
        plus = findViewById(R.id.unitPlusBtn);
        minus = findViewById(R.id.unitMinusBtn);

        ProductModel product = getIntent().getParcelableExtra("product");

        List<String> productImages = new ArrayList<>();
        for (int i = 0; i < product.getProductPictures().size(); i++) {
            productImages.add(product.getProductPictures().get(i).getImg());
        }

        ProductImagesAdapter imagesAdapter = new ProductImagesAdapter(productImages, getApplicationContext());
        productImageViewpager.setAdapter(imagesAdapter);

        viewpagerIndicator.setupWithViewPager(productImageViewpager);

        productName.setText(product.getName());
        productPrice.setText(Integer.toString(product.getPrice()));
        productCategory.setText(product.getCategory().getName());

        productDetailsViewpager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(), productDetailsTabLayout.getTabCount(), product.getDescription()));
        productDetailsViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTabLayout));
        productDetailsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDetailsViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        plus.setOnClickListener(v -> units.setText(Integer.toString(++unit)));

        minus.setOnClickListener(v -> {
            if (unit == 1) return;
            units.setText(Integer.toString(--unit));

        });


    }
}