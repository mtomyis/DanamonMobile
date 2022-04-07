package com.example.danamonmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class SignUp extends AppCompatActivity {

    //Declaration EditTexts
    EditText editTextUserName;
    EditText editTextEmail;
    EditText editTextPassword;
    RadioGroup radioGroup;
    RadioButton role;

    //Declaration Button
    Button buttonRegister;
    Button buttonBack;

    //Declaration SqliteHelper
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        sqliteHelper = new SqliteHelper(this);
        initViews();

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String UserName = editTextUserName.getText().toString();
                    String Email = editTextEmail.getText().toString();
                    String Password = editTextPassword.getText().toString();
                    String roles = role.getText().toString();

                    if (!sqliteHelper.isEmailExists(Email)) {

                        //Email does not exist now add new user to database
                        sqliteHelper.addUser(new User(null, UserName, Email, Password,roles));
                        Snackbar.make(buttonRegister, "User created successfully! Please Login ", Snackbar.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, Snackbar.LENGTH_LONG);
                    }else {

                        //Email exists with email input provided so show error user already exist
                        Snackbar.make(buttonRegister, "User already exists with same email ", Snackbar.LENGTH_LONG).show();
                    }

                }
            }
        });
    }

    private boolean validate() {
        boolean valid = false;
        //Get values from EditText fields
        String UserName = editTextUserName.getText().toString();
        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();
        String roles = role.getText().toString();

        if (editTextEmail.getText().toString().matches("")) {
            valid = false;
            Toast.makeText(this, "You did not enter a email", Toast.LENGTH_SHORT).show();
        }else{
            valid = true;
        }
        if (editTextPassword.getText().toString().matches("")) {
            valid = false;
            Toast.makeText(this, "You did not enter a password", Toast.LENGTH_SHORT).show();
        }else{
            valid = true;
        }

        //Handling validation for UserName field
        if (UserName.isEmpty()) {
            valid = false;
            Toast.makeText(this, "You did not enter a username", Toast.LENGTH_SHORT).show();
        } else {
            valid = true;
        }

        if (roles.isEmpty()) {
            valid = false;
            Toast.makeText(this, "You did not enter a role", Toast.LENGTH_SHORT).show();
        } else {
            valid = true;
        }

        return valid;
    }

    private void initViews() {
        editTextEmail = (EditText) findViewById(R.id.id_email);
        editTextPassword = (EditText) findViewById(R.id.id_password);
        editTextUserName = (EditText) findViewById(R.id.id_username);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        role = (RadioButton) findViewById(selectedId);
        buttonBack = (Button) findViewById(R.id.btn_back);
        buttonRegister = (Button) findViewById(R.id.btn_signupp);

    }
}