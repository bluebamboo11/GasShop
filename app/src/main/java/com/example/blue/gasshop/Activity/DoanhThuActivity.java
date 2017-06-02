package com.example.blue.gasshop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.blue.gasshop.Database.Database;
import com.example.blue.gasshop.Database.DatabaseManager;
import com.example.blue.gasshop.DonHangFirebase;
import com.example.blue.gasshop.R;

import java.text.DecimalFormat;
import java.util.List;

public class DoanhThuActivity extends AppCompatActivity {

    Button DTThang, DTNgay, DTTong, KHTop, KHHuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doanh_thu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Doanh Thu");
        getSupportActionBar().setElevation(0f);
        setSupportActionBar(toolbar);

        DTNgay = (Button) findViewById(R.id.btDTNgay);
        DTThang = (Button) findViewById(R.id.btDTThang);
        DTTong = (Button) findViewById(R.id.btDTTong);
        KHTop = (Button) findViewById(R.id.btKhTop);

        KHHuy = (Button) findViewById(R.id.btKHhuy);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");
        DTTong.setText("Tổng Doanh Thu: " + decimalFormat.format(getDoanhThu()) + " VND");
        DTNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoanhThuActivity.this, DTNgayActivity.class);
                startActivity(intent);
            }
        });
        DTThang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoanhThuActivity.this, DTThang.class);
                startActivity(intent);
            }
        });

        KHTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoanhThuActivity.this, HangBanChayActivity.class));
            }
        });

        KHHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Long getDoanhThu() {
        Long tien = 0l;
        DatabaseManager databaseManager = new DatabaseManager(this);
        List<DonHangFirebase> donHangFirebaseList = databaseManager.getAllData(Database.TAB_THONGKE);
        for (DonHangFirebase donHangFirebase : donHangFirebaseList) {
            tien = tien + donHangFirebase.getTongtien();
        }
        return tien;
    }
}
