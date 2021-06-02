package com.freelearners.ibtha.identification;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
    private EditText email , phone , password , confirmPassword ;
    private Button createAccountBtn ,goLogInBtn;

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

        createAccountBtn.setOnClickListener(v -> {
            email.setError(null);
            phone.setError(null);
            password.setError(null);
            confirmPassword.setError(null);
            if(email.getText().toString().isEmpty()){
                email.setError("Required");
                return;
            }
            if(phone.getText().toString().isEmpty()){
                phone.setError("Required");
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
            if(phone.getText().toString().length() != 10){
                phone.setError("Please enter a valid phone number");
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

            createAccount(email , password , phone);
        });
    }

    private void createAccount(EditText email, EditText password, EditText phone) {

        String error = "error";
        Log.d(error, "createAccount: " + email.getText().toString() + password.getText().toString() + phone.getText().toString()  );
        ((IdentificationActivity) requireActivity()).SIGNUP(email.getText().toString() , phone.getText().toString() ,password.getText().toString());
    }

    private void init(View view){

        email=view.findViewById(R.id.email_sign_up);
        phone=view.findViewById(R.id.phone_sign_up);
        password=view.findViewById(R.id.password_sign_up);
        confirmPassword=view.findViewById(R.id.confirm_password_sign_up);
//        progressBar=view.findViewById(R.id.progressBar);
        createAccountBtn=view.findViewById(R.id.sign_up_button);
        goLogInBtn=view.findViewById(R.id.go_to_log_in_bt);

    }

}