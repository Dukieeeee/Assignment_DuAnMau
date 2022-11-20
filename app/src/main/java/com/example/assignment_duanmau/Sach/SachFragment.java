package com.example.assignment_duanmau.Sach;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assignment_duanmau.LoaiSach.LoaiSach;
import com.example.assignment_duanmau.LoaiSach.LoaiSachDAO;
import com.example.assignment_duanmau.LoaiSach.LoaiSachSpinner;
import com.example.assignment_duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SachFragment extends Fragment {
    FloatingActionButton floatingActionButton;
    SachAdapter sachAdapter;
    SachDAO sachDAO;
    ListView listView;
    LoaiSachDAO loaiSachDAO;
    ArrayList<Sach> listSach = new ArrayList<>();
    LoaiSach objLSach = new LoaiSach();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sach_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        floatingActionButton = view.findViewById(R.id.btn_addsach);
        listView = view.findViewById(R.id.lv_sach);
        sachDAO = new SachDAO(view.getContext());
        sachDAO.open();
        sachAdapter = new SachAdapter(sachDAO, sachDAO.selectAll());
        listView.setAdapter(sachAdapter);
        LayoutInflater inflater = this.getLayoutInflater();
        View v1 = inflater.inflate(R.layout.dialog_them_sua_sach, null);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v1.getParent() != null) {
                    ((ViewGroup) v1.getParent()).removeAllViews();
                }
                Dialog dialog = new Dialog(v1.getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
                dialog.setContentView(v1);
                dialog.setTitle("Thêm");
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
                            Toast.makeText(getContext(), "Không để trống tên", Toast.LENGTH_SHORT).show();
                        } else if (ed_gia.getText().toString().length()==0){
                            Toast.makeText(getContext(), "Không để trống giá", Toast.LENGTH_SHORT).show();
                        } else {
                            Sach objSach = new Sach();
                            objSach.setTen(ed_ten.getText().toString());
                            objSach.setGia(ed_gia.getText().toString());
                            objSach.setLoaisach(objLSach.getTen());
                            objSach.setTacgia(ed_tacgia.getText().toString());
                            objSach.setSoluong_ph25202(Integer.parseInt(ed_soluong.getText().toString()));
                            long res = sachDAO.insert(objSach);
                            if (res > -1){
                                listSach.clear();
                                listSach.addAll(sachDAO.selectAll());
                                sachAdapter.setDataSach(listSach);
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
                dialog.create();
                dialog.show();
            }
        });
    }
}
