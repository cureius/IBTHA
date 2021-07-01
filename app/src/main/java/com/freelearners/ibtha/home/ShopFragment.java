package com.freelearners.ibtha.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.freelearners.ibtha.Constants;
import com.freelearners.ibtha.R;
import com.freelearners.ibtha.ServerClass;
import com.freelearners.ibtha.ServerResponseCallback;
import com.freelearners.ibtha.home.marketPlace.ProductAdapter;
import com.freelearners.ibtha.home.marketPlace.ProductModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShopFragment extends Fragment {
    public static final String TAG = ShopFragment.class.getName();
    public ProductAdapter productAdapter;
    public ArrayList<ProductModel> productModels = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.shop_recyclerview);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(productAdapter);


        new ServerClass().sendPOSTArrayRequestToServer(getContext(),
                Constants.BASE_URL + "/api/product/getProducts",
                new ServerResponseCallback() {
                    @Override
                    public void onJSONResponse(JSONObject jsonObject) {
                        Log.d(TAG, "onJSONResponse: " + jsonObject.toString());
                        Toast.makeText(getContext(), "Account Created Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onJSONArrayResponse(JSONArray jsonArray) throws JSONException {
                        Log.d(TAG, "onJSONResponse: MainActivity" + jsonArray.toString());
                        productModels.clear();
                        Toast.makeText(getContext(), "Successfully got Products " + jsonArray.length(), Toast.LENGTH_LONG).show();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            ProductModel product = new ProductModel();
                            try {
                                product.set_id(object.getString("_id"));
                                product.setName(object.getString("name"));
                                product.setSlug(object.getString("slug"));
                                product.setPrice(object.getInt("price"));
                                product.setDescription(object.getString("description"));
                                product.setProductPictures(object.getJSONArray("productPictures"));
                                product.setReviews(object.getJSONArray("reviews"));
                                product.setCategory(object.getString("category"));
                                product.setQuantity(object.getInt("quantity"));
                                product.setCreatedBy(object.getString("createdBy"));

                            } catch (JSONException e) {
                                Log.e(TAG, "onJSONArrayResponse: ", e);
                            }
                            productModels.add(product);
                        }
                        Toast.makeText(getContext(), "got " + Integer.toString(productModels.size()) + " Products", Toast.LENGTH_SHORT).show();
                        productAdapter = new ProductAdapter(productModels, getContext());
                        recyclerView.setAdapter(productAdapter);
                        productAdapter.notifyDataSetChanged();
                        Log.d(TAG, "onJSONArrayResponse: " + productModels.toString());
//                        for (int i = 0; i <productModels.size(); i++) {
//                            Toast.makeText(getContext(), (CharSequence) productModels.get(i).getProductPictures().toString(), Toast.LENGTH_SHORT).show();
//                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e(TAG, "onError: ", e);
                        Toast.makeText(getContext(), "try again", Toast.LENGTH_SHORT).show();
                    }
                });


        return view;
    }
}