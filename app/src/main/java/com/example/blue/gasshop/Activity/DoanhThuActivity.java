package com.example.blue.gasshop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.blue.gasshop.R;

public class DoanhThuActivity extends AppCompatActivity {

    Button DTThang, DTNgay, DTTong, KHTop, KHHuy, KHTT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doanh_thu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle("Doanh Thu");
        getSupportActionBar().setElevation(0f);
        setSupportActionBar(toolbar);

        DTNgay = (Button) findViewById(R.id.btDTThang);
        DTThang = (Button) findViewById(R.id.btDTThang);
        DTTong = (Button) findViewById(R.id.btDTTong);
        KHTop = (Button) findViewById(R.id.btKhTop);
        KHTT = (Button) findViewById(R.id.btKHTT);
        KHHuy = (Button) findViewById(R.id.btKHhuy);
        final Intent intent = new Intent(this, DTNgayActivity.class);

        DTNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent);
            }
        });
        DTThang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        DTTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        KHTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        KHTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        KHHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }

}
