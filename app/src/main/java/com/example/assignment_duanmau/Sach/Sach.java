package com.example.assignment_duanmau.Sach;

public class Sach {
    private int ma;
    private String ten;
    private String loaisach;
    private String gia;
    private String tacgia;
    private int soluong_ph25202;

    public static final String TB_NAME = "sach";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "ten";
    public static final String COL_TYPE = "loaisach";
    public static final String COL_PRICE = "gia";
    public static final String COL_AUTHOR = "tacgia";
    public static final String COL_NUM = "soluong_ph25202";

    public Sach() {
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLoaisach() {
        return loaisach;
    }

    public void setLoaisach(String loaisach) {
        this.loaisach = loaisach;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public int getSoluong_ph25202() {
        return soluong_ph25202;
    }

    public void setSoluong_ph25202(int soluong_ph25202) {
        this.soluong_ph25202 = soluong_ph25202;
    }
}
