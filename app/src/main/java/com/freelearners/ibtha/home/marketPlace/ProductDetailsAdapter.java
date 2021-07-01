package com.freelearners.ibtha.home.marketPlace;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

public class ProductDetailsAdapter extends FragmentPagerAdapter {

    private  int totalTabs;

    public ProductDetailsAdapter( FragmentManager fm, int totalTabs) {
        super(fm);
        this.totalTabs = totalTabs;
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0 :
                return new ProductDescriptionFragment();
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
