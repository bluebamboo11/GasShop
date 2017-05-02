package com.example.blue.gasshop.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.blue.gasshop.DonHangFirebase;
import com.example.blue.gasshop.R;
import com.example.blue.gasshop.Thongke_Entites;

import java.util.List;

/**
 * Created by youolo on 29/04/2017.
 */

public class ThongKeAdapter extends ArrayAdapter {
    private int ResID;
    private Context context;
    DonHangFirebase donhang;
    private List<Thongke_Entites> list;

    public ThongKeAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        ResID = resource;
        list = objects;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ThongKeAdapter.ViewHolder viewHolder = new ThongKeAdapter.ViewHolder();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(ResID, null);
            viewHolder.TenMH = (TextView) view.findViewById(R.id.txtTenHang);
            viewHolder.Thoigian = (TextView) view.findViewById(R.id.txtThoigian);
            viewHolder.Tien = (TextView) view.findViewById(R.id.txtTien);
            viewHolder.Ketqua = (TextView) view.findViewById(R.id.txtKQ);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ThongKeAdapter.ViewHolder) view.getTag();
        }

        viewHolder.TenMH.setText(donhang.sanpham);
        viewHolder.Thoigian.setText("" + list.get(position).getThoigian());
        viewHolder.Tien.setText(donhang.tongtien);
        if (donhang.KQ == 1) {
            viewHolder.Ketqua.setText("Đã chuyển");
        }
        else {
            viewHolder.Ketqua.setText("Hủy đơn");
        }

        return view;
    }

    private class ViewHolder {
        TextView TenMH;
        TextView Thoigian;
        TextView Tien;
        TextView Ketqua;

    }
}