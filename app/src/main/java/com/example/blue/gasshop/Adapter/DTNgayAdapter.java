package com.example.blue.gasshop.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.blue.gasshop.DonHangFirebase;
import com.example.blue.gasshop.R;

import java.util.ArrayList;

/**
 * Created by youolo on 04/05/2017.
 */

public class DTNgayAdapter extends BaseAdapter {
    ArrayList<DonHangFirebase> listdonhang;
    Context context;

    @Override
    public int getCount() {
        return listdonhang.size();
    }

    @Override
    public Object getItem(int position) {

        return listdonhang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//        View itemView = inflater.inflate(R.layout., null);

        return  null;
    }
}
