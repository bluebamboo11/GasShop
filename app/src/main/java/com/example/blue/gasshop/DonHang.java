package com.example.blue.gasshop;

/**
 * Created by blue on 24/04/2017.
 */

public class DonHang {
    private String name;
    private String Sdt;
    private String sanPham;
    private String diaChi;

    public DonHang(String name, String sdt, String sanPham, String diaChi) {
        this.name = name;
        Sdt = sdt;
        this.sanPham = sanPham;
        this.diaChi = diaChi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        Sdt = sdt;
    }

    public String getSanPham() {
        return sanPham;
    }

    public void setSanPham(String sanPham) {
        this.sanPham = sanPham;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
