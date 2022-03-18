package com.example.opus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {
    EditText email;
    EditText loginpass;
    TextView signupLink;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        signupLink = findViewById(R.id.signupLink);
        loginButton = findViewById(R.id.loginButton);
        loginpass = findViewById(R.id.loginpass);
        email = findViewById(R.id.email);

        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, signup.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginPage.this, "Logged in", Toast.LENGTH_SHORT).show();
                openMainActivity();
            }
        });
    }
    public void openMainActivity(){
        Intent intent = new Intent(LoginPage.this,MainActivity.class);
        startActivity(intent);
    }
}