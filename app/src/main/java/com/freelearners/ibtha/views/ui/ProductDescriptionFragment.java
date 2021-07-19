package com.freelearners.ibtha.views.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freelearners.ibtha.R;

import org.jetbrains.annotations.NotNull;

public class ProductDescriptionFragment extends Fragment {

    private TextView description;
    private String descriptions;
    public ProductDescriptionFragment() {
        // Required empty public constructor
    }

    public ProductDescriptionFragment(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_product_description, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        description = view.findViewById(R.id.product_description);
        description.setText(descriptions);

    }
}