package com.example.duanmau.doiTuong;

public class hoaDon {
    private int id;
    private String ngayMua;
    private double tongTien;
    private String idKhachHang;

    public hoaDon() {
    }

    public hoaDon(int id, String ngayMua, double tongTien, String idKhachHang) {
        this.id = id;
        this.ngayMua = ngayMua;
        this.tongTien = tongTien;
        this.idKhachHang = idKhachHang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(String ngayMua) {
        this.ngayMua = ngayMua;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(String idKhachHang) {
        this.idKhachHang = idKhachHang;
    }
}
