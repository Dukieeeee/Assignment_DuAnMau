package com.example.assignment_duanmau.ThanhVien;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.Image;
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

public class ThanhVienAdapter extends BaseAdapter {
    ThanhVienDAO thanhVienDAO;
    ArrayList<ThanhVien> listTV;

    public ThanhVienAdapter(ThanhVienDAO thanhVienDAO, ArrayList<ThanhVien> listTV) {
        this.thanhVienDAO = thanhVienDAO;
        this.listTV = listTV;
    }

    @Override
    public int getCount() {
        return listTV.size();
    }

    @Override
    public Object getItem(int position) {
        ThanhVien objTV = listTV.get(position);
        return objTV;
    }

    @Override
    public long getItemId(int position) {
        ThanhVien objTV = listTV.get(position);
        return objTV.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if(convertView == null){
            v = View.inflate(parent.getContext(), R.layout.dong_thanhvien,null);
        }else v = convertView;

        final ThanhVien objTV = listTV.get(position);
        final int index = position;

        TextView tv_id = v.findViewById(R.id.tv_idthanhvien);
        TextView tv_ten = v.findViewById(R.id.tv_tenthanhvien);
        TextView tv_gioitinh = v.findViewById(R.id.tv_gioitinh);
        ImageView img_sua = v.findViewById(R.id.img_suathanhvien);
        ImageView img_xoa = v.findViewById(R.id.img_xoathanhvien);

        tv_id.setText(objTV.getId()+"");
        tv_ten.setText(objTV.getTen()+"");
        tv_gioitinh.setText(objTV.getGioitinh_ph25202()+"");

        for (int i = 0; i <listTV.size(); i++){
            if (listTV.get(i).getGioitinh_ph25202() == 1){
                v.setBackgroundColor(Color.parseColor("#FFD700"));
            }
        }

        img_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setView(R.layout.dialog_them_sua_thanhvien);
                builder.setTitle("Sửa");
                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog alertDialog = (AlertDialog) dialog;
                        EditText ed_sua = alertDialog.findViewById(R.id.ed_them_sua_thanhvien);
                        objTV.setTen(ed_sua.getText().toString());
                        objTV.setGioitinh_ph25202(Integer.parseInt(ed_sua.getText().toString()));
                        int res = thanhVienDAO.update(objTV);
                        if (res > -1){
                            listTV.set(index, objTV);
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
                        int kq = thanhVienDAO.delete(objTV);
                        if (kq > -1){
                            listTV.remove(index);
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
    public void setDataThanhVien(ArrayList<ThanhVien> arrayList){
        this.listTV = arrayList;
        notifyDataSetChanged();
    }
}
