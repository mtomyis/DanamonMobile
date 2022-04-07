package com.example.danamonmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class SignIn extends AppCompatActivity {

    //Declaration EditTexts
    EditText editTextEmail;
    EditText editTextPassword;
    //Declaration Button
    Button buttonLogin;
    Button buttonSign;
    //Declaration SqliteHelper
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        sqliteHelper = new SqliteHelper(this);
        initViews();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check user input is correct or not
                if (validate()) {
                    //Get values from EditText fields
                    String Email = editTextEmail.getText().toString();
                    String Password = editTextPassword.getText().toString();

                    //Authenticate user
                    User currentUser = sqliteHelper.Authenticate(new User(null, null, Email, Password, null));
//                        Snackbar.make(buttonLogin, "role : "+currentUser.role, Snackbar.LENGTH_LONG).show();

//                    Check Authentication is successful or not
                    if (currentUser.role.equals("Admin")) {
                        Snackbar.make(buttonLogin, "Successfully Logged in as admin!", Snackbar.LENGTH_LONG).show();
                        //User Logged in Successfully Launch You home screen activity
                        Intent intent=new Intent(SignIn.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    if(currentUser.role.equals("User")){
                        Snackbar.make(buttonLogin, "Successfully Logged in as user!", Snackbar.LENGTH_LONG).show();
                        //User Logged in Successfully Launch You home screen activity
                        Intent intent=new Intent(SignIn.this,MainActivityUser.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        //User Logged in Failed
                        Snackbar.make(buttonLogin, "Failed to log in , data doesn't match", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
        buttonSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignIn.this,SignUp.class);
                startActivity(intent);
            }
        });
    }

    private boolean validate() {
        boolean valid = false;
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

        return valid;
    }

    private void initViews() {
        editTextEmail = (EditText) findViewById(R.id.id_em);
        editTextPassword = (EditText) findViewById(R.id.id_pass);
        buttonLogin = (Button) findViewById(R.id.btn_login);
        buttonSign = (Button) findViewById(R.id.btn_signup);
    }
}