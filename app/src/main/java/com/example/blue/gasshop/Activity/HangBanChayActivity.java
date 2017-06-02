package com.example.blue.gasshop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.blue.gasshop.Adapter.SanPhamAdapter;
import com.example.blue.gasshop.Database.DatabaseManager;
import com.example.blue.gasshop.R;
import com.example.blue.gasshop.SanPhamFirebase;

import java.util.ArrayList;

public class HangBanChayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hang_ban_chay);
       ListView listView = (ListView) findViewById(R.id.listView_sanPham);
        DatabaseManager databaseManager=new DatabaseManager(this);
getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ArrayList<SanPhamFirebase>  sanPhamFirebaseArrayList = databaseManager.selectSanPham();
       SanPhamAdapter sanPhamAdapter = new SanPhamAdapter(sanPhamFirebaseArrayList, this);
        listView.setAdapter(sanPhamAdapter);
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
