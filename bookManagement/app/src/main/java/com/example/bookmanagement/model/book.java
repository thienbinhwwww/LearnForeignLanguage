package com.example.bookmanagement.model;

public class book {

    private int idBook;
    private String bookName;
    private int idCategory;
    private Double unitPrice;
    private int amount;

    public book() {
    }

    public book(int idBook, String bookName, int idCategory, Double unitPrice, int amount) {
        this.idBook = idBook;
        this.bookName = bookName;
        this.idCategory = idCategory;
        this.unitPrice = unitPrice;
        this.amount = amount;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
