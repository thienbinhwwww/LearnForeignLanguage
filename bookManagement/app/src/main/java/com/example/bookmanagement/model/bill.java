package com.example.bookmanagement.model;

public class bill {
    private int idBill;
    private String date;
    private String time;

    public bill() {
    }

    public bill(int idBill, String date,String time) {
        this.idBill = idBill;
        this.date = date;
        this.time = time;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
