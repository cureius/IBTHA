package com.freelearners.ibtha.views.adapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.freelearners.ibtha.database.remote.server.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProductImagesAdapter extends PagerAdapter {

    private List<String> productImages;
    private Context context;
    String url = null;


    public ProductImagesAdapter(List<String> productImages, Context context) {
        this.productImages = productImages;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {
        ImageView productImage = new ImageView(container.getContext());

        if (!productImages.get(position).isEmpty()) {
            String url;
            url = productImages.get(position);
            Log.d(TAG, "onBindViewHolder: " + url);
            byte[] decodedString = Base64.decode(url, Base64.DEFAULT);
            Glide.with(context)
                    .asBitmap()
                    .load(decodedString)
                    .centerCrop()
                    .into(productImage);
        }
        container.addView(productImage,0);
        return productImage;
    }

    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {

        container.removeView((ImageView)object);
    }

    @Override
    public int getCount() {
        return productImages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
        return view == object;
    }
}
