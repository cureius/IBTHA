package com.freelearners.ibtha.views.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.freelearners.ibtha.R;
import com.freelearners.ibtha.model.CartItem;
import com.freelearners.ibtha.server.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {


    ArrayList<CartItem> cartItems;
    Context context;

    private static final String TAG = "CartItemAdapter";
    
    public CartItemAdapter(ArrayList<CartItem> cartItems, Context context) {
        this.cartItems = cartItems;
        this.context = context;
    }


    public void setCartItems(ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }


    @NonNull
    @NotNull
    @Override
    public CartItemAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.cart_item, parent, false);

        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.title.setText(cartItems.get(position).getProduct().getName());
        holder.quantity.setText(R.string.unit_gm);
        holder.price.setText(Integer.toString(cartItems.get(position).getProduct().getPrice()));
        holder.unit.setText(Integer.toString(cartItems.get(position).getQuantity()));

        String url;
        url = Constants.BASE_URL + "/public/" + cartItems.get(position).getProduct().getProductPictures().get(0).getImg();
        Log.d(TAG, "onBindViewHolder: " + url);

        Glide.with(context)
                .load(url)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        if (this.cartItems != null) {
            return cartItems.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView title;
        public TextView quantity;
        public TextView unit;
        public TextView price;
        public ImageButton add;
        public ImageButton subtract;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.product_name_cart);
            quantity = itemView.findViewById(R.id.product_quantity);
            img = itemView.findViewById(R.id.product_img);
            unit = itemView.findViewById(R.id.product_units);
            price = itemView.findViewById(R.id.product_price_cart);
            add = itemView.findViewById(R.id.plus_product_cart);
            subtract = itemView.findViewById(R.id.minus_product_cart);
        }
    }
}
