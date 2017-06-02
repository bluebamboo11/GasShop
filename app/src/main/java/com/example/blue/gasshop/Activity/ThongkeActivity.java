package com.example.blue.gasshop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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

        getSupportActionBar().setTitle("Thống Kế");
        getSupportActionBar().setElevation(0f);


        DatabaseManager manager = new DatabaseManager(this);


//        initData();
        listView = (ListView) findViewById(R.id.lvThongke);
        donHangFirebaseArrayList = manager.getAllData(Database.TAB_THONGKE);
        thongKeAdapter = new ThongKeAdapter(this, R.layout.list_thongke, donHangFirebaseArrayList);
        listView.setAdapter(thongKeAdapter);

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
