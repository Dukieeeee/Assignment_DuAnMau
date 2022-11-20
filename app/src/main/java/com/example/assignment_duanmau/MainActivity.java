package com.example.assignment_duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.assignment_duanmau.LoaiSach.LoaiSachFragment;
import com.example.assignment_duanmau.PhieuMuon.PhieuMuonFragment;
import com.example.assignment_duanmau.Sach.SachFragment;
import com.example.assignment_duanmau.ThanhVien.ThanhVienFragment;
import com.example.assignment_duanmau.ThongKe.TongDoanhThu;
import com.example.assignment_duanmau.ThongKe.Top10Fragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    Button btn_tk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.main_layout);
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        btn_tk = findViewById(R.id.btn_tk);
        btn_tk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getBaseContext(), TimKiemActivity.class);
                startActivity(i1);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu_1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        toggle.syncState();
        SharedPreferences sharedPreferences = getSharedPreferences("user_pass",MODE_PRIVATE);
        String user_1 = sharedPreferences.getString("k_user", "");
        navigationView = findViewById(R.id.main_navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_thanhvien:
                        thayFragment(new ThanhVienFragment());
                        break;
                    case R.id.item_loaisach:
                        thayFragment(new LoaiSachFragment());
                        break;
                    case R.id.item_sach:
                        thayFragment(new SachFragment());
                        break;
                    case R.id.item_phieumuon:
                        thayFragment(new PhieuMuonFragment());
                        break;
                    case R.id.item_tkdoanhthu:
                        thayFragment(new TongDoanhThu());
                        break;
                    case R.id.item_tktop10:
                        thayFragment(new Top10Fragment());
                        break;
                    case R.id.item_themnguoidung:
                        if (user_1.equals("admin")){
                            thayFragment(new ThemNguoiDung());
                        } else {
                            Toast.makeText(MainActivity.this, "Chỉ admin mới được sử dụng", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.item_doimatkhau:
                        thayFragment(new DoiMatKhau());
                        break;
                    case R.id.item_thoat:
                        AlertDialog.Builder builder =  new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Thoát");
                        builder.setMessage("Thoát ứng dụng?");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MainActivity.this.finish();
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.create();
                        builder.show();
                }
                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        });
    }
    public void thayFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_content, fragment);
        transaction.commit();
    }
}