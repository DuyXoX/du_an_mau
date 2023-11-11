package com.example.du_an_mau.DAO;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_mau.Database.DBHelper;
import com.example.du_an_mau.Models.PhieuMuon;

import java.util.ArrayList;

public class PhieuMuonDAO {
//mapm integer primary key autoincrement, ngaymuon text, ngaytra text,
// mand integer references NGUOIDUNG(mand)
    private DBHelper dbHelper;
    SharedPreferences sharedPreferences;
    public PhieuMuonDAO(Context context){
        dbHelper = new DBHelper(context);
        sharedPreferences = context.getSharedPreferences("dataPhieuMuon", Context.MODE_PRIVATE);
    }
    public ArrayList<PhieuMuon> getDSPhieuMuon(){
        ArrayList<PhieuMuon> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT p.mapm, p.ngaymuon, p.ngaytra, p.mand, n.tendangnhap FROM PHIEUMUON p, NGUOIDUNG n WHERE p.mand = n.mand", null);
        if (cursor.getCount() >0){
            cursor.moveToFirst();
            do{
                list.add(new PhieuMuon(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        return list;
    }
    public boolean checkPhieuMuon(String tenDN){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG WHERE mand = ?",
                new String[]{tenDN});
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("mand", cursor.getInt(0));
            editor.apply();
        }
        return cursor.getCount() > 0;
    }
}
