package com.example.du_an_mau.DAO;

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
}
