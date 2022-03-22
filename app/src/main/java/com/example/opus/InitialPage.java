package com.example.opus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InitialPage extends AppCompatActivity {

    private Button signup;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_page);

        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);

       signup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(InitialPage.this, SignupPage.class);
               startActivity(intent);
           }
       });

       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(InitialPage.this, LoginPage.class);
               startActivity(intent);
           }
       });
    }
}