package com.example.blue.gasshop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import com.example.blue.gasshop.Adapter.ThongKeAdapter;
import com.example.blue.gasshop.DonHangFirebase;
import com.example.blue.gasshop.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ThongkeActivity extends AppCompatActivity {

    private DonHangFirebase donHangFirebase;
    private ListView listView;
    private ThongKeAdapter thongKeAdapter;
    private ArrayList<DonHangFirebase> donHangFirebaseArrayList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setElevation(0f);
        setSupportActionBar(toolbar);
//        initData();
        listView = (ListView) findViewById(R.id.lvThongke);
        donHangFirebaseArrayList = new ArrayList<>();
        thongKeAdapter = new ThongKeAdapter(this, android.R.layout.simple_list_item_1, donHangFirebaseArrayList);
        listView.setAdapter(thongKeAdapter);

    }

    public void initData() {
        DonHangFirebase donHangFirebase = (DonHangFirebase) getIntent().getSerializableExtra("donhang");
        TextView txtTenHang = (TextView) findViewById(R.id.txtTenHang);
        txtTenHang.setText(donHangFirebase.sanpham);
        TextView txtTongtien = (TextView) findViewById(R.id.txtTien);
        txtTongtien.setText(donHangFirebase.tongtien);
        TextView txtThoigian = (TextView) findViewById(R.id.txtThoigian);
        txtThoigian.setText(""+donHangFirebase.ngaymua);
        TextView txtKQ = (TextView) findViewById(R.id.txtKQ);
        txtKQ.setText(""+donHangFirebase.KQ);
//        String[] sanPhams = donHangFirebase.idSanPham.split(",");

    }

}
