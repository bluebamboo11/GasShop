package com.example.blue.gasshop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.blue.gasshop.Adapter.ThongKeAdapter;
import com.example.blue.gasshop.Database.Database;
import com.example.blue.gasshop.Database.DatabaseManager;
import com.example.blue.gasshop.DonHangFirebase;
import com.example.blue.gasshop.R;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class ThongkeActivity extends AppCompatActivity {

    private DonHangFirebase donHangFirebase;
    private ListView listView;
    private ThongKeAdapter thongKeAdapter;
    private List<DonHangFirebase> donHangFirebaseArrayList;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle("Thống Kế");
        getSupportActionBar().setElevation(0f);


        DatabaseManager manager = new DatabaseManager(this);


//        initData();
        listView = (ListView) findViewById(R.id.lvThongke);
        donHangFirebaseArrayList = manager.getAllData(Database.TAB_THONGKE);
        thongKeAdapter = new ThongKeAdapter(this, R.layout.list_thongke, donHangFirebaseArrayList);
        listView.setAdapter(thongKeAdapter);

    }


//    public void initData() {
//        DonHangFirebase donHangFirebase = (DonHangFirebase) getIntent().getSerializableExtra("donhang");
//        TextView txtTenHang = (TextView) findViewById(R.id.txtTenHang);
//        txtTenHang.setText(donHangFirebase.sanpham);
//        TextView txtTongtien = (TextView) findViewById(R.id.txtTien);
//        txtTongtien.setText(donHangFirebase.tongtien);
//        TextView txtThoigian = (TextView) findViewById(R.id.txtThoigian);
//        txtThoigian.setText("" + donHangFirebase.ngaymua);
//        TextView txtKQ = (TextView) findViewById(R.id.txtKQ);
//        //        String[] sanPhams = donHangFirebase.idSanPham.split(",");
//
//    }

}
