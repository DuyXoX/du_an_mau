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
import com.example.du_an_mau.Adapter.NguoiDungAdapter;
import com.example.du_an_mau.DAO.NguoiDungDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NguoiDung extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<com.example.du_an_mau.Models.NguoiDung> list;
    private NguoiDungDAO nguoiDungDAO;
    private EditText maUser, tenUser, phoneNumer, diaChi, tenDN, password, role;
    private Button btnLuu, btnHuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);

        recyclerView = findViewById(R.id.recyclerViewNguoiDung);
        FloatingActionButton floadAdd = findViewById(R.id.floadAdd);

        floadAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showThonTinNguoiDung();
            }
        });
        nguoiDungDAO = new NguoiDungDAO(this);
        loadData();
    }
    private void showThonTinNguoiDung(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.nguoi_dung, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false); //Nguoi dung se khong the tat Dialog neu chon ra ben ngoai
        alertDialog.show();

        TextView tieuDe = view.findViewById(R.id.tieuDe);
        tenUser = view.findViewById(R.id.tenUser);
        phoneNumer = view.findViewById(R.id.phoneNumber);
        diaChi = view.findViewById(R.id.diaChi);
        tenDN = view.findViewById(R.id.tenDN);
        password = view.findViewById(R.id.password);
        role = view.findViewById(R.id.role);
        btnLuu = view.findViewById(R.id.btnLuu);
        btnHuy = view.findViewById(R.id.btnHuy);

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tennd = tenUser.getText().toString();
                String sdt = phoneNumer.getText().toString();
                String diachi = diaChi.getText().toString();
                String tendn = tenDN.getText().toString();
                String matkhau = password.getText().toString();
                String roleuser = role.getText().toString();

                if (tennd.equals("")){
                    Toast.makeText(NguoiDung.this, "Bạn chưa nhập tên người dùng", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (sdt.equals("")){
                    Toast.makeText(NguoiDung.this, "Bạn chưa nhập SĐT", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (diachi.equals("")){
                    Toast.makeText(NguoiDung.this, "Bạn chưa nhập địa chỉ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (tendn.equals("")){
                    Toast.makeText(NguoiDung.this, "Bạn chưa nhập tên đăng nhập", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (matkhau.equals("")){
                    Toast.makeText(NguoiDung.this, "Bạn chưa nhập mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (roleuser.equals("")){
                    Toast.makeText(NguoiDung.this, "Bạn chưa nhập quyền hạn", Toast.LENGTH_SHORT).show();
                    return;
                }

                int quyen;
                try {
                    quyen = Integer.parseInt(roleuser);

                    if (quyen > 3) {
                        Toast.makeText(NguoiDung.this, "Quyền hạn nhập sai định dạng", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(NguoiDung.this, "Vui lòng nhập một số nguyên cho quyền hạn", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean check = nguoiDungDAO.themUser(tennd, sdt, diachi, tendn, matkhau, quyen);
                if (check){
//                    Snackbar.make(recyclerView, "Thêm thành công", Snackbar.LENGTH_SHORT).show();
                    Toast.makeText(NguoiDung.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                    alertDialog.dismiss();
                }else{
                    Toast.makeText(NguoiDung.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
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
        list = nguoiDungDAO.getDSUser();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        NguoiDungAdapter nguoiDungAdapter = new NguoiDungAdapter(this, list, nguoiDungDAO);
        recyclerView.setAdapter(nguoiDungAdapter);
    }
}