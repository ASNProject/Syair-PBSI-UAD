package com.example.syair.Adapter;

public class RequestProfil {
    public String asalsekolah, email, namapengguna;

    public RequestProfil() {
    }

    public RequestProfil(String asalsekolah, String email, String namapengguna) {
        this.asalsekolah = asalsekolah;
        this.email = email;
        this.namapengguna = namapengguna;
    }

    public String getAsalsekolah() {
        return asalsekolah;
    }

    public void setAsalsekolah(String asalsekolah) {
        this.asalsekolah = asalsekolah;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNamapengguna() {
        return namapengguna;
    }

    public void setNamapengguna(String namapengguna) {
        this.namapengguna = namapengguna;
    }
}
