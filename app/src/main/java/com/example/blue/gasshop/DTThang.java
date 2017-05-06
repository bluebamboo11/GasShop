package com.example.blue.gasshop;

/**
 * Created by youolo on 04/05/2017.
 */

public class DTThang {
    private int id_sanpham;
    private String ten_sanpham;
    private String tongtien;

    public DTThang(int id_sanpham, String ten_sanpham, String tongtien) {
        this.id_sanpham = id_sanpham;
        this.ten_sanpham = ten_sanpham;
        this.tongtien = tongtien;
    }

    public DTThang() {
    }

    public int getId_sanpham() {
        return id_sanpham;
    }

    public void setId_sanpham(int id_sanpham) {
        this.id_sanpham = id_sanpham;
    }

    public String getTen_sanpham() {
        return ten_sanpham;
    }

    public void setTen_sanpham(String ten_sanpham) {
        this.ten_sanpham = ten_sanpham;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }
}
