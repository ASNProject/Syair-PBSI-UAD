package com.example.syair.Adapter;

public class RequestRegister {

    private String email, namapengguna, asalsekolah, password;

    public RequestRegister() {
    }

    public RequestRegister(String email, String namapengguna, String asalsekolah, String password) {
        this.email = email;
        this.namapengguna = namapengguna;
        this.asalsekolah = asalsekolah;
        this.password = password;
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

    public String getAsalsekolah() {
        return asalsekolah;
    }

    public void setAsalsekolah(String asalsekolah) {
        this.asalsekolah = asalsekolah;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RequestRegister{" +
                "email='" + email + '\'' +
                ", namapengguna='" + namapengguna + '\'' +
                ", asalsekolah='" + asalsekolah + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
