package com.freelearners.ibtha.viewmodels;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.freelearners.ibtha.model.Cart;
import com.freelearners.ibtha.model.CartItem;
import com.freelearners.ibtha.database.remote.server.Constants;
import com.freelearners.ibtha.database.remote.server.data.ServerClass;
import com.freelearners.ibtha.database.remote.server.data.ServerResponseCallback;
import com.freelearners.ibtha.model.Item;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class CartViewModel extends ViewModel {
    private static final String TAG = "CartViewModel";
    private final MutableLiveData<ArrayList<CartItem>> cartItemList;
    private final MutableLiveData<Integer> count;
    private final MutableLiveData<Integer> totalPayable;
    private ArrayList<Item> itemList = new ArrayList<>();

    public CartViewModel() {
        count = new MutableLiveData<>();
        cartItemList = new MutableLiveData<>();
        totalPayable = new MutableLiveData<>();
    }

    public LiveData<ArrayList<CartItem>> getCartItemListObserver() {
        return cartItemList;
    }
    public LiveData<Integer> getItemCount() {
        return count;
    }
    public LiveData<Integer> getPayable() {
        return totalPayable;
    }

    public void makeApiCall(Context context) {
        SharedPreferences getSharedPreferences = context.getSharedPreferences("identification", MODE_PRIVATE);
        String token = getSharedPreferences.getString("token", null);

        new ServerClass().sendPOSTRequestToServerWithHeader(context,
                null,
                Constants.BASE_URL + "/api/user/getCartProducts",
                token,
                new ServerResponseCallback() {
                    @Override
                    public void onJSONResponse(JSONObject jsonObject) {
                        ArrayList<Item> items = new ArrayList<>();
                        Cart cart = new Gson().fromJson(String.valueOf(jsonObject), Cart.class);
                        Log.d(TAG, "onJSONResponse: " + cart.toString());
                        cartItemList.postValue(cart.getCartItems());
                        count.postValue(cart.getCartItems().size());
                        int price = 0;
                        for (int i = 0; i < cart.getCartItems().size(); i++) {
                            Item item = new Item();
//                            item.setProductId(cart.getCartItems().get(i).getProduct().get_id() );
//                            item.setPurchasedQty(cart.getCartItems().get(i).getProduct().getQuantity() );
//                            item.setPayablePrice(cart.getCartItems().get(i).getProduct().getPrice()*cart.getCartItems().get(i).getProduct().getQuantity());
//                            items.add(item);
                            price  = price + cart.getCartItems().get(i).getProduct().getPrice() * cart.getCartItems().get(i).getQuantity();
                        }
                        itemList = items;
                        totalPayable.postValue(price);

                    }

                    @Override
                    public void onJSONArrayResponse(JSONArray jsonArray) {

                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e(TAG, "onError: ", e);
                        Toast.makeText(context, "fail to get cart", Toast.LENGTH_SHORT).show();
                        cartItemList.postValue(null);

                    }
                });
    }
    public void addItem(Context context, JSONObject cartItem){
        SharedPreferences getSharedPreferences = context.getSharedPreferences("identification", MODE_PRIVATE);
        String token = getSharedPreferences.getString("token", null);

        new ServerClass().sendPOSTRequestToServerWithHeader(context, cartItem, Constants.BASE_URL + "/api/user/cart/addtocart", token, new ServerResponseCallback() {
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
    }
    public void deleteItem(int position) throws InterruptedException {
//        for (int i = 0; i < 10; i++) {
//            Thread.sleep(1000);
//            System.out.println(i);
//        }
//        ArrayList<CartItem> cart;
//        cart = cartItemList.getValue();
//        assert cart != null;
//        cart.remove(position);
//        cartItemList.postValue(cart);
    }
    public ArrayList<Item> convertIntoItems(){
        ArrayList<CartItem> cartItems = cartItemList.getValue();
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < Objects.requireNonNull(cartItems).size(); i++) {
            Item item = new Item();
            CartItem cartItem = new CartItem();
            cartItem = cartItems.get(i);
            item.setProductId(cartItem.get_id());
            item.setPurchasedQty(cartItem.getQuantity());
            item.setPayablePrice(cartItem.getProduct().getPrice());

            items.add(item);
        }
        return items;
    }

}
