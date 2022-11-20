package com.example.assignment_duanmau.ThuThu;

public class ThuThu {
    private int id;
    private String ten;
    private String matkhau;

    public static final String TB_NAME = "thuthu";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "ten";
    public static final String COL_MATKHAU = "matkhau";

    public ThuThu() {
    }

    public ThuThu(String ten, String matkhau) {
        this.ten = ten;
        this.matkhau = matkhau;
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

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
