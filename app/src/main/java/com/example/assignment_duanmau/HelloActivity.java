package com.example.assignment_duanmau;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class HelloActivity extends AppCompatActivity {
    EditText ed_hello;
    Button btn_hello;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        ed_hello = findViewById(R.id.ed_hello);
        btn_hello = findViewById(R.id.btn_hello);
//        LayoutInflater layoutInflater = this.getLayoutInflater();
//        View v1 = layoutInflater.inflate(R.layout.lay, null);
        btn_hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = ed_hello.getText().toString();
                if (a.equals("PH25202")){
                    Intent i1 = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(i1);
                }
//                else {
////                    if (v1.getParent() != null) {
////                        ((ViewGroup) v1.getParent()).removeAllViews();
////                    }
////                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
////                    builder.setView(v1);
////                    builder.setTitle("Thông báo");
////                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
////                        @Override
////                        public void onClick(DialogInterface dialog, int which) {
////                            dialog.dismiss();
////                        }
////                    });
////                    builder.create();
////                    builder.show();
//
////                    Dialog dialog = new Dialog(getApplicationContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
////                    dialog.setContentView(R.layout.layout_hello);
////                    dialog.setTitle("Thông báo");
////                    dialog.create();
////                    dialog.show();
//                }
            }
        });
//        start();
    }
//    public void start(){
//        Thread thread = new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                for(i=0; i<=3; i++){
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    if (i == 3){
//                        Intent i1 = new Intent(getBaseContext(), LoginActivity.class);
//                        startActivity(i1);
//                        HelloActivity.this.finish();
//                    }
//                }
//
//            }
//        };
//        thread.start();
//    }
}