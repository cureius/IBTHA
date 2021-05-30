package com.freelearners.ibtha.identification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.freelearners.ibtha.R;

import android.os.Bundle;
import android.widget.FrameLayout;

public class IdentificationActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);


        frameLayout = findViewById(R.id.frame_layout_identification);
        setFragment(new LogInFragment());
    }

    private void setFragment(LogInFragment logInFragment) {
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),logInFragment);
        fragmentTransaction.commit();
    }
}