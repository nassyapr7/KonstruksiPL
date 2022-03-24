package com.example.kpl;

// menentukan atribut atau data data yang akan disimpan termasuk dengan untuk mengambilnya

public class ModelMahasiswa {
    private String NIM;
    private String nama;
    private String prodi;
    private String key;

    public ModelMahasiswa(){

    }

    ///Konstruktor untuk mengambil inputan dari User
    public ModelMahasiswa(String NIM, String nama, String prodi) {
        this.NIM = NIM;
        this.nama = nama;
        this.prodi = prodi;
    }

    public String getNIM() {
        return NIM;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public void setKey(String key) {
    }
}
