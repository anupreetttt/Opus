package com.example.opus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupPage extends AppCompatActivity {
    private TextView signup;
    private TextView loginLink;
    private Button signupButton;
    private EditText name;
    private EditText email;
    private EditText username;
    private EditText password;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup = findViewById(R.id.textView2);
        signupButton = findViewById(R.id.loginButton);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginLink = findViewById(R.id.signupLink);


        loginLink.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SignupPage.this, LoginPage.class);
                startActivity(intent);
                finish();
            }
        });

        auth = FirebaseAuth.getInstance();

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getEmail = email.getText().toString();
                String getPassword = password.getText().toString();
                String getUsername = username.getText().toString();
                String getName = name.getText().toString();

                if (TextUtils.isEmpty(getEmail )|| TextUtils.isEmpty(getPassword) || TextUtils.isEmpty(getName) || TextUtils.isEmpty(getUsername)) {
                    Toast.makeText(SignupPage.this, "Empty credentials", Toast.LENGTH_SHORT).show();
                } else if (getPassword.length() < 6){
                    Toast.makeText(SignupPage.this, "Password too short", Toast.LENGTH_SHORT).show();
                }
                else {
                    registerUser (getEmail, getPassword);
                }
            }
        });
    }

    private void registerUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignupPage.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(SignupPage.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(SignupPage.this, "Signed up successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SignupPage.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}

