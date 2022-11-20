package com.example.assignment_duanmau.ThanhVien;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignment_duanmau.Sach.Sach;
import com.example.assignment_duanmau.Sach.SachDB;

import java.util.ArrayList;

public class ThanhVienDAO {
    SachDB dbHelper;
    SQLiteDatabase db;

    public ThanhVienDAO(Context context){
        dbHelper = new SachDB(context);
    }

    public void open(){
        db = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    public long insert(ThanhVien objTVien){
        ContentValues cv = new ContentValues();
        cv.put(ThanhVien.COL_NAME, objTVien.getTen());
        cv.put(ThanhVien.COL_GENDER, objTVien.getGioitinh_ph25202());

        long res = db.insert(ThanhVien.TB_NAME, null, cv);
        return res;
    }

    public int delete(ThanhVien objTVien){
        int res = db.delete(ThanhVien.TB_NAME, "id = ?", new String[]{ objTVien.getId()+""});

        return res;
    }

    public int update(ThanhVien objTVien){
        ContentValues cv = new ContentValues();
        cv.put(ThanhVien.COL_NAME, objTVien.getTen());
        cv.put(ThanhVien.COL_GENDER, objTVien.getGioitinh_ph25202());

        int res = db.update(ThanhVien.TB_NAME, cv, "id = ?", new String[]{objTVien.getId()+""});
        return res;
    }
    public ArrayList<ThanhVien> selectAll(){
        ArrayList<ThanhVien> listTVien = new ArrayList<ThanhVien>();
        String[] ds = new String[]{"*"};

        Cursor cs = db.query(ThanhVien.TB_NAME, ds,null,null,null,null,null);

        if(cs.moveToFirst()){
            while(!(cs.isAfterLast())){
                ThanhVien objTVien = new ThanhVien();
                objTVien.setId(cs.getInt(0));
                objTVien.setTen(cs.getString(1));
                objTVien.setGioitinh_ph25202(cs.getInt(2));
                listTVien.add(objTVien);

                cs.moveToNext();
            }
        }
        return listTVien;
    }
}
