package com.example.assignment_duanmau.ThanhVien;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assignment_duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ThanhVienFragment extends Fragment {
    FloatingActionButton floatingActionButton;
    ListView listView;
    ThanhVienDAO thanhVienDAO;
    ThanhVienAdapter thanhVienAdapter;

    ArrayList<ThanhVien> listTV = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.thanh_vien_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        floatingActionButton = view.findViewById(R.id.btn_addthanhvien);
        listView = view.findViewById(R.id.lv_thanhvien);
        thanhVienDAO = new ThanhVienDAO(view.getContext());
        thanhVienDAO.open();
        thanhVienAdapter = new ThanhVienAdapter(thanhVienDAO,thanhVienDAO.selectAll());
        listView.setAdapter(thanhVienAdapter);
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View v1 = layoutInflater.inflate(R.layout.dialog_them_sua_thanhvien, null);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v1.getParent() != null) {
                    ((ViewGroup) v1.getParent()).removeAllViews();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(v1);
                builder.setTitle("Thêm");
                final EditText ed_ten = v1.findViewById(R.id.ed_them_sua_thanhvien);
                EditText ed_gioitinh = v1.findViewById(R.id.ed_gioitinh);
                ed_ten.setText("");
                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (ed_ten.getText().toString().length()==0){
                            Toast.makeText(getContext(), "Không để trống tên", Toast.LENGTH_SHORT).show();
                        } else {
                            ThanhVien objTV = new ThanhVien();
                            objTV.setTen(ed_ten.getText().toString());
                            objTV.setGioitinh_ph25202(Integer.parseInt(ed_gioitinh.getText().toString()));
                            long res = thanhVienDAO.insert(objTV);
                            if (res > -1){
                                listTV.clear();
                                listTV.addAll(thanhVienDAO.selectAll());
                                thanhVienAdapter.setDataThanhVien(listTV);
                                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            } dialog.dismiss();
                        }
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

    }
}
