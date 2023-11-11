package com.example.du_an_mau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_mau.Database.DBHelper;
import com.example.du_an_mau.Models.Sach;

import java.util.ArrayList;

public class ThuVienSachDAO {
    private DBHelper dbHelper;
    SharedPreferences sharedPreferences;
    public ThuVienSachDAO(Context context){
        dbHelper = new DBHelper(context);
        sharedPreferences = context.getSharedPreferences("dataBook", Context.MODE_PRIVATE);
    }
    public ArrayList<Sach> getDSSach(){
        ArrayList<Sach> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT s.masach, s.tensach, s.tacgia, s.giaban, s.maloai, l.tenloai FROM SACH s, LOAISACH l WHERE s.maloai = l.maloai", null);

//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SACH", null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            do{
                list.add(new Sach(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        return list;
    }
    public boolean checkSach(String tenLoai){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM LOAISACH WHERE tenloai =?",
                new String[]{tenLoai});
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("tenloai", cursor.getInt(1));
            editor.apply();
        }
        return cursor.getCount() > 0;
    }
    public boolean themSach(String tenSach, String tacGia, int giaBan, String tenLoai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        String[] columns = {"maloai"};
        String selection = "tenloai = ?";
        String[] selectionArgs = {tenLoai};

        Cursor cursor = sqLiteDatabase.query("LOAISACH", columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            // Nếu maloai tồn tại, thì thêm sách vào bảng SACH
            int columnIndex = cursor.getColumnIndex("maloai");
            if (columnIndex != -1) {
                int maloai = cursor.getInt(columnIndex);
                contentValues.put("tensach", tenSach);
                contentValues.put("tacgia", tacGia);
                contentValues.put("giaban", giaBan);
                contentValues.put("maloai", maloai);
            }

            long check = sqLiteDatabase.insert("SACH", null, contentValues);

            return check != -1;
        } else {
            // Nếu maloai không tồn tại, bạn có thể xử lý hoặc thông báo lỗi tại đây.
            return false;
        }
    }

    public boolean suaSach(Sach sach) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        String[] columns = {"maloai"};
        String selection = "tenloai = ?";
        String[] selectionArgs = {sach.getTenloai()};

        Cursor cursor = sqLiteDatabase.query("LOAISACH", columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("maloai");
            if (columnIndex != -1) {
                int maloai = cursor.getInt(columnIndex);
                contentValues.put("tensach", sach.getTensach());
                contentValues.put("tacgia", sach.getTacgia());
                contentValues.put("giaban", sach.getGiaban());
                contentValues.put("maloai", maloai);
            }

            String whereClause = "masach = ?";
            String[] whereArgs = {String.valueOf(sach.getMasach())};

            int rowsAffected = sqLiteDatabase.update("SACH", contentValues, whereClause, whereArgs);

            return rowsAffected > 0;
        } else {
            // Xử lý khi tên loại không tồn tại hoặc thông báo lỗi.
            return false;
        }
    }

    public int xoaSach(int maSach) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String whereClause = "masach = ?";
        String[] whereArgs = {String.valueOf(maSach)};

        int check = sqLiteDatabase.delete("SACH", whereClause, whereArgs);

        if (check > 0) {
            return 1;  // Xóa thành công.
        } else {
            return -1; // Xóa thất bại hoặc không tìm thấy sách để xóa.
        }
    }




}
