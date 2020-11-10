package com.example.learnforeignlanguage.mode;

public class CustomDetail {
    private int idCustomDetail;
    private int idCustom;
    private int idVocabulary;

    public CustomDetail() {
    }

    public CustomDetail(int idCustomDetail, int idCustom, int idVocabulary) {
        this.idCustomDetail = idCustomDetail;
        this.idCustom = idCustom;
        this.idVocabulary = idVocabulary;
    }

    public int getIdCustomDetail() {
        return idCustomDetail;
    }

    public void setIdCustomDetail(int idCustomDetail) {
        this.idCustomDetail = idCustomDetail;
    }

    public int getIdCustom() {
        return idCustom;
    }

    public void setIdCustom(int idCustom) {
        this.idCustom = idCustom;
    }

    public int getIdVocabulary() {
        return idVocabulary;
    }

    public void setIdVocabulary(int idVocabulary) {
        this.idVocabulary = idVocabulary;
    }
}
