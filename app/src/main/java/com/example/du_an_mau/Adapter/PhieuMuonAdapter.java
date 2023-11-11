package com.example.du_an_mau.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_mau.DAO.PhieuMuonDAO;
import com.example.du_an_mau.Models.PhieuMuon;
import com.example.du_an_mau.R;

import java.util.ArrayList;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHolder> {
    private Context context;
    private ArrayList<PhieuMuon> list;
    private PhieuMuonDAO phieuMuonDAO;

    public PhieuMuonAdapter(Context context, ArrayList<PhieuMuon> list, PhieuMuonDAO phieuMuonDAO) {
        this.context = context;
        this.list = list;
        this.phieuMuonDAO = phieuMuonDAO;
    }

    @NonNull
    @Override
    public PhieuMuonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_phieu_muon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhieuMuonAdapter.ViewHolder holder, int position) {
        //Truyền dữ liệu vào
        SharedPreferences sharedPreferences = context.getSharedPreferences("dataUser", Context.MODE_PRIVATE);
        String tenDN = sharedPreferences.getString("tendangnhap", "");

        Log.d("TenDN", "tenDN co gia tri: " + tenDN);
//        SharedPreferences sharedPreferences = context.getSharedPreferences("dataUser", Context.MODE_PRIVATE);
//        int role = sharedPreferences.getInt("role", -1);
//        Log.d("Role User", "Quyen la: " + role);

        PhieuMuon phieuMuonTimDuoc = null;

        for (PhieuMuon phieuMuon : list) {
            if (phieuMuon.getTendangnhap().equals(tenDN)) {
                phieuMuonTimDuoc = phieuMuon;

                break; // Đã tìm thấy, thoát khỏi vòng lặp.
            }
        }

        if (phieuMuonTimDuoc != null) {
            // phieuMuonTimDuoc chứa thông tin về Phiếu Mượn của người dùng có tên đăng nhập là tenDN.
            // Bây giờ bạn có thể làm gì đó với phieuMuonTimDuoc.
            holder.maPM.setText("Mã phiếu mượn: " + phieuMuonTimDuoc.getMapm());
            holder.ngayMuon.setText("Ngày mượn: " + phieuMuonTimDuoc.getNgaymuon());
            holder.ngayTra.setText("Ngày trả: " + phieuMuonTimDuoc.getNgaytra());
            holder.tenUser.setText("Tên người mượn: " + phieuMuonTimDuoc.getTendangnhap());
        } else {
            // Không tìm thấy Phiếu Mượn cho người dùng này.
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView anhPhieuMuon;
        private ImageButton btn_edit, btn_delete;
        private TextView maPM, ngayMuon, ngayTra, tenUser;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            anhPhieuMuon = itemView.findViewById(R.id.anhSach);
            btn_edit = itemView.findViewById(R.id.btn_edit);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            maPM = itemView.findViewById(R.id.maPM);
            ngayMuon = itemView.findViewById(R.id.ngayMuon);
            ngayTra = itemView.findViewById(R.id.ngayTra);
            tenUser = itemView.findViewById(R.id.tenUser);
        }
    }

}
