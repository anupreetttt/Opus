package com.example.opus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signup extends AppCompatActivity {
    TextView signup;
    TextView loginLink;
    Button button;
    EditText name;
    EditText email;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup = findViewById(R.id.textView2);
        button = findViewById(R.id.loginButton);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.loginpass);
        loginLink = findViewById(R.id.signupLink);

        loginLink.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(signup.this, LoginPage.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(signup.this, "Thank you", Toast.LENGTH_SHORT).show();
                openMainActivity();
            }
        });
    }
    public void openMainActivity(){
        Intent intent = new Intent(signup.this, MainActivity.class);
        startActivity(intent);
    }

}

