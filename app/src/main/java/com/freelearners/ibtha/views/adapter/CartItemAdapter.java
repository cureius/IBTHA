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
import com.freelearners.ibtha.database.remote.server.Constants;
import com.freelearners.ibtha.model.CartItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CartItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_ITEM = 2;

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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cart_item, parent, false);
        if (viewType == TYPE_ITEM) {
            //Inflating recycle view item layout
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
            return new CartItemAdapter.ItemViewHolder(itemView);
        } else if (viewType == TYPE_FOOTER) {
            //Inflating footer view
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_footer, parent, false);
            return new CartItemAdapter.FooterViewHolder(itemView);
        } else
            return null;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CartItemAdapter.FooterViewHolder) {
            CartItemAdapter.FooterViewHolder footerHolder = (CartItemAdapter.FooterViewHolder) holder;
//            footerHolder.footerText.setText("Footer View");
        } else if (holder instanceof CartItemAdapter.ItemViewHolder) {
            CartItemAdapter.ItemViewHolder itemViewHolder = (CartItemAdapter.ItemViewHolder) holder;

            itemViewHolder.title.setText(cartItems.get(position).getProduct().getName());
            itemViewHolder.quantity.setText(R.string.unit_gm);
            itemViewHolder.price.setText(Integer.toString(cartItems.get(position).getProduct().getPrice()));
            itemViewHolder.unit.setText(Integer.toString(cartItems.get(position).getQuantity()));

            String url = "";
            url = Constants.BASE_URL + "/public/" + cartItems.get(position).getProduct().getProductPictures().get(0).getImg();
            Log.d(TAG, "onBindViewHolder: " + url);

            Glide.with(context)
                    .load(url)
                    .into(itemViewHolder.img);
        }
    }

    @Override
    public int getItemCount() {
        if (this.cartItems != null) {
            return cartItems.size() + 1;
        }
        return 0;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == cartItems.size()) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    private static class FooterViewHolder extends RecyclerView.ViewHolder {
        TextView footerText;

        public FooterViewHolder(View view) {
            super(view);
            footerText = (TextView) view.findViewById(R.id.footer_text);
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView title;
        public TextView quantity;
        public TextView unit;
        public TextView price;
        public ImageButton add;
        public ImageButton subtract;

        public ItemViewHolder(@NonNull @NotNull View itemView) {
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
