package com.example.du_an_mau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_mau.Database.DBHelper;
import com.example.du_an_mau.Models.LoaiSach;

import java.util.ArrayList;

public class LoaiSachDAO {
    private DBHelper dbHelper;

    public LoaiSachDAO(Context context){
        dbHelper = new DBHelper(context);
    }
    //Lấy danh sách loại sách
    public ArrayList<LoaiSach> getDSLoaiSach(){
        ArrayList<LoaiSach> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAISACH", null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            do{
                list.add(new LoaiSach(cursor.getInt(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        return list;
    }
    public boolean themLoaiSach(String tenLoai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put("maloai", maLoai);
        contentValues.put("tenloai", tenLoai);

        long result = sqLiteDatabase.insert("LOAISACH", null, contentValues);

        return result != -1;
    }
    public boolean suaLoaiSach(LoaiSach loaiSach){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put("maloai", loaiSach.getMaloai());
        contentValues.put("tenloai", loaiSach.getTenloai());

        // Xây dựng điều kiện WHERE để xác định dòng cần cập nhật
        String whereClause = "maloai = ?"; // Điều kiện là maloai
        String[] whereArgs = {String.valueOf(loaiSach.getMaloai())}; // Giá trị của maloai

        // Sử dụng phương thức update để cập nhật dữ liệu
        int rowsAffected = sqLiteDatabase.update("LOAISACH", contentValues, whereClause, whereArgs);

        // Kiểm tra xem có dòng nào bị ảnh hưởng hay không
        return rowsAffected > 0;
    }
    public int xoaLoaiSach(int maLoai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String[] whereArgs = {String.valueOf(maLoai)};
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SACH WHERE maloai = ?", whereArgs);
        if (cursor.getCount() > 0){
            return 0;
        }else {
            String whereClause = "maloai = ?";
            int check = sqLiteDatabase.delete("LOAISACH", whereClause, whereArgs);
            if (check == 0){
                return -1;
            }else {
                return 1;
            }
        }
    }
}
