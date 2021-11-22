package com.example.retrofit_volley.retrofit.model;

public class LoginResponse {
    private int id;
    private String no_telp;
    private String email;

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getno_telp() {
        return no_telp;
    }

    public void setno_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }
}
