package com.freelearners.ibtha.views.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.freelearners.ibtha.R;
import com.freelearners.ibtha.model.CartItem;
import com.freelearners.ibtha.viewmodels.CartViewModel;
import com.freelearners.ibtha.views.adapter.CartItemAdapter;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class CartFragment extends Fragment {

    private TextView totalCalculate;
    private TextView totalPay;

    RecyclerView recyclerView;
    public CartItemAdapter cartitemAdapter;
    public ArrayList<CartItem> cartItemArrayList = new ArrayList<>();

    public CartFragment() {
//         Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CartViewModel cartViewModel = ViewModelProviders.of(requireActivity()).get(CartViewModel.class);

        totalCalculate = view.findViewById(R.id.total_item_cart);
        totalPay = view.findViewById(R.id.total_price_cart);
        Button buyNow = view.findViewById(R.id.buy_now_cart);

        cartitemAdapter = new CartItemAdapter(cartItemArrayList, getContext());
        recyclerView = view.findViewById(R.id.cart_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(cartitemAdapter);
        cartViewModel.makeApiCall(requireContext());
        cartViewModel.getItemCount().observe(getViewLifecycleOwner(), integer -> {
            totalCalculate.setText("Total(" + String.valueOf(integer) + ")");
            ((MainActivity) requireActivity()).bottomNavigation.setCount(3, String.valueOf(integer));

        });
        cartViewModel.getCartItemListObserver().observe(getViewLifecycleOwner(), cartItems -> {
            if (cartItems != null) {
                cartitemAdapter.setCartItems(cartItems);
            }
        });
        cartViewModel.getPayable().observe(getViewLifecycleOwner(), integer -> totalPay.setText(String.valueOf(integer)));

        buyNow.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), OrderActivity.class);
            startActivity(intent);
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    public void startAsyncTask(){
            ExampleAsyncTask task = new ExampleAsyncTask(getContext());
            task.execute(10);
    }

    private static class ExampleAsyncTask extends AsyncTask<Integer, Integer, String>{

        private final WeakReference<Context> contextWeakReference;
        ExampleAsyncTask(Context context){
            contextWeakReference = new WeakReference<Context>(context);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Integer... integers) {
            for (int i = 0; i < integers[0]; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Deletion Finished";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Context context = contextWeakReference.get();
            if(context == null){
                return;
            }
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }

    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            CartViewModel cartViewModel = ViewModelProviders.of(requireActivity()).get(CartViewModel.class);
            int position = viewHolder.getAdapterPosition();
            startAsyncTask();
            Snackbar.make(recyclerView, "Item Deleted", Snackbar.LENGTH_LONG)
                    .setAction("Undo", v -> {

                    }).show();
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//            new RecyclerViewSwipeDecorator(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
//                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.amaranth))
//                    .addBackgroundColor(ContextCompat.getColor(getActivity(), R.color.amaranth))
//                    .addActionIcon(R.drawable.ic_baseline_delete_24)
//                    .create()
//                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };
}