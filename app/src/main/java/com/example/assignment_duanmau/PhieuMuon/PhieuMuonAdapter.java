package com.example.assignment_duanmau.PhieuMuon;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment_duanmau.R;
import com.example.assignment_duanmau.Sach.Sach;
import com.example.assignment_duanmau.Sach.SachDAO;
import com.example.assignment_duanmau.Sach.SachSpinner;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

public class PhieuMuonAdapter extends BaseAdapter {
    SachDAO sachDAO;
    PhieuMuonDAO phieuMuonDAO;
    ArrayList<PhieuMuon> listPMuon;
    Sach objSach = new Sach();

    public PhieuMuonAdapter(PhieuMuonDAO phieuMuonDAO, ArrayList<PhieuMuon> listPMuon) {
        this.phieuMuonDAO = phieuMuonDAO;
        this.listPMuon = listPMuon;
    }

    @Override
    public int getCount() {
        return listPMuon.size();
    }

    @Override
    public Object getItem(int position) {
        PhieuMuon objPMuon = listPMuon.get(position);
        return objPMuon;
    }

    @Override
    public long getItemId(int position) {
        PhieuMuon objPMuon = listPMuon.get(position);
        return objPMuon.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if (convertView == null){
            v = View.inflate(parent.getContext(), R.layout.dong_phieu_muon, null);

        } else v = convertView;

        final PhieuMuon objPMuon = listPMuon.get(position);
        final int index = position;

        TextView tv_id = v.findViewById(R.id.tv_idphieumuon);
        TextView tv_ten = v.findViewById(R.id.tv_tenphieumuon);
        TextView tv_ngay = v.findViewById(R.id.tv_ngaymuon);
        TextView tv_gia = v.findViewById(R.id.tv_giaphieumuon);
        ImageView img_sua = v.findViewById(R.id.img_suaphieumuon);
        ImageView img_xoa = v.findViewById(R.id.img_xoaphieumuon);

        tv_id.setText(objPMuon.getId()+"");
        tv_ten.setText(objPMuon.getTen()+"");
        tv_ngay.setText(objPMuon.getNgaymuon()+"");
        tv_gia.setText(objPMuon.getGia()+"");

        tv_ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Th??ng tin chi ti???t");
                builder.setMessage("T??n phi???u m?????n: "+objPMuon.getTen()+"\n"+"T??n s??ch: "+objPMuon.getTensach()+"\n"+"Ng??y m????n: "
                +objPMuon.getNgaymuon()+"\n"+"Gi??: "+objPMuon.getGia());
                builder.create();
                builder.show();
            }
        });

        img_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(v.getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
                dialog.setContentView(R.layout.dialog_them_sua_phieu_muon);
                dialog.setTitle("S???a");

                EditText ed_ten = dialog.findViewById(R.id.ed_them_sua_tenphieumuon);

                Spinner spinner = dialog.findViewById(R.id.spinner_sach);
                sachDAO = new SachDAO(dialog.getContext());
                sachDAO.open();
                ArrayList<Sach> listSach = sachDAO.selectAll();
                SachSpinner sachSpinner = new SachSpinner(listSach);
                spinner.setAdapter(sachSpinner);

                TextView tv_date = dialog.findViewById(R.id.tv_them_sua_ngaymuon);
                ImageView img_date = dialog.findViewById(R.id.img_date);

                Button btn_ok = dialog.findViewById(R.id.btn_ok_them_sua_phieu_muon);
                Button btn_cancel = dialog.findViewById(R.id.btn_huy_them_sua_phieu_muon);

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
                img_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                tv_date.setText(year+"-"+(month+1)+"-"+dayOfMonth);
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
                            Toast.makeText(dialog.getContext(), "Kh??ng ????? tr???ng t??n", Toast.LENGTH_SHORT).show();
                        } else if (tv_date.getText().toString().length()==0){
                            Toast.makeText(dialog.getContext(), "Kh??ng ????? tr???ng ng??y", Toast.LENGTH_SHORT).show();
                        } else {
                            objPMuon.setTen(ed_ten.getText().toString());
                            objPMuon.setTensach(objSach.getTen());
                            objPMuon.setGia(objSach.getGia());
                            objPMuon.setNgaymuon(tv_date.getText().toString());
                            int res = phieuMuonDAO.update(objPMuon);
                            if (res > -1){
                                listPMuon.set(index, objPMuon);
                                notifyDataSetChanged();
                                Toast.makeText(dialog.getContext(), "S???a th??nh c??ng", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(dialog.getContext(), "S???a th???t b???i", Toast.LENGTH_SHORT).show();
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
        img_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("X??a");
                builder.setMessage("X??c nh???n x??a?");
                builder.setPositiveButton("X??a", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int kq = phieuMuonDAO.delete(objPMuon);
                        if (kq > -1){
                            listPMuon.remove(index);
                            notifyDataSetChanged();
                            Toast.makeText(parent.getContext(), "X??a th??nh c??ng", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(parent.getContext(), "X??a th???t b???i", Toast.LENGTH_SHORT).show();
                        } dialog.dismiss();
                    }
                });
                builder.setNegativeButton("H???y", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create();
                builder.show();
            }
        });
        return v;
    }
    public void setDataPhieuMuon(ArrayList<PhieuMuon> arrayList){
        this.listPMuon = arrayList;
        notifyDataSetChanged();
    }
}
