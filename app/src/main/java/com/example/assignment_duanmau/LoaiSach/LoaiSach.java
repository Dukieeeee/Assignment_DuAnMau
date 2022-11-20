package com.example.assignment_duanmau.LoaiSach;

public class LoaiSach {
    private int id;
    private String ten;

    public static final String TB_NAME = "loaisach";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "ten";

    public LoaiSach() {
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
}
