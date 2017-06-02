package com.example.blue.gasshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.blue.gasshop.Activity.DonHangActivity;
import com.example.blue.gasshop.Database.DatabaseManager;
import com.example.blue.gasshop.DonHangFirebase;
import com.example.blue.gasshop.R;
import com.example.blue.gasshop.SanPhamFirebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by blue on 24/04/2017.
 */

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHolder> {
    private ArrayList<DonHangFirebase> donHangArrayList;
    private Context context;
    DonHangFirebase donHangFirebase;

    DatabaseManager manager;
    SQLiteDatabase datasource;


    public DonHangAdapter(ArrayList<DonHangFirebase> donHangArrayList, Context context) {
        this.donHangArrayList = donHangArrayList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_don_hang, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textName.setText(donHangArrayList.get(position).ten);
        holder.textSDT.setText(donHangArrayList.get(position).sdt);
        holder.textDiaChi.setText(donHangArrayList.get(position).diachi);
        holder.textSanPham.setText(donHangArrayList.get(position).sanpham);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.textGia.setText(decimalFormat.format(donHangArrayList.get(position).tongtien) + "");






    }

    @Override
    public int getItemCount() {
        return donHangArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textSDT;
        TextView textDiaChi;
        TextView textSanPham;
        TextView buttonXong;
        TextView buttonChiTiet;
        TextView textGia;


        public ViewHolder(final View itemView) {
            super(itemView);
            textGia = (TextView) itemView.findViewById(R.id.text_gia);
            textName = (TextView) itemView.findViewById(R.id.text_name);
            textSDT = (TextView) itemView.findViewById(R.id.text_sdt);
            textDiaChi = (TextView) itemView.findViewById(R.id.text_diachi);
            textSanPham = (TextView) itemView.findViewById(R.id.text_ds_sanPham);
            buttonXong = (TextView) itemView.findViewById(R.id.buton_xong);
            buttonChiTiet = (TextView) itemView.findViewById(R.id.buton_chitiet);
            buttonXong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
            DatabaseManager databaseManager=new DatabaseManager(context);
                    donHangFirebase=donHangArrayList.get(getAdapterPosition());
                    databaseManager.insert(donHangFirebase);
                    donHangArrayList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    String[] sanPhams = donHangFirebase.idSanPham.split(",");
                    for (String s : sanPhams) {
                       loadData(s);
                    }
                }
            });
            buttonChiTiet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DonHangActivity.class);
                    intent.putExtra("donhang", donHangArrayList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }

    }

    private void loadData(String s) {
        final String[] sanpham = s.split(":");
        DatabaseReference  databaseReference = FirebaseDatabase.getInstance().getReference();
        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    SanPhamFirebase sanPhamFirebase = dataSnapshot.getValue(SanPhamFirebase.class);
                    sanPhamFirebase.soluong = Integer.parseInt(sanpham[1]);
                   DatabaseManager databaseManager=new DatabaseManager(context);
                    databaseManager.insertSanPham(sanPhamFirebase);
                }}

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.child("SanPham").child("bepgas").child(sanpham[0]).addListenerForSingleValueEvent(valueEventListener);
        databaseReference.child("SanPham").child("binhgas").child(sanpham[0]).addListenerForSingleValueEvent(valueEventListener);
        databaseReference.child("SanPham").child("linhkien").child(sanpham[0]).addListenerForSingleValueEvent(valueEventListener);

    }
}
