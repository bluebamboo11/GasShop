package com.example.blue.gasshop;

import java.io.Serializable;

/**
 * Created by blue on 25/04/2017.
 */

public class DonHangFirebase  implements Serializable{
    public String ten;
    public int tongtien;
    public String sanpham;
    public String diachi;
    public String sdt;
    public String idSanPham;
    public double x;
    public double y;
    public String ngaymua;
    public String thang;
    public String idDonHang;

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public DonHangFirebase() {
    }

    public DonHangFirebase(String ten, int tongtien, String sanpham, String diachi, String sdt, String idSanPham, double x, double y, String ngaymua) {
        this.ten = ten;
        this.tongtien = tongtien;
        this.sanpham = sanpham;
        this.diachi = diachi;
        this.sdt = sdt;
        this.idSanPham = idSanPham;
        this.x = x;
        this.y = y;
        this.ngaymua = ngaymua;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public String getSanpham() {
        return sanpham;
    }

    public void setSanpham(String sanpham) {
        this.sanpham = sanpham;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(String ngaymua) {
        this.ngaymua = ngaymua;
    }
}
