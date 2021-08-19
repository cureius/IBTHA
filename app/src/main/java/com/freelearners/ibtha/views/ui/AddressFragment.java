package com.freelearners.ibtha.views.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.freelearners.ibtha.R;
import com.freelearners.ibtha.model.Address;
import com.freelearners.ibtha.viewmodels.AddressViewModel;
import com.freelearners.ibtha.views.adapter.AddressAdapter;

import java.util.ArrayList;

public class AddressFragment extends Fragment {

    private RecyclerView addressRecyclerView;
    private Button addAddressBtn;
    public AddressAdapter addressAdapter;
    public ArrayList<Address> addressArrayList = new ArrayList<>();

    public AddressFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_address, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AddressViewModel addressViewModel = ViewModelProviders.of(requireActivity()).get(AddressViewModel.class);

        addressRecyclerView = view.findViewById(R.id.recyclerview_address);
        addAddressBtn = view.findViewById(R.id.add_address_btn);

        addressAdapter = new AddressAdapter(addressArrayList, getContext());
        addressRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        addressRecyclerView.setAdapter(addressAdapter);
        addressViewModel.makeApiCall(getContext());
        addressViewModel.getAddresses().observe(getViewLifecycleOwner(), addresses -> {
            if (addresses != null) {
                addressAdapter.setAddresses(addresses);
            }
        });
        addAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((OrderActivity) requireActivity()).setFragment(new AddAddressFragment());
            }
        });

    }
}