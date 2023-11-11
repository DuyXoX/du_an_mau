package com.example.du_an_mau.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_mau.DAO.NguoiDungDAO;
import com.example.du_an_mau.Models.NguoiDung;
import com.example.du_an_mau.R;

import java.util.ArrayList;

public class NguoiDungAdapter extends RecyclerView.Adapter<NguoiDungAdapter.ViewHolder> {
    private Context context;
    private ArrayList<NguoiDung> list;
    private NguoiDungDAO nguoiDungDAO;

    public NguoiDungAdapter(Context context, ArrayList<NguoiDung> list, NguoiDungDAO nguoiDungDAO) {
        this.context = context;
        this.list = list;
        this.nguoiDungDAO = nguoiDungDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_nguoi_dung, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Truyền dữ liệu vào
        holder.maUser.setText("Mã: " + list.get(position).getMand());
        holder.tenUser.setText("Tên: " + list.get(position).getTennd());
        holder.phoneNumber.setText("SĐT: " + list.get(position).getSdt());
        holder.diaChi.setText("Địa chỉ: " + list.get(position).getDiachi());
        holder.tenDN.setText("Tên đăng nhập: " + list.get(position).getTendangnhap());
        holder.role.setText("Quyên hạn: " + list.get(position).getRole());

        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDiaLogUpdate(list.get(holder.getAdapterPosition()));
            }
        });
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xóa người dùng có tên " + list.get(holder.getAdapterPosition()).getTennd() + " hay không?");
                builder.setIcon(R.drawable.ic_warning);

                builder.setPositiveButton("Vâng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int check = nguoiDungDAO.xoaUser(list.get(holder.getAdapterPosition()).getMand());
                        if (check == -1) {
                            // Xóa thất bại.
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        } else {
                            // Xóa thành công.
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            loadData();
                        }
                    }
                });
                builder.setNegativeButton("Hủy", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageButton btn_edit, btn_delete;
        private TextView maUser, tenUser, phoneNumber, diaChi, tenDN, role;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btn_edit = itemView.findViewById(R.id.btn_edit);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            maUser = itemView.findViewById(R.id.maUser);
            tenUser = itemView.findViewById(R.id.tenUser);
            phoneNumber = itemView.findViewById(R.id.phoneNumber);
            diaChi = itemView.findViewById(R.id.diaChi);
            tenDN = itemView.findViewById(R.id.tenDN);
            role = itemView.findViewById(R.id.role);
        }
    }
    private void showDiaLogUpdate(NguoiDung nguoiDung){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.nguoi_dung, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        alertDialog.show();

        TextView tieuDe = view.findViewById(R.id.tieuDe);
        EditText tenND, SDT, diaChi, tenDN, matKhau, role;

        tenND = view.findViewById(R.id.tenUser);
        SDT = view.findViewById(R.id.phoneNumber);
        diaChi = view.findViewById(R.id.diaChi);
        tenDN = view.findViewById(R.id.tenDN);
        matKhau = view.findViewById(R.id.password);
        role = view.findViewById(R.id.role);
        Button btnLuu, btnHuy;
        btnLuu = view.findViewById(R.id.btnLuu);
        btnHuy = view.findViewById(R.id.btnHuy);

        tieuDe.setText("Cập nhật Người dùng");
        btnLuu.setText("Cập nhật");

        tenND.setText(nguoiDung.getTennd());
        SDT.setText(nguoiDung.getSdt());
        diaChi.setText(nguoiDung.getDiachi());
        tenDN.setText(nguoiDung.getTendangnhap());
        matKhau.setText(nguoiDung.getMatkhau());
        role.setText(String.valueOf(nguoiDung.getRole()));

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tennd = tenND.getText().toString();
                String sdt = SDT.getText().toString();
                String diachi = diaChi.getText().toString();
                String tendn = tenDN.getText().toString();
                String matkhau = matKhau.getText().toString();
                String roleuser = role.getText().toString();

                if (tennd.equals("")){
                    Toast.makeText(context, "Bạn chưa nhập tên người dùng", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (sdt.equals("")){
                    Toast.makeText(context, "Bạn chưa nhập SĐT", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (diachi.equals("")){
                    Toast.makeText(context, "Bạn chưa nhập địa chỉ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (tendn.equals("")){
                    Toast.makeText(context, "Bạn chưa nhập tên đăng nhập", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (matkhau.equals("")){
                    Toast.makeText(context, "Bạn chưa nhập mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (roleuser.equals("")){
                    Toast.makeText(context, "Bạn chưa nhập quyền hạn", Toast.LENGTH_SHORT).show();
                    return;
                }

                int quyen;
                try {
                    quyen = Integer.parseInt(roleuser);

                    if (quyen > 3) {
                        Toast.makeText(context, "Quyền hạn nhập sai định dạng", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(context, "Vui lòng nhập một số nguyên cho quyền hạn", Toast.LENGTH_SHORT).show();
                    return;
                }
                NguoiDung userUpdate = new NguoiDung(nguoiDung.getMand(), tennd, sdt, diachi, tendn, matkhau, quyen);

                boolean check = nguoiDungDAO.suaUser(userUpdate);
                if (check){
//                    Snackbar.make(recyclerView, "Thêm thành công", Snackbar.LENGTH_SHORT).show();
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                    alertDialog.dismiss();
                }else{
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void loadData(){
        list.clear();
        list = nguoiDungDAO.getDSUser();
        notifyDataSetChanged();
    }
}
