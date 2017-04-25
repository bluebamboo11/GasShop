package com.example.blue.gasshop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by blue on 24/04/2017.
 */

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHolder> {
    private ArrayList<DonHang> donHangArrayList;
    private Context context;

    public DonHangAdapter(Context context) {
        this.context = context;
    }
    //
//    public DonHangAdapter(ArrayList<DonHang> donHangArrayList, Context context) {
//        this.donHangArrayList = donHangArrayList;
//        this.context = context;
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_don_hang, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.textName.setText(donHangArrayList.get(position).getName());
//        holder.textSDT.setText(donHangArrayList.get(position).getSdt());
//        holder.textDiaChi.setText(donHangArrayList.get(position).getDiaChi());
//        holder.textSanPham.setText(donHangArrayList.get(position).getSanPham());
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textSDT;
        TextView textDiaChi;
        TextView textSanPham;
        TextView buttonXong;
        TextView buttonChiTiet;

        public ViewHolder(View itemView) {
            super(itemView);
            textName = (TextView) itemView.findViewById(R.id.text_name);
            textSDT = (TextView) itemView.findViewById(R.id.text_sdt);
            textDiaChi = (TextView) itemView.findViewById(R.id.text_diachi);
            textSanPham = (TextView) itemView.findViewById(R.id.text_ds_sanPham);
            buttonXong = (TextView) itemView.findViewById(R.id.buton_xong);
            buttonChiTiet = (TextView) itemView.findViewById(R.id.buton_chitiet);
            buttonXong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    donHangArrayList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });
        buttonChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, DonHangActivity.class));
            }
        });
        }

    }
}
