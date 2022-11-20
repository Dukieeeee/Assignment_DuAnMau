package com.example.assignment_duanmau.LoaiSach;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignment_duanmau.Sach.SachDB;

import java.util.ArrayList;

public class LoaiSachDAO {
    SachDB dbHelper;
    SQLiteDatabase db;

    public LoaiSachDAO(Context context){
        dbHelper = new SachDB(context);
    }

    public void open(){
        db = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    public long insert(LoaiSach objLSach){
        ContentValues cv = new ContentValues();
        cv.put(LoaiSach.COL_NAME, objLSach.getTen());

        long res = db.insert(LoaiSach.TB_NAME, null, cv);
        return res;
    }

    public int delete(LoaiSach objLSach){
        int res = db.delete(LoaiSach.TB_NAME, "id = ?", new String[]{ objLSach.getId()+""});

        return res;
    }

    public int update(LoaiSach objLSach){
        ContentValues cv = new ContentValues();
        cv.put(LoaiSach.COL_NAME, objLSach.getTen());

        int res = db.update(LoaiSach.TB_NAME, cv, "id = ?", new String[]{objLSach.getId()+""});
        return res;
    }

    public ArrayList<LoaiSach> selectAll(){
        ArrayList<LoaiSach> listLSach = new ArrayList<LoaiSach>();
        String[] ds = new String[]{"*"};

        Cursor cs = db.query(LoaiSach.TB_NAME, ds,null,null,null,null,null);

        if(cs.moveToFirst()){
            while(!(cs.isAfterLast())){
                LoaiSach objLSach = new LoaiSach();
                objLSach.setId(cs.getInt(0));
                objLSach.setTen(cs.getString(1));
                listLSach.add(objLSach);

                cs.moveToNext();
            }
        }
        return listLSach;
    }
}
