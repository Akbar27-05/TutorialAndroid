package com.akbar27.latihanshow;

public class BuahModelClass {
    String id;
    String nama;
    String sisa;

    public BuahModelClass(String id,String nama, String sisa) {
        this.id = id;
        this.nama = nama;
        this.sisa = sisa;
    }

    public BuahModelClass() {
    }

    public String getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSisa() {
        return sisa;
    }

    public void setSisa(String sisa) {
        this.sisa = sisa;
    }
}
