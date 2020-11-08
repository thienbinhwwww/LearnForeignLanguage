package com.example.bookmanagement.model;

public class invoiceDetails {
    private int idInvoiceDetails;
    private int idBook;
    private int idBill;
    private int amount;

    public invoiceDetails() {
    }

    public invoiceDetails(int idInvoiceDetails, int idBook, int idBill, int amount) {
        this.idInvoiceDetails = idInvoiceDetails;
        this.idBook = idBook;
        this.idBill = idBill;
        this.amount = amount;
    }

    public int getIdInvoiceDetails() {
        return idInvoiceDetails;
    }

    public void setIdInvoiceDetails(int idInvoiceDetails) {
        this.idInvoiceDetails = idInvoiceDetails;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
