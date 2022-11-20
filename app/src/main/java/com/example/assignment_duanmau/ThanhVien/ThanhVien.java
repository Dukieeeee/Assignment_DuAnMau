package com.example.assignment_duanmau.ThanhVien;

public class ThanhVien {
    private int id;
    private String ten;
    private int gioitinh_ph25202;

    public static final String TB_NAME = "thanhvien";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "ten";
    public static final String COL_GENDER = "gioitinh_ph25202";

    public ThanhVien() {
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

    public int getGioitinh_ph25202() {
        return gioitinh_ph25202;
    }

    public void setGioitinh_ph25202(int gioitinh_ph25202) {
        this.gioitinh_ph25202 = gioitinh_ph25202;
    }
}
