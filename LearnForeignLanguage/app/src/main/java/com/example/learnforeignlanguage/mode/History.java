package com.example.learnforeignlanguage.mode;

public class History {
    private int idHistory;
    private int idCustom;
    private int idUser;

    public History() {
    }

    public History(int idHistory, int idCustom, int idUser) {
        this.idHistory = idHistory;
        this.idCustom = idCustom;
        this.idUser = idUser;
    }

    public int getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(int idHistory) {
        this.idHistory = idHistory;
    }

    public int getIdCustom() {
        return idCustom;
    }

    public void setIdCustom(int idCustom) {
        this.idCustom = idCustom;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
