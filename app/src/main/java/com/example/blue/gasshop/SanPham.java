package com.example.blue.gasshop;

/**
 * Created by blue on 24/04/2017.
 */

public class SanPham {
    private String name;
    private String thongso;
    private int url;

    public SanPham(String name, String thongso, int url) {
        this.name = name;
        this.thongso = thongso;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThongso() {
        return thongso;
    }

    public void setThongso(String thongso) {
        this.thongso = thongso;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }
}
