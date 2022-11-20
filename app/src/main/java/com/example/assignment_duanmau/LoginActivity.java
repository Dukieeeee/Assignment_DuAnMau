package com.example.assignment_duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.assignment_duanmau.ThuThu.ThuThu;
import com.example.assignment_duanmau.ThuThu.ThuThuDAO;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    ArrayList<ThuThu> listTT = new ArrayList<>();
    ThuThuDAO thuThuDAO;
    EditText ed_user, ed_pass;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_user = findViewById(R.id.ed_userlogin);
        ed_pass = findViewById(R.id.ed_passlogin);
        Button btn_cancel = findViewById(R.id.btn_cancellogin);
        Button btn_ok = findViewById(R.id.btn_oklogin);
        ImageView img_eye = findViewById(R.id.img_eye1_login);
        thuThuDAO = new ThuThuDAO(getBaseContext());
        thuThuDAO.open();
//        ThuThu objTT = new ThuThu("thuthu1", "123456");
//        thuThuDAO.insert(objTT);
        listTT = thuThuDAO.selectAll();
        Boolean check = getDataThuThu();

        sharedPreferences = getSharedPreferences("user_pass", Context.MODE_PRIVATE);
        String user_1 = sharedPreferences.getString("k_user","");
        String user_2 = sharedPreferences.getString("k_pass","");



        ed_user.setText(user_1);
        ed_pass.setText(user_2);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_user.setText("");
                ed_pass.setText("");
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_user.getText().toString().length()==0){
                    Toast.makeText(LoginActivity.this, "Không để trống tên đăng nhập", Toast.LENGTH_SHORT).show();
                } else if (ed_pass.getText().toString().length()==0){
                    Toast.makeText(LoginActivity.this, "Không để trống mật khẩu", Toast.LENGTH_SHORT).show();
                } else if (!ed_user.getText().toString().equals("admin")&&!ed_pass.getText().toString().equals("123")&&check==true){
                    Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không hợp lệ", Toast.LENGTH_SHORT).show();
                } else {
                    String user = ed_user.getText().toString();
                    String pass = ed_pass.getText().toString();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("k_user",user);
                    editor.putString("k_pass",pass);
                    editor.commit();

                    Intent i1 = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i1);
                    LoginActivity.this.finish();
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
    }
    public boolean getDataThuThu(){
        thuThuDAO = new ThuThuDAO(getBaseContext());
        thuThuDAO.open();
        listTT = thuThuDAO.selectAll();
        for (ThuThu objTT : listTT){
            if (!ed_user.getText().toString().equals(objTT.getTen())&&!ed_pass.getText().toString().equals(objTT.getMatkhau())){
                return false;
            }
        }
        return true;
    }
}