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

import com.example.du_an_mau.Adapter.PhieuMuonAdapter;
import com.example.du_an_mau.Adapter.SachAdapter;
import com.example.du_an_mau.DAO.PhieuMuonDAO;
import com.example.du_an_mau.DAO.ThuVienSachDAO;
import com.example.du_an_mau.Models.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PhieuMuon extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<com.example.du_an_mau.Models.PhieuMuon> list;
    private PhieuMuonDAO phieuMuonDAO;
    private EditText maPM, ngayMuon, ngayTra, tenUser;
    private Button btnLuu, btnHuy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phieu_muon);

        recyclerView = findViewById(R.id.recyclerViewPhieuMuon);
        FloatingActionButton floadAdd = findViewById(R.id.floadAdd);

        floadAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPhieuMuon();
            }
        });

        phieuMuonDAO = new PhieuMuonDAO(this);
        loadData();
    }

    private void showPhieuMuon(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.phieu_muon, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        alertDialog.show();

//        TextView tieuDe =  view.findViewById(R.id.tieuDe);
//        maPM = view.findViewById(R.id.maPM);
//        tenSach = view.findViewById(R.id.tenSach);
//        tenSach = view.findViewById(R.id.tenSach);
//        tenSach = view.findViewById(R.id.tenSach);
    }
    private void loadData(){
        list = phieuMuonDAO.getDSPhieuMuon();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        PhieuMuonAdapter phieuMuonAdapter = new PhieuMuonAdapter(this, list, phieuMuonDAO);
        recyclerView.setAdapter(phieuMuonAdapter);
    }
}