package com.example.blue.gasshop;

import java.util.Date;

/**
 * Created by youolo on 29/04/2017.
 */

public class Thongke_Entites {
    private int ID_thongke;
    private Date Thoigian;
    private int Makhachhang;
    private float TongTien;
    private String Ketqua;

    public Thongke_Entites(int ID_thongke, Date thoigian, int makhachhang, float tongTien, String ketqua) {
        this.ID_thongke = ID_thongke;
        Thoigian = thoigian;
        Makhachhang = makhachhang;
        TongTien = tongTien;
        Ketqua = ketqua;
    }

    public Thongke_Entites() {
    }

    public int getID_thongke() {
        return ID_thongke;
    }

    public void setID_thongke(int ID_thongke) {
        this.ID_thongke = ID_thongke;
    }

    public Date getThoigian() {
        return Thoigian;
    }

    public void setThoigian(Date thoigian) {
        Thoigian = thoigian;
    }


    public int getMakhachhang() {
        return Makhachhang;
    }

    public void setMakhachhang(int makhachhang) {
        Makhachhang = makhachhang;
    }

    public float getTongTien() {
        return TongTien;
    }

    public void setTongTien(float tongTien) {
        TongTien = tongTien;
    }

    public String getKetqua() {
        return Ketqua;
    }

    public void setKetqua(String ketqua) {
        Ketqua = ketqua;
    }
}
