package com.example.blue.gasshop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.blue.gasshop.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DTNgayActivity extends AppCompatActivity {
    TextView txtTongtien;
    //    ListView lvDTNgay;
    BarChart barChart;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dtngay);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtTongtien = (TextView) findViewById(R.id.txtTongtien);
//        lvDTNgay = (ListView) findViewById(R.id.lvDTNgay);
        barChart = (BarChart) findViewById(R.id.barDTNgay);
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(5000000f, 0));
        barEntries.add(new BarEntry(4000000f, 1));
        BarDataSet barDataSet = new BarDataSet(barEntries, "Dates");

        barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

        ArrayList<String> theDates = new ArrayList<>();
        Date today = new Date(System.currentTimeMillis());
        SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy");

        String PreDay = timeFormat.format(today.getTime( ) );
        String NowDay = timeFormat.format(today.getTime());
        theDates.add(PreDay);
        theDates.add(NowDay);


        BarData theDate = new BarData(theDates, barDataSet);
        barChart.setData(theDate);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);


    }

}
