package com.freelearners.ibtha.views.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.freelearners.ibtha.R;
import com.freelearners.ibtha.database.remote.server.Constants;
import com.freelearners.ibtha.database.remote.server.data.ServerClass;
import com.freelearners.ibtha.database.remote.server.data.ServerResponseCallback;
import com.freelearners.ibtha.model.ProductModel;
import com.freelearners.ibtha.repository.CartRepository;
import com.freelearners.ibtha.views.ui.ProductActivity;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_ITEM = 2;

    ArrayList<ProductModel> products;
    Context context;
    private static final String TAG = "ProductAdapter";

    public ProductAdapter(ArrayList<ProductModel> products, Context context) {
        this.products = products;
        this.context = context;

    }

    public void setProducts(ArrayList<ProductModel> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.product, parent, false);
        if (viewType == TYPE_ITEM) {
            //Inflating recycle view item layout
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product, parent, false);
            return new ProductAdapter.ItemViewHolder(itemView);
        } else if (viewType == TYPE_FOOTER) {
            //Inflating footer view
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_footer, parent, false);
            return new ProductAdapter.FooterViewHolder(itemView);
        } else
            return null;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ProductAdapter.FooterViewHolder) {
            ProductAdapter.FooterViewHolder footerHolder = (ProductAdapter.FooterViewHolder) holder;
//            footerHolder.footerText.setText("Footer View");
        } else if (holder instanceof ProductAdapter.ItemViewHolder) {
            ProductAdapter.ItemViewHolder itemViewHolder = (ProductAdapter.ItemViewHolder) holder;

            CartRepository cartRepository = new CartRepository();
            JSONObject jsonObject = new JSONObject();
            JSONObject cartItems = new JSONObject();
            try {
                cartItems.put("product", products.get(position).get_id());
                cartItems.put("quantity", 1);
                cartItems.put("price", products.get(position).getPrice());
            } catch (JSONException je) {
                Log.e(TAG, "postRequest: ", je);
                Toast.makeText(context, "JSON Exception", Toast.LENGTH_SHORT).show();
            }
            try {
                jsonObject.put("cartItems", cartItems);
            } catch (JSONException je) {
                Log.e(TAG, "postRequest: ", je);
                Toast.makeText(context, "JSON Exception", Toast.LENGTH_SHORT).show();
            }

            itemViewHolder.title.setText(products.get(position).getName());
            itemViewHolder.unit.setText(R.string.unit_gm);
            itemViewHolder.price.setText(Integer.toString(products.get(position).getPrice()));

            if (products.get(position).getProductPictures().get(0).getImg() != null) {
                String url;
                url = Constants.BASE_URL + "/public/" + products.get(position).getProductPictures().get(0).getImg();
                Log.d(TAG, "onBindViewHolder: " + url);

                Glide.with(context)
                        .load(url)
                        .centerCrop()
//                .apply(RequestOptions.centerCropTransform())
                        .into(itemViewHolder.img);
            }

            itemViewHolder.img.setOnClickListener(v -> {
                Log.d(TAG, "onBindViewHolder: " + "Item clicked " + products.get(position).toString());
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra("product", products.get(position));
                context.startActivity(intent);
            });
            itemViewHolder.add.setOnClickListener(v -> {
                cartRepository.addToCart(context, jsonObject);
            });
        }
    }

    @Override
    public int getItemCount() {
        if (this.products != null) {
            return products.size() + 2;
        }
        return 0;
    }


    @Override
    public int getItemViewType(int position) {
        if (position >= products.size()) {
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
        public TextView unit;
        public TextView price;
        public ImageButton add;

        public ItemViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.product_title);
            img = itemView.findViewById(R.id.image);
            unit = itemView.findViewById(R.id.product_unit);
            price = itemView.findViewById(R.id.price);
            add = itemView.findViewById(R.id.add_button);
        }
    }
}
