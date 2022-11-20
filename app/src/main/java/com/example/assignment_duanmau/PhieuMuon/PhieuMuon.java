package com.example.assignment_duanmau.PhieuMuon;

public class PhieuMuon {
    private int id;
    private String ten;
    private String tensach;
    private String ngaymuon;
    private String gia;

    public static final String TB_NAME = "phieumuon";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "ten";
    public static final String COL_BNAME = "tensach";
    public static final String COL_DATE = "ngaymuon";
    public static final String COL_PRICE = "gia";

    public PhieuMuon() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getNgaymuon() {
        return ngaymuon;
    }

    public void setNgaymuon(String ngaymuon) {
        this.ngaymuon = ngaymuon;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}
