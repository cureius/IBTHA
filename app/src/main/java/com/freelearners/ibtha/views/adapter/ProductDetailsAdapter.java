package com.freelearners.ibtha.views.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.freelearners.ibtha.views.ui.ProductDescriptionFragment;
import com.freelearners.ibtha.views.ui.ProductReviewFragment;

import org.jetbrains.annotations.NotNull;

public class ProductDetailsAdapter extends FragmentPagerAdapter {

    private  int totalTabs;
    private  String description;

    public ProductDetailsAdapter( FragmentManager fm, int totalTabs, String description) {
        super(fm);
        this.totalTabs = totalTabs;
        this.description = description;
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0 :
                return new ProductDescriptionFragment(description);
            case  1:
                return new ProductReviewFragment();
            default:
                return  null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
