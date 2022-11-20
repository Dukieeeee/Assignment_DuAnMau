package com.example.assignment_duanmau.PhieuMuon;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assignment_duanmau.R;
import com.example.assignment_duanmau.Sach.Sach;
import com.example.assignment_duanmau.Sach.SachDAO;
import com.example.assignment_duanmau.Sach.SachSpinner;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class PhieuMuonFragment extends Fragment {
    FloatingActionButton floatingActionButton;
    ListView listView;
    ArrayList<PhieuMuon> listPMuon = new ArrayList<>();
    PhieuMuonDAO phieuMuonDAO;
    PhieuMuonAdapter phieuMuonAdapter;
    Sach objSach = new Sach();
    SachDAO sachDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.phieu_muon_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        floatingActionButton = view.findViewById(R.id.btn_addphieumuon);
        listView = view.findViewById(R.id.lv_phieumuon);
        phieuMuonDAO = new PhieuMuonDAO(view.getContext());
        phieuMuonDAO.open();
        phieuMuonAdapter = new PhieuMuonAdapter(phieuMuonDAO, phieuMuonDAO.selectAll());
        listView.setAdapter(phieuMuonAdapter);
        LayoutInflater inflater = this.getLayoutInflater();
        View v1 = inflater.inflate(R.layout.dialog_them_sua_phieu_muon, null);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v1.getParent() != null) {
                    ((ViewGroup) v1.getParent()).removeAllViews();
                }
                Dialog dialog = new Dialog(v.getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
                dialog.setContentView(v1);
                dialog.setTitle("Thêm");
                Button btn_ok = dialog.findViewById(R.id.btn_ok_them_sua_phieu_muon);
                Button btn_cancel = dialog.findViewById(R.id.btn_huy_them_sua_phieu_muon);

                EditText ed_ten = dialog.findViewById(R.id.ed_them_sua_tenphieumuon);
                Spinner spinner = dialog.findViewById(R.id.spinner_sach);
                sachDAO = new SachDAO(dialog.getContext());
                sachDAO.open();
                ArrayList<Sach> listSach = sachDAO.selectAll();
                SachSpinner sachSpinner = new SachSpinner(listSach);
                spinner.setAdapter(sachSpinner);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        objSach = listSach.get(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        objSach = listSach.get(0);
                    }
                });

                ImageView img_date = dialog.findViewById(R.id.img_date);
                TextView tv_date = dialog.findViewById(R.id.tv_them_sua_ngaymuon);

                img_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                if (month+1 < 10 && dayOfMonth>10){
                                    tv_date.setText(year+"-"+"0"+(month+1)+"-"+dayOfMonth);
                                } else if (dayOfMonth<10 && month+1 > 10){
                                    tv_date.setText(year+"-"+(month+1)+"-"+"0"+dayOfMonth);
                                } else if (dayOfMonth<10 && month+1 < 10){
                                    tv_date.setText(year+"-"+"0"+(month+1)+"-"+"0"+dayOfMonth);
                                }else {
                                    tv_date.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                                }
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)
                        );
                        datePickerDialog.show();
                    }
                });

                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ed_ten.getText().toString().length()==0){
                            Toast.makeText(getContext(), "Không để trống tên", Toast.LENGTH_SHORT).show();
                        } else if (tv_date.getText().toString().length()==0){
                            Toast.makeText(getContext(), "Không để trống ngày", Toast.LENGTH_SHORT).show();
                        } else {
                            PhieuMuon objPMuon = new PhieuMuon();
                            objPMuon.setTen(ed_ten.getText().toString());
                            objPMuon.setTensach(objSach.getTen());
                            objPMuon.setGia(objSach.getGia());
                            objPMuon.setNgaymuon(tv_date.getText().toString());
                            long res = phieuMuonDAO.insert(objPMuon);
                            if (res > -1){
                                listPMuon.clear();
                                listPMuon.addAll(phieuMuonDAO.selectAll());
                                phieuMuonAdapter.setDataPhieuMuon(listPMuon);
                                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
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
                dialog.show();
            }
        });
    }
}
