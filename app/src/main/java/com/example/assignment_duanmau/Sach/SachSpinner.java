package com.example.assignment_duanmau.Sach;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.assignment_duanmau.R;

import java.util.ArrayList;

public class SachSpinner extends BaseAdapter {
    ArrayList<Sach> listSach;

    public SachSpinner(ArrayList<Sach> listSach) {
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
        if (convertView == null){
            v = View.inflate(parent.getContext(), R.layout.spinner_sach, null);

        } else v = convertView;

        final Sach objSach = listSach.get(position);

        TextView tv_ten = v.findViewById(R.id.tv_spinnerTenSach);
        TextView tv_gia = v.findViewById(R.id.tv_spinnerGiaSach);

        tv_ten.setText(objSach.getTen()+"");
        tv_gia.setText(objSach.getGia()+"");

        return v;
    }
}
