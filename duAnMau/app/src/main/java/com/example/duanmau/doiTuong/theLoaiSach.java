package com.example.duanmau.doiTuong;

public class theLoaiSach {
    private String theLoai;
    private int soLuong;

    public theLoaiSach() {
    }

    public theLoaiSach(String theLoai, int soLuong) {
        this.theLoai = theLoai;
        this.soLuong = soLuong;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
