package com.freelearners.ibtha.home.marketPlace;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
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

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.bumptech.glide.Glide;
import com.freelearners.ibtha.Constants;
import com.freelearners.ibtha.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.util.ArrayList;


public class ProductAdapter  extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    ArrayList<ProductModel> products;
    Context context;

    private  final String TAG = ProductAdapter.class.getName();
    public ProductAdapter(ArrayList<ProductModel> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView =  layoutInflater.inflate(R.layout.product,parent, false);

        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductAdapter.ViewHolder holder, int position) {
        holder.title.setText(products.get(position).getName());
        holder.unit.setText(R.string.unit_gm);
        holder.price.setText(Integer.toString(products.get(position).getPrice()));
        String url = null;
        try {
            url = Constants.BASE_URL+"/public/"+products.get(position).getProductPictures().getJSONObject(0).getString("img");
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        Log.d(TAG, "onBindViewHolder: "+ url);

        Glide.with(context).load(url).into(holder.img);
//        ImageRequest request = new ImageRequest(url,
//                new Response.Listener<Bitmap>() {
//                    @Override
//                    public void onResponse(Bitmap response) {
//                        holder.img.setImageBitmap(response);
//                        Log.d(TAG, "onResponse: gotResponse" + response.toString());
//                    }
//                }, 100, 100, null,
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                });
//        holder.price.setText(products.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

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
