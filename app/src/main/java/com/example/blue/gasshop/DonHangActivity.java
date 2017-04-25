package com.example.blue.gasshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DonHangActivity extends  AppCompatActivity implements OnMapReadyCallback {
    static final LatLng HO_HOAN_KIEM = new LatLng(21.028933, 105.852107);
    static final LatLng LANG_CHU_TICH_HCM = new LatLng(21.036779, 105.834697);
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ListView listView=(ListView) findViewById(R.id.listView_sanPham);
        SanPhamAdapter sanPhamAdapter=new SanPhamAdapter(this);
        listView.setAdapter(sanPhamAdapter);
        setListViewHeightBasedOnChildren(listView);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions().position(HO_HOAN_KIEM).title("Marker"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HO_HOAN_KIEM,15));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
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
}