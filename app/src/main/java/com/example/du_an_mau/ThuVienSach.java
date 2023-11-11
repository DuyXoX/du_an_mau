package com.example.du_an_mau;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_mau.Adapter.LoaiSachAdapter;
import com.example.du_an_mau.Adapter.SachAdapter;
import com.example.du_an_mau.DAO.LoaiSachDAO;
import com.example.du_an_mau.DAO.ThuVienSachDAO;
import com.example.du_an_mau.Models.LoaiSach;
import com.example.du_an_mau.Models.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ThuVienSach extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Sach> list;
    private ThuVienSachDAO thuVienSachDAO;
    private EditText maSach, tenSach, tenTacGia, giaBan, tenLoai;
    private Button btnLuu, btnHuy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thu_vien_sach);

        recyclerView = findViewById(R.id.recyclerViewThuVienSach);
        FloatingActionButton floadAdd = findViewById(R.id.floadAdd);

        floadAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSach();
            }
        });

        thuVienSachDAO = new ThuVienSachDAO(this);
        loadData();
    }
    private void showSach(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.sach, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        alertDialog.show();

        TextView tieuDe =  view.findViewById(R.id.tieuDe);
        tenSach = view.findViewById(R.id.tenSach);
        tenTacGia = view.findViewById(R.id.tenTacGia);
        giaBan = view.findViewById(R.id.giaBan);
        tenLoai = view.findViewById(R.id.tenLoai);
        btnLuu = view.findViewById(R.id.btnLuu);
        btnHuy = view.findViewById(R.id.btnHuy);
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tensach = tenSach.getText().toString();
                String tentacgia = tenTacGia.getText().toString();
                String giabanStr = giaBan.getText().toString();
                String tenloai = tenLoai.getText().toString();

                if (tensach.equals("")){
                    Toast.makeText(ThuVienSach.this, "Bạn chưa nhập tên sách", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (tentacgia.equals("")){
                    Toast.makeText(ThuVienSach.this, "Bạn chưa nhập tên tác giả", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (giabanStr.isEmpty()){
                    Toast.makeText(ThuVienSach.this, "Bạn chưa nhập giá bán", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (tenloai.isEmpty()){
                    Toast.makeText(ThuVienSach.this, "Bạn chưa nhập mã loại", Toast.LENGTH_SHORT).show();
                    return;
                }
                int giaban = Integer.parseInt(giabanStr);

                boolean checkSach = thuVienSachDAO.checkSach(tenloai);
                if (!checkSach){
                    Toast.makeText(ThuVienSach.this, "Tên loại sách này không tồn tại", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean check = thuVienSachDAO.themSach(tensach, tentacgia, giaban, tenloai);
                if (check){
                    Toast.makeText(ThuVienSach.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(ThuVienSach.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
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
    private void loadData(){
        list = thuVienSachDAO.getDSSach();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        SachAdapter sachAdapter = new SachAdapter(this, list, thuVienSachDAO);
        recyclerView.setAdapter(sachAdapter);
    }
}