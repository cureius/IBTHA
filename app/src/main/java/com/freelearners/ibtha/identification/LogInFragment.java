package com.freelearners.ibtha.identification;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.freelearners.ibtha.R;

import java.util.regex.Pattern;

public class LogInFragment extends Fragment {

    public LogInFragment() {
        // Required empty public constructor
    }
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private EditText email , password  ;
    private Button logInBtn, logInByFaceBook, logInByGoogle;
    private LinearLayout goSignup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

        goSignup.setOnClickListener(v -> ((IdentificationActivity) requireActivity()).setFragment(new SignUpFragment()));

        logInByFaceBook.setOnClickListener(v -> Toast.makeText(getContext(), "Log in with Facebook", Toast.LENGTH_SHORT).show());
        logInByGoogle.setOnClickListener(v -> Toast.makeText(getContext(), "Log in with Google", Toast.LENGTH_SHORT).show());

        logInBtn.setOnClickListener(v -> {

            email.setError(null);
            password.setError(null);
            if(email.getText().toString().isEmpty()){
                email.setError("Required");
                return;
            }
            if(password.getText().toString().isEmpty()){
                password.setError("Required");
                return;
            }
            if(!VALID_EMAIL_ADDRESS_REGEX.matcher(email.getText().toString()).find()){
                email.setError("Please enter a valid Email");
                return;
            }
            if(password.getText().toString().length() < 6){
                password.setError("Please enter a password longer than 6 character ");
                return;
            }
            logIn(email , password);
        });
    }

    private void logIn(EditText email, EditText password) {
        ((IdentificationActivity) requireActivity()).loginRequest(email.getText().toString() , password.getText().toString(), "/api/signin");
    }

    private void init(View view){

        email=view.findViewById(R.id.email_log_in);
        password=view.findViewById(R.id.password_log_in);
//        progressBar=view.findViewById(R.id.progressBar);
        goSignup=view.findViewById(R.id.go_signup_tv_view);
        logInBtn=view.findViewById(R.id.log_in);
        logInByFaceBook=view.findViewById(R.id.login_facebook_btn);
        logInByGoogle=view.findViewById(R.id.login_google_btn);

    }
}