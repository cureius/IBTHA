package com.freelearners.ibtha.identification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.freelearners.ibtha.R;

import java.util.regex.Pattern;

public class SignUpFragment extends Fragment {

    public SignUpFragment() {
        // Required empty public constructor
    }
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private EditText name, email, password , confirmPassword ;
    private Button createAccountBtn, signupByFacebook, signupByGoogle ;
    private LinearLayout goLogInBtn ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);

        goLogInBtn.setOnClickListener(v -> ((IdentificationActivity) requireActivity()).setFragment(new LogInFragment()));


        signupByFacebook.setOnClickListener(v -> Toast.makeText(getContext(), "Log in with Facebook", Toast.LENGTH_SHORT).show());
        signupByGoogle.setOnClickListener(v -> Toast.makeText(getContext(), "Log in with Google", Toast.LENGTH_SHORT).show());

        createAccountBtn.setOnClickListener(v -> {
            name.setError(null);
//            lastName.setError(null);
            email.setError(null);
            password.setError(null);
            confirmPassword.setError(null);
            if(name.getText().toString().isEmpty()){
                name.setError("Required");
                return;
            }
            if(email.getText().toString().isEmpty()){
                email.setError("Required");
                return;
            }
            if(password.getText().toString().isEmpty()){
                password.setError("Required");
                return;
            }
            if(confirmPassword.getText().toString().isEmpty()){
                confirmPassword.setError("Required");
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
            if(!password.getText().toString().equals(confirmPassword.getText().toString())){
                confirmPassword.setError("Password mismatched");
                return;
            }

            createAccount(name.getText().toString(), email.getText().toString(), password.getText().toString());
        });
    }

    private void createAccount(String name, String email, String password) {
        ((IdentificationActivity) requireActivity()).SIGNUP(name, email, password);
    }

    private void init(View view){

        name=view.findViewById(R.id.name_sign_up);
        email=view.findViewById(R.id.email_sign_up);
        password=view.findViewById(R.id.password_sign_up);
        confirmPassword=view.findViewById(R.id.confirm_password_sign_up);
//        progressBar=view.findViewById(R.id.progressBar);
        createAccountBtn=view.findViewById(R.id.sign_up);
        goLogInBtn=view.findViewById(R.id.go_login);
        signupByFacebook=view.findViewById(R.id.signup_facebook_btn);
        signupByGoogle=view.findViewById(R.id.signup_google_btn);

    }

}