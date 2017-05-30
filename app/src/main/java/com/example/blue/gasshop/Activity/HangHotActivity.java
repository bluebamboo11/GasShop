package com.example.blue.gasshop.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.blue.gasshop.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class HangHotActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    PieChart pieBepgasHot, pieBinhGasHot, piePhukienHot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hang_hot);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tbHangHot);
        setSupportActionBar(toolbar);

        pieBepgasHot = (PieChart) findViewById(R.id.pieBepGasHot);
        pieBinhGasHot = (PieChart) findViewById(R.id.pieBinhGasHot);
        piePhukienHot = (PieChart) findViewById(R.id.piePhuKienHot);
        pieBepgasHot.setUsePercentValues(false);
//        pieBinhGasHot.setUsePercentValues(true);
//        piePhukienHot.setUsePercentValues(true);
        BepGasHot();
        BinhGasHot();
        PhuKienHot();

    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getVal() + ", xIndex: " + e.getXIndex()
                        + ", DataSet index: " + dataSetIndex);
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

    public void BepGasHot() {

        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(8f, 0));
        yvalues.add(new Entry(15f, 1));
        yvalues.add(new Entry(12f, 2));
        yvalues.add(new Entry(25f, 3));
        yvalues.add(new Entry(23f, 4));


        PieDataSet dataSet = new PieDataSet(yvalues, "Doanh Thu");

        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("Bếp Gas Giovani GD2000");
        xVals.add("Bếp Gas Kwashaki 292");
        xVals.add("Bếp Gas Franch 9792D");
        xVals.add("Bếp Gas Italy 39323");
        xVals.add("Bếp Gas Việt Nam VN2017");


        PieData data = new PieData(xVals, dataSet);
//        PieData data = new PieData( );
//        data.setValueFormatter(  new DefaultValueFormatter(0));
        pieBepgasHot.setData(data);
        pieBepgasHot.setDescription(null);

//        pieBepgasHot.setDescription("Biểu đồ bếp gas bán chạy");
//        pieBepgasHot.set
        pieBepgasHot.setDrawHoleEnabled(true);
        pieBepgasHot.setTransparentCircleRadius(25f);
        pieBepgasHot.setHoleRadius(25f);
        pieBepgasHot.setRotationEnabled(false);
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        data.setValueTextSize(6f);
        data.setValueTextColor(Color.DKGRAY);
        pieBepgasHot.setOnChartValueSelectedListener(this);
        Legend legend = pieBepgasHot.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
//        pieBepgasHot.animateXY(1400, 1400);

    }

    public void BinhGasHot() {

        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(8f, 0));
        yvalues.add(new Entry(15f, 1));
        yvalues.add(new Entry(12f, 2));
        yvalues.add(new Entry(25f, 3));
        yvalues.add(new Entry(23f, 4));
        PieDataSet dataSet = new PieDataSet(yvalues, "Doanh Thu");

        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("Bếp Gas Giovani GD2000");
        xVals.add("Bếp Gas Kwashaki 292");
        xVals.add("Bếp Gas Franch 9792D");
        xVals.add("Bếp Gas Italy 39323");
        xVals.add("Bếp Gas Việt Nam VN2017");


        PieData data = new PieData(xVals, dataSet);
//
        pieBinhGasHot.setData(data);
//        pieBinhGasHot.setDescription("Biểu đồ bếp gas bán chạy");

        pieBinhGasHot.setDrawHoleEnabled(true);
        pieBinhGasHot.setTransparentCircleRadius(25f);
        pieBinhGasHot.setHoleRadius(25f);
        pieBinhGasHot.setRotationEnabled(false);

        dataSet.setColors(ColorTemplate.PASTEL_COLORS);
//        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);
        pieBinhGasHot.setOnChartValueSelectedListener(this);
        Legend legend = pieBinhGasHot.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

    }

    public void PhuKienHot() {

        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(800000, 0));
        yvalues.add(new Entry(150000, 1));
        yvalues.add(new Entry(1200000, 2));
        yvalues.add(new Entry(25000000, 3));
        yvalues.add(new Entry(2300000, 4));


        PieDataSet dataSet = new PieDataSet(yvalues, "Doanh Thu");

        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("Bếp Gas Giovani GD2000");
        xVals.add("Bếp Gas Kwashaki 292");
        xVals.add("Bếp Gas Franch 9792D");
        xVals.add("Bếp Gas Italy 39323");
        xVals.add("Bếp Gas Việt Nam VN2017");


        PieData data = new PieData(xVals, dataSet);

        piePhukienHot.setData(data);
//        piePhukienHot.setDescription("Biểu đồ bếp gas bán chạy");

        piePhukienHot.setDrawHoleEnabled(true);
        piePhukienHot.setTransparentCircleRadius(25f);
        piePhukienHot.setHoleRadius(25f);
        piePhukienHot.setRotationEnabled(false);
        dataSet.setColors(ColorTemplate.LIBERTY_COLORS);
//        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);
        piePhukienHot.setOnChartValueSelectedListener(this);

        Legend legend = piePhukienHot.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

    }
}


