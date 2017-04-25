package com.example.blue.gasshop;

/**
 * Created by blue on 24/04/2017.
 */

public class SanPham {
    private String name;
    private String thongso;
    private String url;

    public SanPham(String name, String thongso, String url) {
        this.name = name;
        this.thongso = thongso;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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


}
