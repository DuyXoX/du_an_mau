package com.example.du_an_mau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.du_an_mau.Adapter.LoaiSachAdapter;
import com.example.du_an_mau.DAO.LoaiSachDAO;
import com.example.du_an_mau.Models.LoaiSach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TheLoaiSach extends AppCompatActivity {
    private LoaiSachDAO loaiSachDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai_sach);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewThuVienSach);
        FloatingActionButton floatingActionButton = findViewById(R.id.floadAdd);

        loaiSachDAO = new LoaiSachDAO(this);
        ArrayList<LoaiSach> list = loaiSachDAO.getDSLoaiSach();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        LoaiSachAdapter loaiSachAdapter = new LoaiSachAdapter(this, list);
        recyclerView.setAdapter(loaiSachAdapter);
    }
}