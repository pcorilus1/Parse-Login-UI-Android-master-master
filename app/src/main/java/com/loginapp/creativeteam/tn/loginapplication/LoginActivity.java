package com.loginapp.creativeteam.tn.loginapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    EditText edEmail, edPassword;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        btn = findViewById(R.id.button);
        /*if(ParseUser.getCurrentUser()!=null){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }*/

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edEmail.getText().toString();
                String pass = edPassword.getText().toString();

                login(user,pass);
            }
        });

    }



    public void login2(View view) {
        if( TextUtils.isEmpty(edEmail.getText())){
            edEmail.setError( "Email is required!" );
        }else if( TextUtils.isEmpty(edPassword.getText())){
            edPassword.setError( "Password is required!" );
        }else{
            final ProgressDialog progress = new ProgressDialog(this);
            progress.setMessage("Loading ...");
            progress.show();
            ParseUser.logInInBackground(edEmail.getText().toString(), edPassword.getText().toString() , new LogInCallback() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    progress.dismiss();
                    if (parseUser != null) {
                        Toast.makeText(LoginActivity.this, "Welcome back!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        ParseUser.logOut();
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }

    private void login( String user,final String password) {

        ParseUser.logInInBackground(user, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.e("ERROR", "Issue w Login");
                    e.printStackTrace();
                    return;
                }
                //TODO:navigate to new activity if the sign in succeeds
                Log.d("WORKING", "It's working");
                //goMainActivity();
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    public void signup(View view) {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    public void forgotPassword(View view) {
        Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
        startActivity(intent);
    }


}
