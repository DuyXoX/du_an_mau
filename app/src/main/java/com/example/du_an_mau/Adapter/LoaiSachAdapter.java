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

import com.example.du_an_mau.DAO.LoaiSachDAO;
import com.example.du_an_mau.Models.LoaiSach;
import com.example.du_an_mau.R;
import com.example.du_an_mau.TheLoaiSach;

import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder> {
    private Context context;
    private ArrayList<LoaiSach> list;
    private LoaiSachDAO loaiSachDAO;

    public LoaiSachAdapter(Context context, ArrayList<LoaiSach> list, LoaiSachDAO loaiSachDAO) {
        this.context = context;
        this.list = list;
        this.loaiSachDAO = loaiSachDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_the_loai_sach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Truyền dữ liệu vào
        holder.maLoai.setText("Mã Loại: " + list.get(position).getMaloai());
        holder.tenLoaiSach.setText(list.get(position).getTenloai());

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
                builder.setMessage("Bạn có muốn xóa thể loại sách " + list.get(holder.getAdapterPosition()).getTenloai() + " hay không?");
                builder.setIcon(R.drawable.ic_warning);
                builder.setPositiveButton("Vâng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int check = loaiSachDAO.xoaLoaiSach(list.get(holder.getAdapterPosition()).getMaloai());
                        if (check == 0) {
                            // Có sách thuộc loại sách này, không thể xóa.
                            Toast.makeText(context, "Không thể xóa loại sách có sách liên quan", Toast.LENGTH_SHORT).show();
                        } else if (check == -1) {
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
        private ImageView anhSach;
        private ImageButton btn_edit, btn_delete;
        private TextView maLoai, tenSach, tenTacGia, tenLoaiSach;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            anhSach = itemView.findViewById(R.id.anhSach);
            btn_edit = itemView.findViewById(R.id.btn_edit);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            maLoai = itemView.findViewById(R.id.maLoai);
            tenSach = itemView.findViewById(R.id.tenSach);
            tenTacGia = itemView.findViewById(R.id.tenTacGia);
            tenLoaiSach = itemView.findViewById(R.id.tenLoaiSach);
        }
    }
    private void showDiaLogUpdate(LoaiSach loaiSach){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.the_loai_sach, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        alertDialog.show();

        TextView tieuDe = view.findViewById(R.id.tieuDe);
        EditText maLoai, tenLoaiSach;
//        maLoai = view.findViewById(R.id.maLoai);
        tenLoaiSach = view.findViewById(R.id.tenLoaiSach);
        Button btnLuu, btnHuy;
        btnLuu = view.findViewById(R.id.btnLuu);
        btnHuy = view.findViewById(R.id.btnHuy);

        tieuDe.setText("Cập nhật Loại Sách");
        btnLuu.setText("Cập nhật");
        tenLoaiSach.setText(loaiSach.getTenloai());

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String maLoaiStr = maLoai.getText().toString();
                String tenloai = tenLoaiSach.getText().toString();

                LoaiSach loaiSachUpdate = new LoaiSach(loaiSach.getMaloai(), tenloai);



//                if (maLoaiStr.isEmpty()) {
//                    Toast.makeText(context, "Bạn chưa nhập mã loại sách", Toast.LENGTH_SHORT).show();
//                    return;
//                }

//                int maloai = Integer.parseInt(maLoaiStr);

                if (tenloai.equals("")){
                    Toast.makeText(context, "Bạn chưa nhập tên loại sách", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean check = loaiSachDAO.suaLoaiSach(loaiSachUpdate);
//                boolean check = loaiSachDAO.suaLoaiSach(maloai, tenloai);
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
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }
    private void loadData(){
        list.clear();
        list = loaiSachDAO.getDSLoaiSach();
        notifyDataSetChanged();
    }
}
