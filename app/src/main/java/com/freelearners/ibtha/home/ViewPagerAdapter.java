package com.freelearners.ibtha.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return new ShopFragment();
            case 1:
                return new SearchFragment();
            case 2:
                return new NotificationFragment();
            case 3:
                return new ProfileFragment();

        }
//        return new HomeFragment();
        return new SearchFragment();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
