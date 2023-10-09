package com.example.du_an_mau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Home extends AppCompatActivity {
    private LinearLayout addUser, addBook, manageUser, manageBook, theloaiSach, bills, cart, setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addUser = findViewById(R.id.addUser);
        addBook = findViewById(R.id.addBook);
        manageUser = findViewById(R.id.manageUser);
        manageBook = findViewById(R.id.manageBook);
        theloaiSach = findViewById(R.id.theloaiSach);
        bills = findViewById(R.id.bills);
        cart = findViewById(R.id.cart);
        setting = findViewById(R.id.setting);

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(Home.this, .class));
            }
        });
        manageBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, ThuVienSach.class));
            }
        });
        theloaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, TheLoaiSach.class));
            }
        });
    }
}