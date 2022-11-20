package com.example.assignment_duanmau.Sach;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment_duanmau.LoaiSach.LoaiSach;
import com.example.assignment_duanmau.LoaiSach.LoaiSachDAO;
import com.example.assignment_duanmau.LoaiSach.LoaiSachSpinner;
import com.example.assignment_duanmau.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SachAdapter extends BaseAdapter {
    SachDAO sachDAO;
    LoaiSachDAO loaiSachDAO;
    ArrayList<Sach> listSach;
    LoaiSach objLSach = new LoaiSach();

    public SachAdapter(SachDAO sachDAO, ArrayList<Sach> listSach) {
        this.sachDAO = sachDAO;
        this.listSach = listSach;
    }

    @Override
    public int getCount() {
        return listSach.size();
    }

    @Override
    public Object getItem(int position) {
        Sach objSach = listSach.get(position);
        return objSach;
    }

    @Override
    public long getItemId(int position) {
        Sach objSach = listSach.get(position);
        return objSach.getMa();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if(convertView == null){
            v = View.inflate(parent.getContext(), R.layout.dong_sach,null);
        }else v = convertView;

        final Sach objSach = listSach.get(position);
        final int index = position;

        TextView tv_id = v.findViewById(R.id.tv_idsach);
        TextView tv_ten = v.findViewById(R.id.tv_tensach);
        TextView tv_loaisach = v.findViewById(R.id.tv_tenloaisach2);
        TextView tv_gia = v.findViewById(R.id.tv_giasach);
        TextView tv_soluong = v.findViewById(R.id.tv_soluongsach);
        ImageView img_sua = v.findViewById(R.id.img_suasach);
        ImageView img_xoa = v.findViewById(R.id.img_xoasach);

        tv_id.setText(objSach.getMa()+"");
        tv_id.setTextColor(Color.parseColor("#FFBB86FC"));
        tv_ten.setText(objSach.getTen()+"");
        tv_ten.setTextColor(Color.parseColor("#FF3700B3"));
        tv_loaisach.setText(objSach.getLoaisach()+"");
        tv_gia.setText(objSach.getGia()+"");
        tv_soluong.setText(objSach.getSoluong_ph25202()+"");

        tv_soluong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(v.getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
                dialog.setContentView(R.layout.layout_sua_soluong);
                dialog.setTitle("Sửa số lượng");
                Button btn_ok = dialog.findViewById(R.id.btn_oksl);
                Button btn_cancel = dialog.findViewById(R.id.btn_cancelsl);
                EditText ed_sl = dialog.findViewById(R.id.ed_sua_sls);
                ed_sl.setText(tv_soluong.getText().toString());
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ed_sl.getText().toString().length()==0){
                            Toast.makeText(v.getContext(), "Vui lòng nhập", Toast.LENGTH_SHORT).show();
                        } else {
                            objSach.setSoluong_ph25202(Integer.parseInt(ed_sl.getText().toString()));
                            int res = sachDAO.update(objSach);
                            if (res > -1){
                                listSach.set(index, objSach);
                                notifyDataSetChanged();
                                Toast.makeText(v.getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(v.getContext(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
                            } dialog.dismiss();
                        }

                    }
                });
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.create();
                dialog.show();
            }
        });
        img_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(v.getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
                dialog.setContentView(R.layout.dialog_them_sua_sach);
                dialog.setTitle("Sửa");
                Button btn_ok = dialog.findViewById(R.id.btn_ok_them_sua_sach);
                Button btn_cancel = dialog.findViewById(R.id.btn_huy_them_sua_sach);
                EditText ed_ten = dialog.findViewById(R.id.ed_them_sua_tensach);
                EditText ed_gia = dialog.findViewById(R.id.ed_them_sua_giasach);
                EditText ed_tacgia = dialog.findViewById(R.id.ed_them_sua_tacgia);
                EditText ed_soluong = dialog.findViewById(R.id.ed_them_sua_soluongsach);
                Spinner spinner = dialog.findViewById(R.id.spinner_loaisach);

                loaiSachDAO = new LoaiSachDAO(dialog.getContext());
                loaiSachDAO.open();
                ArrayList<LoaiSach> listLSach = loaiSachDAO.selectAll();
                LoaiSachSpinner loaiSachSpinner = new LoaiSachSpinner(listLSach);
                spinner.setAdapter(loaiSachSpinner);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        objLSach = listLSach.get(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        objLSach = listLSach.get(0);
                    }
                });

                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ed_ten.getText().toString().length()==0){
                            Toast.makeText(v.getContext(), "Không để trống tên", Toast.LENGTH_SHORT).show();
                        } else if (ed_gia.getText().toString().length()==0){
                            Toast.makeText(v.getContext(), "Không để trống giá", Toast.LENGTH_SHORT).show();
                        } else {
                            objSach.setTen(ed_ten.getText().toString());
                            objSach.setGia(ed_gia.getText().toString());
                            objSach.setLoaisach(objLSach.getTen());
                            objSach.setTacgia(ed_tacgia.getText().toString());
                            objSach.setSoluong_ph25202(Integer.parseInt(ed_soluong.getText().toString()));
                            int res = sachDAO.update(objSach);
                            if (res > -1){
                                listSach.set(index, objSach);
                                notifyDataSetChanged();
                                Toast.makeText(v.getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(v.getContext(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
                            } dialog.dismiss();
                        }
                    }
                });
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.create();
                dialog.show();
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
                        int kq = sachDAO.delete(objSach);
                        if (kq > -1){
                            listSach.remove(index);
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

    public void setDataSach(ArrayList<Sach> arrayList){
        this.listSach = arrayList;
        notifyDataSetChanged();
    }
}
