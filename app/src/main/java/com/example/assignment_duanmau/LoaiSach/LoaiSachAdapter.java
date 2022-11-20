package com.example.assignment_duanmau.LoaiSach;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment_duanmau.R;
import com.example.assignment_duanmau.Sach.Sach;

import java.util.ArrayList;

public class LoaiSachAdapter extends BaseAdapter {
    LoaiSachDAO loaiSachDAO;
    ArrayList<LoaiSach> listLSach;

    public LoaiSachAdapter(LoaiSachDAO loaiSachDAO, ArrayList<LoaiSach> listLSach) {
        this.loaiSachDAO = loaiSachDAO;
        this.listLSach = listLSach;
    }

    @Override
    public int getCount() {
        return listLSach.size();
    }

    @Override
    public Object getItem(int position) {
        LoaiSach objLSach = listLSach.get(position);
        return objLSach;
    }

    @Override
    public long getItemId(int position) {
        LoaiSach objLSach = listLSach.get(position);
        return objLSach.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if(convertView == null){
            v = View.inflate(parent.getContext(), R.layout.dong_loaisach,null);
        }else v = convertView;

        final LoaiSach objLSach = listLSach.get(position);
        final int index = position;
        TextView tv_id = v.findViewById(R.id.tv_idloaisach);
        TextView tv_ten = v.findViewById(R.id.tv_tenloaisach);
        ImageView img_sua = v.findViewById(R.id.img_sualoaisach);
        ImageView img_xoa = v.findViewById(R.id.img_xoaloaisach);

        tv_id.setText(objLSach.getId()+"");
        tv_ten.setText(objLSach.getTen()+"");

        img_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setView(R.layout.dialog_them_sua_loaisach);
                builder.setTitle("Sửa");
                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog alertDialog = (AlertDialog) dialog;
                        EditText ed_sua = alertDialog.findViewById(R.id.ed_them_sua_thanhvien);
                        objLSach.setTen(ed_sua.getText().toString());
                        int res = loaiSachDAO.update(objLSach);
                        if (res > -1){
                            listLSach.set(index, objLSach);
                            notifyDataSetChanged();
                            Toast.makeText(parent.getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(parent.getContext(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        } dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create();
                builder.show();
            }
        });

        img_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Xóa");
                builder.setMessage("Xác nhận xóa?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int kq = loaiSachDAO.delete(objLSach);
                        if (kq > -1){
                            listLSach.remove(index);
                            notifyDataSetChanged();
                            Toast.makeText(parent.getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(parent.getContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        } dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create();
                builder.show();
            }
        });
        return v;
    }
    public void setDataLoaiSach(ArrayList<LoaiSach> arrayList){
        this.listLSach = arrayList;
        notifyDataSetChanged();
    }
}
