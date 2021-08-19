package com.freelearners.ibtha.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freelearners.ibtha.R;
import com.freelearners.ibtha.model.Address;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_ITEM = 2;

    ArrayList<Address> addresses;
    Context context;

    public AddressAdapter(ArrayList<Address> addresses, Context context) {
        this.addresses = addresses;
        this.context = context;
    }


    public void setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cart_item, parent, false);
        if (viewType == TYPE_ITEM) {
            //Inflating recycle view item layout
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.address, parent, false);
            return new AddressAdapter.ItemViewHolder(itemView);
        } else if (viewType == TYPE_FOOTER) {
            //Inflating footer view
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_footer, parent, false);
            return new AddressAdapter.FooterViewHolder(itemView);
        } else
            return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AddressAdapter.FooterViewHolder) {
            AddressAdapter.FooterViewHolder footerHolder = (AddressAdapter.FooterViewHolder) holder;
//            footerHolder.footerText.setText("Footer View");
        } else if (holder instanceof AddressAdapter.ItemViewHolder) {
            AddressAdapter.ItemViewHolder itemViewHolder = (AddressAdapter.ItemViewHolder) holder;

            itemViewHolder.name.setText(addresses.get(position).getName());
            itemViewHolder.mobileNumber.setText(addresses.get(position).getMobileNumber());
            itemViewHolder.address.setText(addresses.get(position).getAddress()+", "+addresses.get(position).getLandmark()+", "+addresses.get(position).getLocality()+", "+addresses.get(position).getCityDistrictTown()+", "+addresses.get(position).getAddressType());
            itemViewHolder.pin.setText(addresses.get(position).getPinCode());

        }
    }

    @Override
    public int getItemCount() {
        if (this.addresses != null) {
            return addresses.size() + 1;
        }
        return 0;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == addresses.size()) {
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
        public TextView name;
        public TextView mobileNumber;
        public TextView address;
        public TextView pin;

        public ItemViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_address);
            mobileNumber = itemView.findViewById(R.id.mobile_number);
            address = itemView.findViewById(R.id.address_full);
            pin = itemView.findViewById(R.id.pin_address);
        }
    }
}
