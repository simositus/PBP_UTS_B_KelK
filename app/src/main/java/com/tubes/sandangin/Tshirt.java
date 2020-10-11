package com.tubes.sandangin;

public class Tshirt {
    public String namaItem;
    public String detail;
    public String ukuran;
    public int harga;
    public int fotoItem;

    public Tshirt(String namaItem, String detail, String ukuran, int harga, int fotoItem) {
        this.namaItem = namaItem;
        this.detail = detail;
        this.ukuran = ukuran;
        this.harga = harga;
        this.fotoItem = fotoItem;
    }

    public String getNamaItem() {
        return namaItem;
    }

    public void setNamaItem(String namaItem) {
        this.namaItem = namaItem;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getFotoItem() {
        return fotoItem;
    }

    public void setFotoItem(int fotoItem) {
        this.fotoItem = fotoItem;
    }
}
