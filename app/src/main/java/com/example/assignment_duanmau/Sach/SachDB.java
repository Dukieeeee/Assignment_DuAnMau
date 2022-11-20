package com.example.assignment_duanmau.Sach;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SachDB extends SQLiteOpenHelper {
        static final String DB_NAME = "assignment_duanmau2.db";
        static final int DB_VERSION = 1;
        Sach objSach;

        public SachDB(Context context){
            super(context, DB_NAME, null, DB_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql_cre_sach = "CREATE TABLE sach (id INTEGER NOT NULL UNIQUE, ten TEXT NOT NULL, loaisach TEXT NOT NULL, gia TEXT NOT NULL, tacgia TEXT NOT NULL,  soluong_ph25202 INTEGER NOT NULL, PRIMARY KEY(id AUTOINCREMENT))";
            db.execSQL(sql_cre_sach);
            String sql_cre_loaisach = "CREATE TABLE loaisach (id INTEGER NOT NULL UNIQUE, ten TEXT NOT NULL, PRIMARY KEY(id AUTOINCREMENT))";
            db.execSQL(sql_cre_loaisach);
            String sql_cre_thanhvien = "CREATE TABLE thanhvien (id INTEGER NOT NULL UNIQUE, ten TEXT NOT NULL, gioitinh_ph25202 INTERGER NOT NULL, PRIMARY KEY(id AUTOINCREMENT))";
            db.execSQL(sql_cre_thanhvien);
            String sql_cre_phieumuon = "CREATE TABLE phieumuon (id INTEGER NOT NULL UNIQUE, ten TEXT NOT NULL, tensach TEXT NOT NULL, ngaymuon TEXT NOT NULL, gia TEXT NOT NULL, PRIMARY KEY(id AUTOINCREMENT))";
            db.execSQL(sql_cre_phieumuon);
            String sql_cre_thuthu = "CREATE TABLE thuthu (id INTEGER NOT NULL UNIQUE, ten TEXT NOT NULL, matkhau TEXT NOT NULL, PRIMARY KEY(id AUTOINCREMENT))";
            db.execSQL(sql_cre_thuthu);
            String sql_cre_top = "CREATE TABLE top (id INTEGER NOT NULL UNIQUE, ten TEXT NOT NULL, soluong TEXT NOT NULL, PRIMARY KEY(id AUTOINCREMENT))";
            db.execSQL(sql_cre_top);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

