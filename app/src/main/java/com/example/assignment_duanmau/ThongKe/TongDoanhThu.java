package com.example.assignment_duanmau.ThongKe;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assignment_duanmau.PhieuMuon.PhieuMuon;
import com.example.assignment_duanmau.PhieuMuon.PhieuMuonDAO;
import com.example.assignment_duanmau.R;

import java.util.ArrayList;
import java.util.Calendar;

public class TongDoanhThu extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.doanhthu_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv_sdate = view.findViewById(R.id.tv_ngaybatdau);
        TextView tv_edate = view.findViewById(R.id.tv_ngayketthuc);
        ImageView img_sdate = view.findViewById(R.id.img_ngaybatdau);
        ImageView img_edate = view.findViewById(R.id.img_ngayketthuc);
        Button btn_thongke = view.findViewById(R.id.btn_thongketop10);
        TextView tv_tong = view.findViewById(R.id.tv_tongdoanhthu);

        PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(getContext());
        phieuMuonDAO.open();

        img_sdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        if (month+1 < 10 && dayOfMonth>10){
                            tv_sdate.setText(year+"-"+"0"+(month+1)+"-"+dayOfMonth);
                        } else if (dayOfMonth<10 && month+1 > 10){
                            tv_sdate.setText(year+"-"+(month+1)+"-"+"0"+dayOfMonth);
                        } else if (dayOfMonth<10 && month+1 < 10){
                            tv_sdate.setText(year+"-"+"0"+(month+1)+"-"+"0"+dayOfMonth);
                        }else {
                            tv_sdate.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                        }
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)
                );
                datePickerDialog.show();
            }
        });
        img_edate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        if (month+1 < 10 && dayOfMonth>10){
                            tv_edate.setText(year+"-"+"0"+(month+1)+"-"+dayOfMonth);
                        } else if (dayOfMonth<10 && month+1 > 10){
                            tv_edate.setText(year+"-"+(month+1)+"-"+"0"+dayOfMonth);
                        } else if (dayOfMonth<10 && month+1 < 10){
                            tv_edate.setText(year+"-"+"0"+(month+1)+"-"+"0"+dayOfMonth);
                        }else {
                            tv_edate.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                        }
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)
                );
                datePickerDialog.show();
            }
        });
        btn_thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdate = tv_sdate.getText().toString();
                String edate = tv_edate.getText().toString();
                ArrayList<PhieuMuon> list = phieuMuonDAO.doanhThu(sdate,edate);
                int tong = 0;
                for (int i=0; i<list.size();i++){
                    tong = tong + Integer.parseInt(list.get(i).getGia());
                }
                tv_tong.setText("Tổng: " + tong + "VND");
            }
        });
    }
}
