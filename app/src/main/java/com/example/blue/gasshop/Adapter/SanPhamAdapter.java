package com.example.blue.gasshop.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.blue.gasshop.R;
import com.example.blue.gasshop.SanPhamFirebase;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by blue on 24/04/2017.
 */

public class SanPhamAdapter extends BaseAdapter {
    ArrayList<SanPhamFirebase> sanPhamArrayList;
    Context context;

    public SanPhamAdapter(ArrayList<SanPhamFirebase> sanPhamArrayList, Context context) {
        this.sanPhamArrayList = sanPhamArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return sanPhamArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return sanPhamArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_san_pham, null);
        TextView textName=(TextView) itemView.findViewById(R.id.text_name);
        TextView textTien=( TextView) itemView.findViewById(R.id.text_gia);
        TextView textSoLuong=(TextView) itemView.findViewById(R.id.text_soluong);
        ImageView imgSanpham=(ImageView) itemView.findViewById(R.id.img_sanPham);
        textName.setText(sanPhamArrayList.get(position).ten);
        textTien.setText(sanPhamArrayList.get(position).gia+"");
        textSoLuong.setText(sanPhamArrayList.get(position).soluong+"");
        StorageReference storage = FirebaseStorage.getInstance().getReferenceFromUrl(sanPhamArrayList.get(position).anh);
        Glide.with(context)
                .using(new FirebaseImageLoader())
                .load(storage)
                .into(imgSanpham);
        return itemView;
    }
}
