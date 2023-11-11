package com.example.du_an_mau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_mau.Database.DBHelper;
import com.example.du_an_mau.Models.LoaiSach;
import com.example.du_an_mau.Models.NguoiDung;

import java.util.ArrayList;

public class NguoiDungDAO {
    private DBHelper dbHelper;
    SharedPreferences sharedPreferences;
    public NguoiDungDAO(Context context){
        dbHelper = new DBHelper(context);
        sharedPreferences = context.getSharedPreferences("dataUser", Context.MODE_PRIVATE);
    }
    public ArrayList<NguoiDung> getDSUser(){
        ArrayList<NguoiDung> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG", null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            do{
                list.add(new NguoiDung(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6)));
            } while (cursor.moveToNext());
        }
        return list;
    }
    public boolean CheckUser(String username, String password){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM NGUOIDUNG WHERE tendangnhap = ? AND matkhau = ?",
                new String[]{username, password});
//        if (cursor.getCount() > 0){
//            return true;
//        } else {
//            return false;
//        }
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("role", cursor.getInt(6));
            editor.putString("tendangnhap", cursor.getString(4));
            editor.apply();
        }

        return cursor.getCount() > 0;
    }
    public boolean timKiemUser(String username){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM NGUOIDUNG WHERE tendangnhap = ?",
                new String[]{username});
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("role", cursor.getInt(6));
            editor.apply();
        }

        return cursor.getCount() > 0;
    }
    public boolean signupUser(NguoiDung nguoiDung){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("tennd", nguoiDung.getTendangnhap());
//        contentValues.put("sdt", nguoiDung.getTendangnhap());
//        contentValues.put("diachi", nguoiDung.getTendangnhap());
        contentValues.put("tendangnhap", nguoiDung.getTendangnhap());
        contentValues.put("matkhau", nguoiDung.getMatkhau());
        contentValues.put("role", 3);

        long result = db.insert("NGUOIDUNG", null, contentValues);
        return result != -1;
    }
    public boolean themUser(String tenND, String SDT, String diaChi, String username, String password, int role) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("tennd", tenND);
        contentValues.put("sdt", SDT);
        contentValues.put("diachi", diaChi);
        contentValues.put("tendangnhap", username);
        contentValues.put("matkhau", password);
        contentValues.put("role", role);

        long result = sqLiteDatabase.insert("NGUOIDUNG", null, contentValues);

        return result != -1;
    }
    public boolean suaUser(NguoiDung nguoiDung){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("tennd", nguoiDung.getTennd());
        contentValues.put("sdt", nguoiDung.getSdt());
        contentValues.put("diachi", nguoiDung.getDiachi());
        contentValues.put("tendangnhap", nguoiDung.getTendangnhap());
        contentValues.put("matkhau", nguoiDung.getMatkhau());
        contentValues.put("role", nguoiDung.getRole());

        // Xây dựng điều kiện WHERE để xác định dòng cần cập nhật
        String whereClause = "mand = ?"; // Điều kiện là maloai
        String[] whereArgs = {String.valueOf(nguoiDung.getMand())};

        int rowsAffected = sqLiteDatabase.update("NGUOIDUNG", contentValues, whereClause, whereArgs);
        return rowsAffected > 0;
    }
    public int xoaUser(int mand){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String whereClause = "mand = ?";
        String[] whereArgs = {String.valueOf(mand)};

        int check = sqLiteDatabase.delete("NGUOIDUNG", whereClause, whereArgs);

        return (check > 0) ? 1 : -1;
    }

}
