package com.example.assignment_duanmau.Sach;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    SachDB dbHelper;
    SQLiteDatabase db;

    public SachDAO(Context context){
        dbHelper = new SachDB(context);
    }

    public void open(){
        db = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }

    public long insert(Sach objSach){
        ContentValues cv = new ContentValues();
        cv.put(Sach.COL_NAME, objSach.getTen());
        cv.put(Sach.COL_TYPE, objSach.getLoaisach());
        cv.put(Sach.COL_PRICE, objSach.getGia());
        cv.put(Sach.COL_AUTHOR, objSach.getTacgia());
        cv.put(Sach.COL_NUM, objSach.getSoluong_ph25202());

        long res = db.insert(Sach.TB_NAME, null, cv);
        return res;
    }

    public int delete(Sach objSach){
        int res = db.delete(Sach.TB_NAME, "id = ?", new String[]{ objSach.getMa()+""});

        return res;
    }

    public int update(Sach objSach){
        ContentValues cv = new ContentValues();
        cv.put(Sach.COL_NAME, objSach.getTen());
        cv.put(Sach.COL_TYPE, objSach.getLoaisach());
        cv.put(Sach.COL_PRICE, objSach.getGia());
        cv.put(Sach.COL_AUTHOR, objSach.getTacgia());
        cv.put(Sach.COL_NUM, objSach.getSoluong_ph25202());

        int res = db.update(Sach.TB_NAME, cv, "id = ?", new String[]{objSach.getMa()+""});
        return res;
    }
    public int updateSoluong(Sach objSach){
        ContentValues cv = new ContentValues();
        cv.put(Sach.COL_NUM, objSach.getSoluong_ph25202());

        int res = db.update(Sach.TB_NAME, cv, "id = ?", new String[]{objSach.getMa()+""});
        return res;
    }

    public ArrayList<Sach> selectAll(){
        ArrayList<Sach> listSach = new ArrayList<Sach>();
        String[] ds = new String[]{"*"};

        Cursor cs = db.query(Sach.TB_NAME, ds,null,null,null,null,null);

        if(cs.moveToFirst()){
            while(!(cs.isAfterLast())){
                Sach objSach = new Sach();
                objSach.setMa(cs.getInt(0));
                objSach.setTen(cs.getString(1));
                objSach.setLoaisach(cs.getString(2));
                objSach.setGia(cs.getString(3));
                objSach.setTacgia(cs.getString(4));
                listSach.add(objSach);

                cs.moveToNext();
            }
        }
        return listSach;
    }

    public ArrayList<Sach> getSoluong(String a){
        String sql = "SELECT * FROM sach WHERE soluong_ph25202 >= ?";
        ArrayList<Sach> list = new ArrayList<>();
        Cursor cs = db.rawQuery(sql, new String[]{a});
        if(cs.moveToFirst()){
            while(!(cs.isAfterLast())){
                Sach objSach = new Sach();
                objSach.setMa(cs.getInt(0));
                objSach.setTen(cs.getString(1));
                objSach.setLoaisach(cs.getString(2));
                objSach.setGia(cs.getString(3));
                objSach.setTacgia(cs.getString(4));
                list.add(objSach);

                cs.moveToNext();
            }
        }
        return list;
    }
}
