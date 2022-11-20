package com.example.assignment_duanmau;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
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

public class DoiMatKhau extends Fragment {
    SharedPreferences sharedPreferences;
    ThuThuDAO thuThuDAO;
    ArrayList<ThuThu> listTT;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.doi_mat_khau_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText ed_user = view.findViewById(R.id.ed_userchange);
        EditText ed_passcu = view.findViewById(R.id.ed_passcu);
        EditText ed_passmoi = view.findViewById(R.id.ed_passmoi);
        EditText ed_passmoi2 = view.findViewById(R.id.ed_xnpassmoi);
        Button btn_ok = view.findViewById(R.id.btn_updatepass);

        ImageView img_passcu = view.findViewById(R.id.img_eyepasscu);
        ImageView img_passmoi = view.findViewById(R.id.img_eyepassmoi);
        ImageView img_xnpassmoi = view.findViewById(R.id.img_xneyepassmoi);

        sharedPreferences = getContext().getSharedPreferences("user_pass", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("k_user","");
        String pass = sharedPreferences.getString("k_pass","");

        ed_user.setText(user);
        ed_passcu.setText(pass);
        ed_user.setEnabled(false);
        ed_passcu.setEnabled(false);

        thuThuDAO = new ThuThuDAO(getContext());
        thuThuDAO.open();
        listTT = thuThuDAO.selectAll();

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_user.getText().toString().length()==0){
                    Toast.makeText(getContext(), "Không để trống tên đăng nhập", Toast.LENGTH_SHORT).show();
                } else if (ed_passcu.getText().toString().length()==0){
                    Toast.makeText(getContext(), "Không để trống mật khẩu cũ", Toast.LENGTH_SHORT).show();
                } else if (ed_passmoi.getText().toString().length()==0){
                    Toast.makeText(getContext(), "Không để trống mật khẩu mới", Toast.LENGTH_SHORT).show();
                } else if (ed_passmoi2.getText().toString().length()==0){
                    Toast.makeText(getContext(), "Không để trống mật khẩu mới", Toast.LENGTH_SHORT).show();
                } else if (ed_user.getText().toString().equals("admin")){
                    Toast.makeText(getContext(), "Tài khoản admin không thể đổi mật khẩu", Toast.LENGTH_SHORT).show();
                } else if (!ed_passmoi.getText().toString().equals(ed_passmoi2.getText().toString())){
                    Toast.makeText(getContext(), "Mật khẩu mới nhập lại không trùng", Toast.LENGTH_SHORT).show();
                } else if (!ed_passcu.getText().toString().equals(pass)){
                    Toast.makeText(getContext(), "Mật khẩu cũ không trùng", Toast.LENGTH_SHORT).show();
                } else {
                    for (ThuThu objTT : listTT){
                        if (ed_user.getText().toString().equals(objTT.getTen())&&ed_passcu.getText().toString().equals(objTT.getMatkhau())){
                            objTT.setMatkhau(ed_passmoi.getText().toString());
                            thuThuDAO.update(objTT);
                            Toast.makeText(getContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    
                }
            }
        });
        img_passcu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_passcu.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                    ((ImageView)(v)).setImageResource(R.drawable.eye);
                    ed_passcu.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    ((ImageView)(v)).setImageResource(R.drawable.xeye);
                    ed_passcu.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        img_passmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_passmoi.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                    ((ImageView)(v)).setImageResource(R.drawable.eye);
                    ed_passmoi.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    ((ImageView)(v)).setImageResource(R.drawable.xeye);
                    ed_passmoi.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        img_xnpassmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_passmoi2.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                    ((ImageView)(v)).setImageResource(R.drawable.eye);
                    ed_passmoi2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    ((ImageView)(v)).setImageResource(R.drawable.xeye);
                    ed_passmoi2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }
}
