package com.example.blue.gasshop;

import java.io.Serializable;
import java.util.Date;

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
}
