package com.example.blue.gasshop.Activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.blue.gasshop.Adapter.DonHangAdapter;
import com.example.blue.gasshop.DonHangFirebase;
import com.example.blue.gasshop.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private DonHangFirebase donHangFirebase;
    DatabaseReference databaseReferenceDonHang;
    ChildEventListener childEventListenerDonHang;
    DonHangAdapter donHangAdapter;
    private DatabaseReference databaseReference;
    private ArrayList<DonHangFirebase> donHangFirebaseArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        donHangFirebaseArrayList = new ArrayList<>();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initRecycleView();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initRecycleView() {
        databaseReferenceDonHang = databaseReference.child("HaNoi/HaDong/LaKhe");

        donHangAdapter = new DonHangAdapter(donHangFirebaseArrayList, this);
        childEventListenerDonHang = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DonHangFirebase donHangFirebase = dataSnapshot.getValue(DonHangFirebase.class);
                databaseReferenceDonHang.child(dataSnapshot.getKey()).removeValue();
                donHangFirebaseArrayList.add(donHangFirebase);
                donHangAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_DonHang);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(donHangAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        databaseReferenceDonHang.addChildEventListener(childEventListenerDonHang);
    }

    @Override
    protected void onStop() {
        super.onStop();
        databaseReferenceDonHang.removeEventListener(childEventListenerDonHang);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("list", donHangFirebaseArrayList);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
             ArrayList<DonHangFirebase> donHangFirebases= (ArrayList<DonHangFirebase>) savedInstanceState.getSerializable("list");
        if(donHangFirebases!=null){
            donHangFirebaseArrayList=donHangFirebases;
            donHangAdapter = new DonHangAdapter(donHangFirebaseArrayList, this);
            recyclerView.setAdapter(donHangAdapter);
        }
    }
}
