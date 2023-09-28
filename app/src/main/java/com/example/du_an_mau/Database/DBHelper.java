package com.example.du_an_mau.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context){
        super(context, "Database", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String tLoaiSach = "CREATE TABLE LOAISACH(maloai integer primary key autoincrement, tenloai text)";
        db.execSQL(tLoaiSach);

        String tSach = "CREATE TABLE SACH(masach integer primary key autoincrement, tensach text, tacgia text, giaban integer, maloai integer references LOAISACH(maloai))";
        db.execSQL(tSach);

        String TNguoiDung = "CREATE TABLE NGUOIDUNG(mand integer primary key autoincrement, tennd text, sdt text, diachi text, tendangnhap text, matkhau text, role integer)";
        db.execSQL(TNguoiDung);

        String TPhieuMuon = "CREATE TABLE PHIEUMUON(mapm integer primary key autoincrement, ngaymuon text, ngaytra text, mand integer references NGUOIDUNG(mand))";
        db.execSQL(TPhieuMuon);

//        String tCTPM = "CREATE TABLE CTPM(mapm integer primary key references PHIEUMUON(mapm), masach integer primary key references SACH(masach), soluong integer)";
        String tCTPM = "CREATE TABLE CTPM(mapm integer, masach integer, soluong integer, PRIMARY KEY (mapm, masach), FOREIGN KEY (mapm) REFERENCES PHIEUMUON(mapm), FOREIGN KEY (masach) REFERENCES SACH(masach))";

        db.execSQL(tCTPM);

        //Database mau loai sach
//        db.execSQL("INSERT INTO LOAISACH VALUES(1, 'Chi Dau')"); // Bat buot phai nhap day du du lieu
        db.execSQL("INSERT INTO LOAISACH(maloai, tenloai) VALUES(1, 'Chi Dau'), (2, 'Thang Teo'), (3, 'Cay Khe')"); // Khong buot phai nhap day du du lieu
        db.execSQL("INSERT INTO SACH(masach, tensach, tacgia, giaban, maloai) VALUES(1, 'Sach 1', 'Tac Gia 1', 10000, 1), (2, 'Sach 2', 'Tac Gia 2', 15000, 2), (3, 'Sach 3', 'Tac Gia 3', 20000, 3)");
        db.execSQL("INSERT INTO NGUOIDUNG(tennd, sdt, diachi, tendangnhap, matkhau, role) VALUES('Người Dùng 1', '1234567890', 'Địa Chỉ 1', 'user1', 'password1', 3), ('Thủ Thư 1', '9876543210', 'Địa Chỉ 2', 'librarian1', 'password2', 2), ('Admin', '5555555555', 'Địa Chỉ 3', 'admin', 'admin', 1)");
        db.execSQL("INSERT INTO PHIEUMUON(ngaymuon, ngaytra, mand) VALUES('2023-09-15', '2023-09-30', 1), ('2023-09-16', '2023-09-28', 2), ('2023-09-17', '2023-09-25', 3)");
        db.execSQL("INSERT INTO CTPM(mapm, masach, soluong) VALUES(1, 1, 2), (1, 2, 1), (2, 3, 3)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS LOAISACH");
            db.execSQL("DROP TABLE IF EXISTS SACH");
            db.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            db.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
            db.execSQL("DROP TABLE IF EXISTS CTPM");
            onCreate(db);
        }
    }
}
