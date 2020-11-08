package com.example.baithi;

public class ViTri {
    private String ten;
    private String ID;
    private Double kinhDo;
    private Double viDo;

    public ViTri(String ten, String ID, Double kinhDo, Double viDo) {
        this.ten = ten;
        this.ID = ID;
        this.kinhDo = kinhDo;
        this.viDo = viDo;
    }

    public ViTri() {
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String tieuDe) {
        this.ten = tieuDe;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Double getKinhDo() {
        return kinhDo;
    }

    public void setKinhDo(Double kinhDo) {
        this.kinhDo = kinhDo;
    }

    public Double getViDo() {
        return viDo;
    }

    public void setViDo(Double viDo) {
        this.viDo = viDo;
    }
}
