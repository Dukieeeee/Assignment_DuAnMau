package com.example.assignment_duanmau;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assignment_duanmau.ThuThu.ThuThu;
import com.example.assignment_duanmau.ThuThu.ThuThuDAO;

import java.util.ArrayList;

public class ThemNguoiDung extends Fragment {
    ThuThuDAO thuThuDAO;
    ArrayList<ThuThu> listTT;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.them_nguoi_dung_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText ed_user = view.findViewById(R.id.ed_adduser);
        EditText ed_pass = view.findViewById(R.id.ed_addpass);
        EditText ed_pass2 = view.findViewById(R.id.ed_addpass2);
        ImageView img_eye = view.findViewById(R.id.img_eye1);
        ImageView img_eye2 = view.findViewById(R.id.img_eye2);
        Button btn_ok = view.findViewById(R.id.btn_addthuthu);
        Button btn_cancel = view.findViewById(R.id.btn_cancelthuthu);

        thuThuDAO = new ThuThuDAO(getContext());
        thuThuDAO.open();

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_user.setText("");
                ed_pass.setText("");
                ed_pass2.setText("");
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_user.getText().toString().length()==0){
                    Toast.makeText(getContext(), "Không để trống tên đăng nhập", Toast.LENGTH_SHORT).show();
                } else if (ed_pass.getText().toString().length()==0){
                    Toast.makeText(getContext(), "Không để trống mật khẩu", Toast.LENGTH_SHORT).show();
                } else if (ed_pass2.getText().toString().length()==0){
                    Toast.makeText(getContext(), "Nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
                } else if (!ed_pass.getText().toString().equals(ed_pass2.getText().toString())){
                    Toast.makeText(getContext(), "Mật khẩu nhập lại không trùng", Toast.LENGTH_SHORT).show();
                } else {
                    ThuThu objTT = new ThuThu();
                    objTT.setTen(ed_user.getText().toString());
                    objTT.setMatkhau(ed_pass.getText().toString());
                    thuThuDAO.insert(objTT);
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }
                }

        });

        img_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_pass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                    ((ImageView)(v)).setImageResource(R.drawable.eye);
                    ed_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    ((ImageView)(v)).setImageResource(R.drawable.xeye);
                    ed_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        img_eye2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_pass2.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                    ((ImageView)(v)).setImageResource(R.drawable.eye);
                    ed_pass2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    ((ImageView)(v)).setImageResource(R.drawable.xeye);
                    ed_pass2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }
}
