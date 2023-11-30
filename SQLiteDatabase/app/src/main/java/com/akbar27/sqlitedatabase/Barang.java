package com.akbar27.sqlitedatabase;

public class Barang {
    private String idBarang, barang, stock, harga;

    public Barang(String idBarang, String barang, String stock, String harga) {
        this.idBarang = idBarang;
        this.barang = barang;
        this.stock = stock;
        this.harga = harga;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getBarang() {
        return barang;
    }

    public void setBarang(String barang) {
        this.barang = barang;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
