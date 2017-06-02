package com.example.blue.gasshop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

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

public class DTThang extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dtthang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        BarChart barChart=(BarChart) findViewById(R.id.barDTThang);
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yyyy", Locale.getDefault());
        ArrayList<String> theDates = new ArrayList<>();
        ArrayList<Long> doanhthu=new ArrayList<>();

               for(int i=0;i<6;i++){
                   Calendar c = Calendar.getInstance();


                             c.add(Calendar.MONTH, i-5);
            Date d=c.getTime();
                   Log.e("date",dateFormat.format(c.getTime()));
            theDates.add(dateFormat.format(d));
            doanhthu.add(getDoanhThu(dateFormat.format(d)));


        }


       for(int i=5;i>=0;i--){
           barEntries.add(new BarEntry(doanhthu.get(i), i));
           Log.e("date",doanhthu.get(i)+"");
       }
        BarDataSet barDataSet = new BarDataSet(barEntries, "Dates");
        barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        BarData theDate = new BarData(theDates, barDataSet);
        barChart.setData(theDate);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
    }
    private Long getDoanhThu(String date) {
        Long tien = 0l;
        DatabaseManager databaseManager = new DatabaseManager(this);
        List<DonHangFirebase> donHangFirebaseList = databaseManager.getAllDataThang(Database.TAB_THONGKE, date);
        for (DonHangFirebase donHangFirebase : donHangFirebaseList) {
            tien = tien + donHangFirebase.getTongtien();
        }
        return tien;
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
}
