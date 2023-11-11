package com.example.du_an_mau.Models;

public class PhieuMuon {
//mapm integer primary key autoincrement, ngaymuon text, ngaytra text,
// mand integer references NGUOIDUNG(mand)
    private int mapm;
    private String ngaymuon;
    private String ngaytra;
    private int mand;
    private String tendangnhap;

    public PhieuMuon(int mapm, String ngaymuon, String ngaytra, int mand, String tendangnhap) {
        this.mapm = mapm;
        this.ngaymuon = ngaymuon;
        this.ngaytra = ngaytra;
        this.mand = mand;
        this.tendangnhap = tendangnhap;
    }

    public int getMapm() {
        return mapm;
    }

    public void setMapm(int mapm) {
        this.mapm = mapm;
    }

    public String getNgaymuon() {
        return ngaymuon;
    }

    public void setNgaymuon(String ngaymuon) {
        this.ngaymuon = ngaymuon;
    }

    public String getNgaytra() {
        return ngaytra;
    }

    public void setNgaytra(String ngaytra) {
        this.ngaytra = ngaytra;
    }

    public int getMand() {
        return mand;
    }

    public void setMand(int mand) {
        this.mand = mand;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }
}
