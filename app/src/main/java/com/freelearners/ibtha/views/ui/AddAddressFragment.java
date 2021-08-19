package com.freelearners.ibtha.views.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.freelearners.ibtha.R;
import com.freelearners.ibtha.model.Address;

import org.jetbrains.annotations.NotNull;

public class AddAddressFragment extends Fragment {

    private EditText name, phone, houseNo, streetArea;
    private String pin = "0";
    private RadioGroup pinRadio;
    private Button submit;
    private ProgressBar progressBar;
    Address address;
    public AddAddressFragment() {
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
        return inflater.inflate(R.layout.fragment_add_address, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        TextView back = view.findViewById(R.id.toolbar_back_tv);
//        back.setOnClickListener(v -> requireActivity().onBackPressed());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setError(null);
                phone.setError(null);
                houseNo.setError(null);
                streetArea.setError(null);
                if(name.getText().toString().isEmpty()){
                    name.setError("Required");
                    return;
                }
                if(phone.getText().toString().isEmpty()){
                    phone.setError("Required");
                    return;
                }
                if(houseNo.getText().toString().isEmpty()){
                    houseNo.setError("Required");
                    return;
                }
                if(streetArea.getText().toString().isEmpty()){
                    streetArea.setError("Required");
                    return;
                }

                int checkedId = pinRadio.getCheckedRadioButtonId();
                if (checkedId == -1){
                    Toast.makeText(getContext(), "Please Select Pin-Code", Toast.LENGTH_SHORT).show();
                }else{
                    findRadioBtn(checkedId);
                }
                progressBar.setVisibility(View.VISIBLE);
                addAddress(name.getText().toString(), phone.getText().toString(), houseNo.getText().toString(), streetArea.getText().toString(), pin);

            }
        });



    }

    @SuppressLint("NonConstantResourceId")
    private void findRadioBtn(int checkedId) {
        switch(checkedId){
            case R.id.radioButton1:
                Toast.makeText(getContext(), "700049", Toast.LENGTH_SHORT).show();
                pin = "700049";
                break;

            case R.id.radioButton2:
                Toast.makeText(getContext(), "700050", Toast.LENGTH_SHORT).show();
                pin = "700050";
                break;

            case R.id.radioButton3:
                Toast.makeText(getContext(), "700060", Toast.LENGTH_SHORT).show();
                pin = "700060";
                break;
        }
    }

    private void addAddress(String name, String phone, String houseNo, String streetArea, String pin) {
        ((OrderActivity) requireActivity()).addUserAddress(name, phone, pin, streetArea, houseNo + ", " + streetArea, "Kolkata", "Jadavpore", phone, "home");

    }


    private void init(View view){

        name=view.findViewById(R.id.name);
        phone=view.findViewById(R.id.phone);
        houseNo=view.findViewById(R.id.house_number);
        streetArea=view.findViewById(R.id.street);
        pinRadio=view.findViewById(R.id.address_radio_group);
        submit=view.findViewById(R.id.address_submit);
        progressBar=view.findViewById(R.id.progressBar);

    }
}