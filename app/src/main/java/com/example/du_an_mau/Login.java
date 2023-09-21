package com.example.du_an_mau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView buttonLogin = findViewById(R.id.buttonSignup);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignupButtonClick();
            }
        });
    }
    public void onSignupButtonClick() {
        Intent intent = new Intent(Login.this, Signup.class); // Thay B.class bằng tên Activity B
        startActivity(intent);
    }
}