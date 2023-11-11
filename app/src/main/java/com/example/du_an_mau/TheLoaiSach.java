package com.example.du_an_mau;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_mau.Adapter.LoaiSachAdapter;
import com.example.du_an_mau.DAO.LoaiSachDAO;
import com.example.du_an_mau.Models.LoaiSach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Locale;

public class TheLoaiSach extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<LoaiSach> list;
    private LoaiSachDAO loaiSachDAO;
    private EditText maLoai, tenLoaiSach;
    private Button btnLuu, btnHuy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai_sach);

        recyclerView = findViewById(R.id.recyclerViewTheVienSach);
        FloatingActionButton floadAdd = findViewById(R.id.floadAdd);

        floadAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTheLoaiSach();
            }
        });

        loaiSachDAO = new LoaiSachDAO(this);
        loadData();
    }
    private void loadData(){
        list = loaiSachDAO.getDSLoaiSach();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        LoaiSachAdapter loaiSachAdapter = new LoaiSachAdapter(this, list, loaiSachDAO);
        recyclerView.setAdapter(loaiSachAdapter);
    }
    private void showTheLoaiSach(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.the_loai_sach, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false); //Nguoi dung se khong the tat Dialog neu chon ra ben ngoai
        alertDialog.show();

        TextView tieuDe = view.findViewById(R.id.tieuDe);
//        maLoai = view.findViewById(R.id.maLoai);
        tenLoaiSach = view.findViewById(R.id.tenLoaiSach);
        btnLuu = view.findViewById(R.id.btnLuu);
        btnHuy = view.findViewById(R.id.btnHuy);

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String maLoaiStr = maLoai.getText().toString();
                String tenloai = tenLoaiSach.getText().toString();

//                if (maLoaiStr.isEmpty()) {
//                    Toast.makeText(TheLoaiSach.this, "Bạn chưa nhập mã loại sách", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                int maloai = Integer.parseInt(maLoaiStr);

                if (tenloai.equals("")){
                    Toast.makeText(TheLoaiSach.this, "Bạn chưa nhập tên loại sách", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean check = loaiSachDAO.themLoaiSach(tenloai);
                if (check){
//                    Snackbar.make(recyclerView, "Thêm thành công", Snackbar.LENGTH_SHORT).show();
                    Toast.makeText(TheLoaiSach.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                    alertDialog.dismiss();
                }else{
                    Toast.makeText(TheLoaiSach.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }
}