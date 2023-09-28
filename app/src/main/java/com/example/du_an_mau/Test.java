package com.example.du_an_mau;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;

public class Test extends AppCompatActivity {
    private EditText test;
    private RadioButton radio_1, radio_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

// Gan gia tri vao id username o dinh dang EditText        username.setHint("");
// Gan gia tri vao id username o dinh dang Button        username.setText("");

// lay du lieu cua EditText chuyen vao thong bao      String data = username.getText().toString();
// hien thi thong bao        Toast.makeText(Signup.this, data, Toast.LENGTH_SHORT).show();
// chuyen doi EditText thanh So       int number = Integer.parseInt(data);
// hien thi ket qua ra TextView       String ketqua = String.valueOf(number);

// Nhan gia tri data sau khi Put ben mot gia dien khac       Intent intent = getIntent();
//        String data = intent.getStringExtra("user");

        test = findViewById(R.id.test);
        radio_1 = findViewById(R.id.radio_1);
        radio_2 = findViewById(R.id.radio_2);
        Button moWeb, btn_test, goiDien, guiTinnhan, truyenDUlieu;
        moWeb = findViewById(R.id.moWeb);
        btn_test = findViewById(R.id.btn_test);
        goiDien = findViewById(R.id.goiDien);
        guiTinnhan = findViewById(R.id.guiTinnhan);
        truyenDUlieu = findViewById(R.id.truyenDulieu);

        truyenDUlieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gioiTinh = "";
                if (radio_1.isChecked()) {
                    gioiTinh += radio_1.getText().toString();
                }
                if (radio_2.isChecked()) {
                    gioiTinh += radio_2.getText().toString();
                }
                String data = test.getText().toString();
                Intent intent = new Intent(Test.this, Signup.class);
                Bundle bundle = new Bundle();
                bundle.putString("test", data);
                bundle.putString("gioiTinh", gioiTinh);
                intent.putExtras(bundle);

//                startActivity(intent);
                myLauncher.launch(intent);
            }
        });
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = "";
                if (radio_1.isChecked()) {
                    data += radio_1.getText().toString();
                }
                if (radio_2.isChecked()) {
                    data += radio_2.getText().toString();
                }
//                test.setHint(data);
                test.setText(data);

//                String data = test.getText().toString();

//                Toast.makeText(Test.this, data , Toast.LENGTH_SHORT).show();
            }
        });

        moWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://m.youtube.com/"));
                startActivity(intent);
            }
        });

        goiDien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:098765421"));
                startActivity(intent);
            }
        });

        guiTinnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:098765421"));
                intent.putExtra("sms_body", "Gui tin nhan thanh cong");
                startActivity(intent);
            }
        });
    }
    private ActivityResultLauncher<Intent> myLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == 1) {
                    Intent intent = result.getData();
                    String user = intent.toString();
                    test.setHint(user);
                }
            }
        });
}