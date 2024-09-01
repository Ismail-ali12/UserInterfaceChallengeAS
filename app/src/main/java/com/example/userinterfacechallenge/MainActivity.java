package com.example.userinterfacechallenge;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.time.Duration;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private CoordinatorLayout coordinateLayout;
    private Button btnPickImage, btnRegister;

    private TextView nameWarn, emailWarn, passwordWarn, rePasswordWarn;

    private EditText name, email, password, rePassword;

    private CheckBox licenseCheckBox;

    private RadioGroup genderSelect;


    private Spinner countrySelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();

        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Yet to talk about", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRegister();
            }
        });
    }

    private void initRegister(){
        Log.d(TAG, "initRegister: started");

        if(validateData()){
            if(licenseCheckBox.isChecked()){
                showSnackBar();
            }else{
                Toast.makeText(this, "You must agree to license agreement", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showSnackBar(){
        Log.d(TAG, "showSnackBar: started");
        nameWarn.setVisibility(View.GONE);
        emailWarn.setVisibility(View.GONE);
        passwordWarn.setVisibility(View.GONE);
        rePasswordWarn.setVisibility(View.GONE);

        String userName = name.getText().toString();
        String userEmail = name.getText().toString();
        String userCountry = countrySelect.getSelectedItem().toString();
        String userGender = "";
        if(genderSelect.getId() == R.id.maleRB){
            userGender = "Male";
        }else if(genderSelect.getId() == R.id.femaleRB){
            userGender = "Female";
        }else if(genderSelect.getId() == R.id.otherRB){
            userGender = "Other";
        }

        String snackText =
                "Name: " + userName + "\n" +
                "Email: " + userEmail + "\n" +
                "Gender: " + userGender + "\n" +
                "Country: " + userCountry + "\n";


        Snackbar.make(coordinateLayout, snackText, Snackbar.LENGTH_INDEFINITE)
                .setAction("Next", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        name.setText("");
                        email.setText("");
                        password.setText("");
                        rePassword.setText("");

                    }
                }).show();
    }

    private boolean validateData(){
        boolean Entered = true;
        Log.d(TAG, "validateData: started");
        if(name.getText().toString().equals("")){
            nameWarn.setVisibility(View.VISIBLE);
            nameWarn.setText("Name not entered");
            Entered = false;
        }
        if(email.getText().toString().equals("")){
            emailWarn.setVisibility(View.VISIBLE);
            emailWarn.setText("Email not entered");
            Entered =  false;
        }
        if(password.getText().toString().equals("")){
            passwordWarn.setVisibility(View.VISIBLE);
            passwordWarn.setText("Enter a password");
            Entered =  false;
        }
        if(rePassword.getText().toString().equals("")){
            rePasswordWarn.setVisibility(View.VISIBLE);
            rePasswordWarn.setText("Confirm password");
            Entered =  false;
        }
        if(!rePassword.getText().toString().equals(password.getText().toString())){
            rePasswordWarn.setVisibility(View.VISIBLE);
            rePasswordWarn.setText("Passwords do not match");
            Entered = false;
        }
        return Entered;
    }
    private void initView(){
        Log.d(TAG, "initView: started");

        coordinateLayout = findViewById(R.id.coordinator);
        btnPickImage = findViewById(R.id.pickImageButton);
        btnRegister = findViewById(R.id.register);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        rePassword = findViewById(R.id.passwordRe);

        nameWarn = findViewById(R.id.nameWarn);
        emailWarn = findViewById(R.id.emailWarn);
        passwordWarn = findViewById(R.id.passwordWarn);
        rePasswordWarn = findViewById(R.id.rePasswordWarn);

        licenseCheckBox = findViewById(R.id.licenseAgreementCheckBox);
        genderSelect = findViewById(R.id.rgGender);
        countrySelect = findViewById(R.id.countrySpinner);
    }
}