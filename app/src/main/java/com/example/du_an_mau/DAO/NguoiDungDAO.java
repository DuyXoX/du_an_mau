package com.example.du_an_mau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_mau.Database.DBHelper;

public class NguoiDungDAO {
    private DBHelper dbHelper;
    public NguoiDungDAO(Context context){
        dbHelper = new DBHelper(context);
    }
    public boolean CheckUser(String username, String password){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM NGUOIDUNG WHERE tendangnhap = ? AND matkhau = ?",
                new String[]{username, password});
        if (cursor.getCount() > 0){
            return true;
        } else {
            return false;
        }
    }
//    private boolean AddUser(String username, String password) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put("tendangnhap", username);
//        values.put("matkhau", password);
//        // Bạn có thể thêm các trường thông tin khác của tài khoản vào values ở đây
//
//        long result = db.insert("NGUOIDUNG", null, values);
//        db.close();
//        // Khi kết quả là -1, có nghĩa là thêm không thành công
//        // Khi kết quả khác -1, có nghĩa là thêm thành công
//        return result != -1;
//    }

}
