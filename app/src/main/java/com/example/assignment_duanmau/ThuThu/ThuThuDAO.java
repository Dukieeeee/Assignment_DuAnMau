package com.example.assignment_duanmau.ThuThu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignment_duanmau.Sach.SachDB;

import java.util.ArrayList;

public class ThuThuDAO {
    SachDB dbHelper;
    SQLiteDatabase db;

    public ThuThuDAO(Context context){
        dbHelper = new SachDB(context);
    }

    public void open(){
        db = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    public long insert(ThuThu objThu){
        ContentValues cv = new ContentValues();
        cv.put(ThuThu.COL_NAME, objThu.getTen());
        cv.put(ThuThu.COL_MATKHAU, objThu.getMatkhau());
        long res = db.insert(ThuThu.TB_NAME, null, cv);
        return res;
    }

    public int delete(ThuThu objThu){
        int res = db.delete(ThuThu.TB_NAME, "id = ?", new String[]{ objThu.getId()+""});

        return res;
    }

    public int update(ThuThu objThu){
        ContentValues cv = new ContentValues();
        cv.put(ThuThu.COL_NAME, objThu.getTen());
        cv.put(ThuThu.COL_MATKHAU, objThu.getMatkhau());

        int res = db.update(ThuThu.TB_NAME, cv, "id = ?", new String[]{objThu.getId()+""});
        return res;
    }
    public ArrayList<ThuThu> selectAll(){
        ArrayList<ThuThu> listThu = new ArrayList<ThuThu>();
        String[] ds = new String[]{"*"};

        Cursor cs = db.query(ThuThu.TB_NAME, ds,null,null,null,null,null);

        if(cs.moveToFirst()){
            while(!(cs.isAfterLast())){
                ThuThu objThu = new ThuThu();
                objThu.setId(cs.getInt(0));
                objThu.setTen(cs.getString(1));
                objThu.setMatkhau(cs.getString(2));
                listThu.add(objThu);
                cs.moveToNext();
            }
        }
        return listThu;
    }
}
