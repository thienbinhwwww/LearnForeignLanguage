package com.example.duanmau.doiTuong;

public class khachHang {
    private String idKhachHang;
    private String hoTen;
    private String sdt;
    private int donHangDaMua;
    private double soTienMuaHang;

    public khachHang() {
    }

    public khachHang(String idKhachHang, String hoTen, String sdt, int donHangDaMua, double soTienMuaHang) {
        this.idKhachHang = idKhachHang;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.donHangDaMua = donHangDaMua;
        this.soTienMuaHang = soTienMuaHang;
    }

    public String getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(String idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getDonHangDaMua() {
        return donHangDaMua;
    }

    public void setDonHangDaMua(int donHangDaMua) {
        this.donHangDaMua = donHangDaMua;
    }

    public double getSoTienMuaHang() {
        return soTienMuaHang;
    }

    public void setSoTienMuaHang(double soTienMuaHang) {
        this.soTienMuaHang = soTienMuaHang;
    }
}
