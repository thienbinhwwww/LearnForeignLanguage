package com.example.duanmau.doiTuong;

public class sach {
    private int ID;
    private String tenSach;
    private String theLoai;
    private double donGia;
    private int soLuong;

    public sach() {
    }

    public sach(String tenSach, String theLoai, double donGia, int soLuong) {
        this.tenSach = tenSach;
        this.theLoai = theLoai;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
