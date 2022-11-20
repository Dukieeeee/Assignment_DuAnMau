package com.example.assignment_duanmau.PhieuMuon;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignment_duanmau.Sach.Sach;
import com.example.assignment_duanmau.Sach.SachDAO;
import com.example.assignment_duanmau.Sach.SachDB;

import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {
    SachDB dbHelper;
    SQLiteDatabase db;
    Context context;
    public PhieuMuonDAO(Context context){
        this.context = context;
        dbHelper = new SachDB(context);
    }

    public void open(){
        db = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    public long insert(PhieuMuon objPMuon){
        ContentValues cv = new ContentValues();
        cv.put(PhieuMuon.COL_NAME, objPMuon.getTen());
        cv.put(PhieuMuon.COL_BNAME, objPMuon.getTensach());
        cv.put(PhieuMuon.COL_DATE, objPMuon.getNgaymuon());
        cv.put(PhieuMuon.COL_PRICE, objPMuon.getGia());

        long res = db.insert(PhieuMuon.TB_NAME, null, cv);
        return res;
    }

    public int delete(PhieuMuon objPMuon){
        int res = db.delete(PhieuMuon.TB_NAME, "id = ?", new String[]{ objPMuon.getId()+""});

        return res;
    }

    public int update(PhieuMuon objPMuon){
        ContentValues cv = new ContentValues();
        cv.put(PhieuMuon.COL_NAME, objPMuon.getTen());
        cv.put(PhieuMuon.COL_BNAME, objPMuon.getTensach());
        cv.put(PhieuMuon.COL_DATE, objPMuon.getNgaymuon());
        cv.put(PhieuMuon.COL_PRICE, objPMuon.getGia());

        int res = db.update(PhieuMuon.TB_NAME, cv, "id = ?", new String[]{objPMuon.getId()+""});
        return res;
    }
    public ArrayList<PhieuMuon> selectAll(){
        ArrayList<PhieuMuon> listPMuon = new ArrayList<PhieuMuon>();
        String[] ds = new String[]{"*"};

        Cursor cs = db.query(PhieuMuon.TB_NAME, ds,null,null,null,null,null);

        if(cs.moveToFirst()){
            while(!(cs.isAfterLast())){
                PhieuMuon objPMuon = new PhieuMuon();
                objPMuon.setId(cs.getInt(0));
                objPMuon.setTen(cs.getString(1));
                objPMuon.setTensach(cs.getString(2));
                objPMuon.setNgaymuon(cs.getString(3));
                objPMuon.setGia(cs.getString(4));
                listPMuon.add(objPMuon);

                cs.moveToNext();
            }
        }
        return listPMuon;
    }
    public ArrayList<PhieuMuon> doanhThu(String ngaydau, String ngaycuoi){
        String sql = "SELECT gia FROM phieumuon WHERE ngaymuon BETWEEN ? AND ?";
        ArrayList<PhieuMuon> list = new ArrayList<PhieuMuon>();
        Cursor cs = db.rawQuery(sql, new String[]{ngaydau, ngaycuoi});
        if (cs.moveToFirst()){
            while (!(cs.isAfterLast())){
                PhieuMuon objPMuon = new PhieuMuon();
                objPMuon.setGia(cs.getString(0));
                list.add(objPMuon);

                cs.moveToNext();
            }
        }
        return list;
    }

//    public List<Top> getTop(){
//        String sqlTop = "SELECT tensach, count(tensach) as soLuong FROM phieumuon GROUP BY tensach ORDER BY soluong DESE LIMIT 10";
//        List<Top> listTop = new ArrayList<Top>();
//        SachDAO sachDAO = new SachDAO(context);
//        Sach objSach = new Sach();
//        Cursor cs = db.rawQuery(sqlTop, null);
//        while (cs.moveToNext()){
//            Top top = new Top();
//        }
//    }
}
