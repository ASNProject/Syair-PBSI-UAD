package com.example.syair.Adapter;

public class RequestNilai {
    public String tanggal, nilai;

    public RequestNilai() {
    }

    public RequestNilai(String tanggal, String nilai) {
        this.tanggal = tanggal;
        this.nilai = nilai;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    @Override
    public String toString() {
        return "RequestNilai{" +
                "tanggal='" + tanggal + '\'' +
                ", nilai='" + nilai + '\'' +
                '}';
    }
}
