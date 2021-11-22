package com.example.retrofit_volley.retrofit.model;

import java.util.List;

public class ResponseModel {

    private int kode;
    private String pesan;
    private List<DataModel> data;

    public int getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<DataModel> getData() {
        return data;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public void setData(List<DataModel> data) {
        this.data = data;
    }

}
