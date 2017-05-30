package com.example.blue.gasshop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.blue.gasshop.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class DTTongActivity extends AppCompatActivity {
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dttong);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        barChart = (BarChart) findViewById(R.id.barDTTong);
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(20000000f, 0));
        barEntries.add(new BarEntry(50000000f, 1));
        barEntries.add(new BarEntry(66000000f, 2));
        barEntries.add(new BarEntry(77000000f, 3));
        barEntries.add(new BarEntry(12000000f, 4));
        barEntries.add(new BarEntry(20000000f, 5));
        barEntries.add(new BarEntry(50000000f, 6));
        barEntries.add(new BarEntry(81000000f, 7));
        barEntries.add(new BarEntry(24000000f, 8));
        barEntries.add(new BarEntry(64000000f, 9));
        barEntries.add(new BarEntry(10000000f, 10));
        barEntries.add(new BarEntry(90000000f, 11));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Dates");

        barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

        ArrayList<String> theDates = new ArrayList<>();
        theDates.add("Tháng 1");
        theDates.add("Tháng 2");
        theDates.add("Tháng 3");
        theDates.add("Tháng 4");
        theDates.add("Tháng 5");
        theDates.add("Tháng 6");
        theDates.add("Tháng 7");
        theDates.add("Tháng 8");
        theDates.add("Tháng 9");
        theDates.add("Tháng 10");
        theDates.add("Tháng 11");
        theDates.add("Tháng 12");
        BarData barData = new BarData(theDates,barDataSet);

        barChart.setData(barData);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);


    }

}


