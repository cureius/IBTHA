package com.freelearners.ibtha.views.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.freelearners.ibtha.R;
import com.freelearners.ibtha.model.ProductModel;
import com.freelearners.ibtha.database.remote.server.Constants;
import com.freelearners.ibtha.database.remote.server.data.ServerClass;
import com.freelearners.ibtha.database.remote.server.data.ServerResponseCallback;
import com.freelearners.ibtha.views.ui.ProductActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static android.content.Context.MODE_PRIVATE;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> implements Filterable {

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
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.product, parent, false);

        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductAdapter.ViewHolder holder, int position) {
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
        holder.title.setText(products.get(position).getName());
        holder.unit.setText(R.string.unit_gm);
        holder.price.setText(Integer.toString(products.get(position).getPrice()));

        if (products.get(position).getProductPictures().get(0).getImg() != null) {
            String url = "";
            url = Constants.BASE_URL + "/public/" + products.get(position).getProductPictures().get(0).getImg();
            Log.d(TAG, "onBindViewHolder: " + url);

            Glide.with(context)
                    .load(url)
//                .apply(RequestOptions.centerCropTransform())
                    .into(holder.img);
        }

        holder.img.setOnClickListener(v -> {
            Log.d(TAG, "onBindViewHolder: " + "Item clicked " + products.get(position).toString());
            Intent intent = new Intent(context, ProductActivity.class);
            intent.putExtra("product", products.get(position));
            context.startActivity(intent);
        });
        holder.add.setOnClickListener(v -> {
            SharedPreferences getSharedPreferences = context.getSharedPreferences("identification", MODE_PRIVATE);
            String token = getSharedPreferences.getString("token", null);

            new ServerClass().sendPOSTRequestToServerWithHeader(context, jsonObject, Constants.BASE_URL + "/api/user/cart/addtocart", token, new ServerResponseCallback() {
                @Override
                public void onJSONResponse(JSONObject jsonObject) {
                    Toast.makeText(context, "added to cart" + jsonObject.toString(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onJSONArrayResponse(JSONArray jsonArray) {
                    Toast.makeText(context, "Item added to your cart", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onError(Exception e) {
                    Toast.makeText(context, "fail to add to cart", Toast.LENGTH_SHORT).show();
                }
            });

        });
    }

    @Override
    public int getItemCount() {
        if (this.products != null) {
            return products.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<ProductModel> filteredList = new ArrayList<>();
            if (constraint.toString().isEmpty()) {
                filteredList.addAll(products);
            } else {
                    for (int i = 0; i < products.size(); i++) {
                        if (products.get(i).getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            filteredList.add(products.get(i));
                        }
                    }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
//            products.clear();
            products.addAll((Collection<? extends ProductModel>) results.values);
            notifyDataSetChanged();
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView img;
        public TextView title;
        public TextView unit;
        public TextView price;
        public ImageButton add;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.product_title);
            img = itemView.findViewById(R.id.image);
            unit = itemView.findViewById(R.id.product_unit);
            price = itemView.findViewById(R.id.price);
            add = itemView.findViewById(R.id.add_button);
        }
    }
}
