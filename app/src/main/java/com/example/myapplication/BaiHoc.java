package com.example.myapplication;

public class BaiHoc {
    private int id;
    private String TenComputer;

    public BaiHoc(int id, String tenComputer) {
        this.id = id;
        this.TenComputer = tenComputer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenComputer() {
        return TenComputer;
    }

    public void setTenComputer(String tenComputer) {
        TenComputer = tenComputer;
    }
}
