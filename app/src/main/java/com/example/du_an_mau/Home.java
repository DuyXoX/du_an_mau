package com.example.du_an_mau;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.du_an_mau.DAO.NguoiDungDAO;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
//    private ArrayList<NguoiDung> list;
//    private NguoiDungDAO nguoiDungDAO;
    private LinearLayout tickets, history, addBook, manageUser, manageBook, theloaiSach, bills, cart, setting;
//    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tickets = findViewById(R.id.tickets);
        history = findViewById(R.id.history);
        manageUser = findViewById(R.id.manageUser);
        manageBook = findViewById(R.id.manageBook);
        theloaiSach = findViewById(R.id.theloaiSach);
        bills = findViewById(R.id.bills);
        cart = findViewById(R.id.cart);
        setting = findViewById(R.id.setting);

        SharedPreferences sharedPreferences = getSharedPreferences("dataUser", MODE_PRIVATE);
        int role = sharedPreferences.getInt("role", -1);
        Log.d("Role User", "Quyen: " + role);
        switch (role){
            case 1://Admin
                break;
            case 2://Thu thu
                bills.setVisibility(View.GONE);
//                manageBook.setVisibility(View.GONE);
                break;
            case 3://Nguoi dung
                theloaiSach.setVisibility(View.GONE);
                bills.setVisibility(View.GONE);
                manageUser.setVisibility(View.GONE);
                manageBook.setVisibility(View.GONE);

                bills.setVisibility(View.GONE);
                break;
            default:
                break;
        }


        manageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, NguoiDung.class));
            }
        });
        tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, PhieuMuon.class));
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(Home.this, PhieuMuon.class));
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
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showDialogSetting();
            }
        });

    }
//    private void showDialogSetting(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
//        View view = layoutInflater.inflate(R.layout.setting_user, null);
//        builder.setView(view);
//
//        AlertDialog alertDialog = builder.create();
//        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
////        alertDialog.setCancelable(false);
//        alertDialog.show();
//
//        LinearLayout thongTinUser = view.findViewById(R.id.thongTinUser);
//        LinearLayout logout = view.findViewById(R.id.logout);
//
//        thongTinUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "Ahihi", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }
}