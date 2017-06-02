package com.example.blue.gasshop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.blue.gasshop.Database.Database;
import com.example.blue.gasshop.Database.DatabaseManager;
import com.example.blue.gasshop.DonHangFirebase;
import com.example.blue.gasshop.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

        Calendar c = Calendar.getInstance();
        Date d=c.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String homnay=dateFormat.format(d);
        c.add(Calendar.DATE, -1);
        d=c.getTime();
        String homqua=dateFormat.format(d);

        barChart = (BarChart) findViewById(R.id.barDTNgay);
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(getDoanhThu(homnay), 0));
        barEntries.add(new BarEntry(getDoanhThu(homqua), 1));
        BarDataSet barDataSet = new BarDataSet(barEntries, "Dates");

        barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

        ArrayList<String> theDates = new ArrayList<>();
        theDates.add(homqua);
        theDates.add(homnay);

        BarData theDate = new BarData(theDates, barDataSet);
        barChart.setData(theDate);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);



    }

    private Long getDoanhThu(String date) {
        Long tien = 0l;
        DatabaseManager databaseManager = new DatabaseManager(this);
        List<DonHangFirebase> donHangFirebaseList = databaseManager.getAllData(Database.TAB_THONGKE, date);
        for (DonHangFirebase donHangFirebase : donHangFirebaseList) {
            tien = tien + donHangFirebase.getTongtien();
        }
        return tien;
    }

}
