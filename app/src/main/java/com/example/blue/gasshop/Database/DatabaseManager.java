package com.example.blue.gasshop.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.blue.gasshop.DonHangFirebase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by blue on 27/02/2017.
 */
// Đối tương xử lý các thao tác với cơ sở dữ liệu.
public class DatabaseManager {
    SQLiteDatabase datasource;


    public DatabaseManager(Context context) {
        datasource = Database.initDatabase(context, Database.DATA_NAME);
    }


    // Lấy toàn bộ dữ liệu tù bảng trong CSDL
    public List<DonHangFirebase> getAllData(String tab_name) {
        List<DonHangFirebase> list_donHang = new ArrayList<>();
        Cursor cursor = datasource.rawQuery("SELECT * FROM " + tab_name + "", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            DonHangFirebase donHangFirebase = getData(cursor);
            list_donHang.add(donHangFirebase);
            cursor.moveToNext();
        }
        return list_donHang;
    }
    //

    public void insert(DonHangFirebase donHangFirebase) {
      ContentValues contentValues = new ContentValues();
        contentValues.put("Ten_KH", donHangFirebase.getTen());
        contentValues.put("Ten_SP", donHangFirebase.getSanpham());
        contentValues.put("Ngay_mua", donHangFirebase.getNgaymua());
        contentValues.put("Dia_Chi", donHangFirebase.getDiachi());
        contentValues.put("SDT", donHangFirebase.getSdt());
        contentValues.put("X", donHangFirebase.getX());
        contentValues.put("Y", donHangFirebase.getY());
        contentValues.put("ID_SP", donHangFirebase.getIdSanPham());
        contentValues.put("Tong_tien", donHangFirebase.getTongtien());
        datasource.insert(Database.TAB_THONGKE, null, contentValues);

    }

    public DonHangFirebase getData(Cursor cursor) {


        String ten = cursor.getString(1);
        String sanpham = cursor.getString(2);
        String ngaymua = cursor.getString(3);
        String diachi = cursor.getString(4);
        String sdt = cursor.getString(5);
        double x = cursor.getDouble(6);
        double y = cursor.getDouble(7);

        String idSanPham = cursor.getString(8);

        int tongtien = cursor.getInt(9);

        DonHangFirebase sanPham = new DonHangFirebase(ten, tongtien, sanpham, diachi, sdt, idSanPham, x, y, ngaymua);
        return sanPham;
    }

}
