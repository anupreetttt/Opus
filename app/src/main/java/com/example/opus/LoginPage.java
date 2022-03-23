package com.example.opus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private TextView signupLink;
    private Button loginButton;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        signupLink = findViewById(R.id.signupLink);
        loginButton = findViewById(R.id.loginButton);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);

        auth = FirebaseAuth.getInstance();

        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, SignupPage.class);
                startActivity(intent);
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getEmail = email.getText().toString();
                String getPassword = password.getText().toString();
                if (TextUtils.isEmpty(getEmail )|| TextUtils.isEmpty(getPassword)) {
                    Toast.makeText(LoginPage.this, "Empty credentials", Toast.LENGTH_SHORT).show();
                }
                else {
                    loginUser(getEmail, getPassword);
                }

            }
        });
    }

    private void loginUser(String email, String password) {

        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(LoginPage.this, "Logged in", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginPage.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}