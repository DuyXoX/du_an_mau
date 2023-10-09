package com.example.du_an_mau.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_mau.Models.LoaiSach;
import com.example.du_an_mau.R;

import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder> {
    private Context context;
    private ArrayList<LoaiSach> list;

    public LoaiSachAdapter(Context context, ArrayList<LoaiSach> list) {
        this.context = context;
        this.list = list;
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
}
