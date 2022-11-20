package com.example.assignment_duanmau.LoaiSach;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.assignment_duanmau.R;

import java.util.ArrayList;

public class LoaiSachSpinner extends BaseAdapter {

    ArrayList<LoaiSach> listLSach;

    public LoaiSachSpinner(ArrayList<LoaiSach> listLSach) {
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
        if (convertView == null){
            v = View.inflate(parent.getContext(), R.layout.spinner_loai_sach, null);

        } else v = convertView;

        final LoaiSach objLSach = listLSach.get(position);

        TextView tv_spinnerloaisach = v.findViewById(R.id.tv_spinnerloaisach);

        tv_spinnerloaisach.setText(objLSach.getTen()+"");

        return v;
    }
}
