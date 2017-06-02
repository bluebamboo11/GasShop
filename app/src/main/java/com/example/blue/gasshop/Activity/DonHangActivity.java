package com.example.blue.gasshop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.blue.gasshop.Adapter.SanPhamAdapter;
import com.example.blue.gasshop.DonHangFirebase;
import com.example.blue.gasshop.R;
import com.example.blue.gasshop.SanPhamFirebase;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DonHangActivity extends AppCompatActivity implements OnMapReadyCallback {

    private LatLng diachi;
    private DatabaseReference databaseReference;
    private ArrayList<SanPhamFirebase> sanPhamFirebaseArrayList;
    private SanPhamAdapter sanPhamAdapter;
    private ListView listView;

    public DonHangActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        initData();
        mapFragment.getMapAsync(this);
        listView = (ListView) findViewById(R.id.listView_sanPham);
        sanPhamFirebaseArrayList = new ArrayList<>();
        sanPhamAdapter = new SanPhamAdapter(sanPhamFirebaseArrayList, this);
        listView.setAdapter(sanPhamAdapter);
        setListViewHeightBasedOnChildren(listView);
        getSupportActionBar().setTitle("Đơn Hàng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions().position(diachi).title("Khach Hang"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(diachi, 15));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {

            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public void initData() {
        DonHangFirebase donHangFirebase = (DonHangFirebase) getIntent().getSerializableExtra("donhang");
        TextView textTen = (TextView) findViewById(R.id.text_name);
        TextView textSDT = (TextView) findViewById(R.id.text_sdt);
        TextView textDiaChi = (TextView) findViewById(R.id.text_diachi);
        TextView textTien = (TextView) findViewById(R.id.text_gia);
        textTen.setText(donHangFirebase.ten);
        textSDT.setText(donHangFirebase.sdt);
        textDiaChi.setText(donHangFirebase.diachi);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        textTien.setText(decimalFormat.format(donHangFirebase.tongtien));
        diachi = new LatLng(donHangFirebase.x, donHangFirebase.y);
        String[] sanPhams = donHangFirebase.idSanPham.split(",");
        for (String s : sanPhams) {
            loadData(s);
        }
    }

    private void loadData(String s) {
        final String[] sanpham = s.split(":");
        databaseReference = FirebaseDatabase.getInstance().getReference();
        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              if(dataSnapshot.getValue()!=null){
                SanPhamFirebase sanPhamFirebase = dataSnapshot.getValue(SanPhamFirebase.class);
                sanPhamFirebase.soluong = Integer.parseInt(sanpham[1]);
                sanPhamFirebaseArrayList.add(sanPhamFirebase);
                sanPhamAdapter.notifyDataSetChanged();
                setListViewHeightBasedOnChildren(listView);
            }}

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        Log.e("sanpham",sanpham[0]);
        databaseReference.child("SanPham").child("bepgas").child(sanpham[0]).addListenerForSingleValueEvent(valueEventListener);
        databaseReference.child("SanPham").child("binhgas").child(sanpham[0]).addListenerForSingleValueEvent(valueEventListener);
        databaseReference.child("SanPham").child("linhkien").child(sanpham[0]).addListenerForSingleValueEvent(valueEventListener);

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