package com.example.du_an_mau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_mau.DAO.NguoiDungDAO;
import com.example.du_an_mau.Database.DBHelper;

public class Login extends AppCompatActivity {
    private NguoiDungDAO nguoiDungDAO;
    private EditText usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nguoiDungDAO = new NguoiDungDAO(this);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        TextView buttonSignup = findViewById(R.id.buttonSignup);
        Button btnLogin = findViewById(R.id.btnLogin);

//        Intent intent = getIntent();
//        String data = intent.getStringExtra("user");
//        usernameEditText.setHint(data);
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignupButtonClick();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                boolean check = nguoiDungDAO.CheckUser(username, password);

                if (check) {
                    Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, Home.class); // Thay B.class bằng tên Activity B
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void onSignupButtonClick() {
        Intent intent = new Intent(Login.this, Signup.class); // Thay B.class bằng tên Activity B
        startActivity(intent);
    }
}