package com.example.du_an_mau;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonGoToB = findViewById(R.id.buttonLogin);
        Button buttonRegister = findViewById(R.id.buttonRegister);
        Button btn_test = findViewById(R.id.btn_test);

        buttonGoToB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo Intent để chuyển đến màn hình B
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent); // Bắt đầu Activity B
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo Intent để chuyển đến màn hình B
                Intent intent = new Intent(MainActivity.this, Signup.class);
                startActivity(intent); // Bắt đầu Activity B
            }
        });

        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Test.class);
                startActivity(intent);
            }
        });

    }
}