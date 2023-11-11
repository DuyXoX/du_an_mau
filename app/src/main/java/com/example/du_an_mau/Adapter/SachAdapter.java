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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_mau.DAO.ThuVienSachDAO;
import com.example.du_an_mau.Models.Sach;
import com.example.du_an_mau.R;
import com.example.du_an_mau.ThuVienSach;

import java.util.ArrayList;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Sach> list;
    private ThuVienSachDAO thuVienSachDAO;

    public SachAdapter(Context context, ArrayList<Sach> list, ThuVienSachDAO thuVienSachDAO) {
        this.context = context;
        this.list = list;
        this.thuVienSachDAO = thuVienSachDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thu_vien_sach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SachAdapter.ViewHolder holder, int position) {
        //Truyền dữ liệu vào
        holder.maSach.setText("Mã sách: " + list.get(position).getMasach());
        holder.tenSach.setText(list.get(position).getTensach());
        holder.tenTacGia.setText(list.get(position).getTacgia());
        holder.giaBan.setText("Giá bán: " + list.get(position).getGiaban());
        holder.tenLoai.setText("Tên loại: " + list.get(position).getTenloai());

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
                builder.setMessage("Bạn có muốn xóa sách " + list.get(holder.getAdapterPosition()).getTensach() + " hay không?");
                builder.setIcon(R.drawable.ic_warning);
                builder.setPositiveButton("Vâng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int check = thuVienSachDAO.xoaSach(list.get(holder.getAdapterPosition()).getMasach());
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView anhSach;
        private ImageButton btn_edit, btn_delete;
        private TextView maSach, tenSach, tenTacGia, giaBan, tenLoai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            anhSach = itemView.findViewById(R.id.anhSach);
            btn_edit = itemView.findViewById(R.id.btn_edit);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            maSach = itemView.findViewById(R.id.maSach);
            tenSach = itemView.findViewById(R.id.tenSach);
            tenTacGia = itemView.findViewById(R.id.tenTacGia);
            giaBan = itemView.findViewById(R.id.giaBan);
            tenLoai = itemView.findViewById(R.id.tenLoai);
        }
    }
    private void showDiaLogUpdate(Sach sach){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sach, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        alertDialog.show();

        TextView tieuDe = view.findViewById(R.id.tieuDe);
        EditText tenSach, tenTacGia, giaBan, tenLoai;;
        tenSach = view.findViewById(R.id.tenSach);
        tenTacGia = view.findViewById(R.id.tenTacGia);
        giaBan = view.findViewById(R.id.giaBan);
        tenLoai = view.findViewById(R.id.tenLoai);
        Button btnLuu, btnHuy;
        btnLuu = view.findViewById(R.id.btnLuu);
        btnHuy = view.findViewById(R.id.btnHuy);

        tieuDe.setText("Cập nhật Sách");
        btnLuu.setText("Cập nhật");

        tenSach.setText(sach.getTensach());
        tenTacGia.setText(sach.getTacgia());
        giaBan.setText(String.valueOf(sach.getGiaban()));
        tenLoai.setText(sach.getTenloai());

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tensach = tenSach.getText().toString();
                String tentacgia = tenTacGia.getText().toString();
                String giabanStr = giaBan.getText().toString();
                String tenloai = tenLoai.getText().toString();

                if (tensach.equals("")){
                    Toast.makeText(context, "Bạn chưa nhập tên sách", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (tentacgia.equals("")){
                    Toast.makeText(context, "Bạn chưa nhập tên tác giả", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (giabanStr.isEmpty()){
                    Toast.makeText(context, "Bạn chưa nhập giá bán", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (tenloai.isEmpty()){
                    Toast.makeText(context, "Bạn chưa nhập mã loại", Toast.LENGTH_SHORT).show();
                    return;
                }

                int giaban = Integer.parseInt(giabanStr);
                Sach sachUpdate = new Sach(sach.getMasach(), tensach, tentacgia, giaban, sach.getMaloai(), tenloai);

                boolean checkSach = thuVienSachDAO.checkSach(tenloai);
                if (!checkSach){
                    Toast.makeText(context, "Tên loại sách này không tồn tại", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean check = thuVienSachDAO.suaSach(sachUpdate);
                if (check){
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
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
    private void showDiaLogDelete(){

    }
    private void loadData(){
        list.clear();
        list = thuVienSachDAO.getDSSach();
        notifyDataSetChanged();
    }
}
