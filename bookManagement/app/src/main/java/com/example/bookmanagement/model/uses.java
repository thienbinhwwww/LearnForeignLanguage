package com.example.bookmanagement.model;

public class uses {
    private int idUses;
    private String usesName;
    private String password;
    private String gmail;

    public uses() {
    }

    public uses(int idUses, String usesName, String password, String gmail) {
        this.idUses = idUses;
        this.usesName = usesName;
        this.password = password;
        this.gmail = gmail;
    }

    public int getIdUses() {
        return idUses;
    }

    public void setIdUses(int idUses) {
        this.idUses = idUses;
    }

    public String getUsesName() {
        return usesName;
    }

    public void setUsesName(String usesName) {
        this.usesName = usesName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }
}
