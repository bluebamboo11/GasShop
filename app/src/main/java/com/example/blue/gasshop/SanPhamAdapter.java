package com.example.blue.gasshop;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by blue on 24/04/2017.
 */

public class SanPhamAdapter extends BaseAdapter {
    ArrayList<SanPham> sanPhamArrayList;
    Context context;

    public SanPhamAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_san_pham, null);

        return itemView;
    }
}
