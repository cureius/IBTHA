package com.freelearners.ibtha.views.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freelearners.ibtha.R;
import com.freelearners.ibtha.entity.User;
import com.freelearners.ibtha.viewmodels.UserViewModel;

import java.util.List;

public class ProfileFragment extends Fragment {

    private TextView name;
    private TextView email;

    public ProfileFragment() {
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        name = view.findViewById(R.id.profile_name);
        email = view.findViewById(R.id.profile_email);
        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllUser.observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(List<User> users) {
                name.setText(users.get(0).getFirstName()+" "+users.get(0).getLastName());
                email.setText(users.get(0).getEmail());
            }
        });
        return  view;
    }
}