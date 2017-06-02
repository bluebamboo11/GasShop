package com.example.blue.gasshop.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.blue.gasshop.DonHangFirebase;
import com.example.blue.gasshop.SanPhamFirebase;

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
        cursor.moveToLast();
        for(int i=0;i<cursor.getCount();i++){

            DonHangFirebase donHangFirebase = getData(cursor);
            list_donHang.add(donHangFirebase);
            cursor.moveToPosition(cursor.getPosition()-1);
        }
        return list_donHang;
    }

    //
    public List<DonHangFirebase> getAllData(String tab_name, String date) {
        List<DonHangFirebase> list_donHang = new ArrayList<>();
        Cursor cursor = datasource.rawQuery("SELECT * FROM " + tab_name + " WHERE Ngay_mua = ? ", new String[]{date});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            DonHangFirebase donHangFirebase = getData(cursor);
            list_donHang.add(donHangFirebase);
            cursor.moveToNext();
        }
        return list_donHang;
    }

    public List<DonHangFirebase> getAllDataThang(String tab_name, String thang) {
        List<DonHangFirebase> list_donHang = new ArrayList<>();
        Cursor cursor = datasource.rawQuery("SELECT * FROM " + tab_name + " WHERE thang = ? ", new String[]{thang});
        while (cursor.moveToNext()){

            DonHangFirebase donHangFirebase = getData(cursor);
            list_donHang.add(donHangFirebase);

        }
        return list_donHang;
    }

    public void insert(DonHangFirebase donHangFirebase) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Ten_KH", donHangFirebase.ten);
        contentValues.put("Ten_SP", donHangFirebase.sanpham);
        contentValues.put("Ngay_mua", donHangFirebase.ngaymua);
        contentValues.put("Dia_Chi", donHangFirebase.diachi);
        contentValues.put("SDT", donHangFirebase.sdt);
        contentValues.put("X", donHangFirebase.x);
        contentValues.put("Y", donHangFirebase.y);
        contentValues.put("ID_SP", donHangFirebase.idSanPham);
        contentValues.put("Tong_tien", donHangFirebase.tongtien);
        contentValues.put("thang", donHangFirebase.thang);
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
        String thang = cursor.getString(10);
        DonHangFirebase sanPham = new DonHangFirebase(ten, tongtien, sanpham, diachi, sdt, idSanPham, x, y, ngaymua);
        sanPham.setThang(thang);
        return sanPham;
    }

    public void insertSanPham(SanPhamFirebase sanPhamFirebase) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", sanPhamFirebase.id);
        contentValues.put("anh", sanPhamFirebase.anh);
        contentValues.put("ten", sanPhamFirebase.ten);
        contentValues.put("gia", sanPhamFirebase.gia);
        contentValues.put("soluong", sanPhamFirebase.soluong);
        int id = (int) datasource.insertWithOnConflict(Database.TAB_SANPHAM, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
        if (id == -1) {
            Cursor cursor = datasource.rawQuery("SELECT * FROM " + Database.TAB_SANPHAM + " WHERE id = ? ", new String[]{sanPhamFirebase.id});
            cursor.moveToFirst();
            int soluong = cursor.getInt(4);
            contentValues = new ContentValues();
            contentValues.put("soluong", soluong + sanPhamFirebase.soluong);
            datasource.update(Database.TAB_SANPHAM, contentValues, "id=? ", new String[]{sanPhamFirebase.id});
        }
    }

    public ArrayList<SanPhamFirebase> selectSanPham() {
        Cursor cursor = datasource.rawQuery("SELECT * FROM " + Database.TAB_SANPHAM + " ORDER BY soluong DESC ", null);
        ArrayList<SanPhamFirebase> sanPhamFirebaseArrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            SanPhamFirebase sanPhamFirebase = new SanPhamFirebase();
            sanPhamFirebase.id = cursor.getString(0);
            sanPhamFirebase.anh = cursor.getString(1);
            sanPhamFirebase.gia = cursor.getInt(3);
            sanPhamFirebase.ten = cursor.getString(2);
            sanPhamFirebase.soluong = cursor.getInt(4);
            sanPhamFirebaseArrayList.add(sanPhamFirebase);
        }
        return sanPhamFirebaseArrayList;
    }
}
