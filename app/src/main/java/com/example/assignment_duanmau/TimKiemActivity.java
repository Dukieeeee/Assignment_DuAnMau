package com.example.assignment_duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.assignment_duanmau.Sach.Sach;
import com.example.assignment_duanmau.Sach.SachAdapter;
import com.example.assignment_duanmau.Sach.SachDAO;

import java.util.ArrayList;

public class TimKiemActivity extends AppCompatActivity {
    EditText ed_tk;
    Button btn_tk;
    ListView lv_tk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        ed_tk = findViewById(R.id.ed_tk);
        btn_tk = findViewById(R.id.btn_tk2);
        lv_tk = findViewById(R.id.lv_tk);
        SachDAO sachDAO = new SachDAO(getApplicationContext());
        sachDAO.open();
        btn_tk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = ed_tk.getText().toString();
                ArrayList<Sach> list = sachDAO.getSoluong(a);
                SachAdapter adapter = new SachAdapter(sachDAO, list);
                lv_tk.setAdapter(adapter);
            }
        });
    }
}