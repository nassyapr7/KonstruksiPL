package com.example.kpl;

public class ModelNilai {
    private String matkul;
    private float nilaiTugas;
    private float nilaiQuiz;
    private float nilaiUTS;
    private float nilaiUAS;

    public ModelNilai(String matkul, float nilaiTugas, float nilaiQuiz, float nilaiUTS, float nilaiUAS) {
        this.matkul = matkul;
        this.nilaiTugas = nilaiTugas;
        this.nilaiQuiz = nilaiQuiz;
        this.nilaiUTS = nilaiUTS;
        this.nilaiUAS = nilaiUAS;
    }

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public float getNilaiTugas() {
        return nilaiTugas;
    }

    public void setNilaiTugas(float nilaiTugas) {
        this.nilaiTugas = nilaiTugas;
    }

    public float getNilaiQuiz() {
        return nilaiQuiz;
    }

    public void setNilaiQuiz(float nilaiKuis) {
        this.nilaiQuiz = nilaiKuis;
    }

    public float getNilaiUTS() {
        return nilaiUTS;
    }

    public void setNilaiUTS(float nilaiUTS) {
        this.nilaiUTS = nilaiUTS;
    }

    public float getNilaiUAS() {
        return nilaiUAS;
    }

    public void setNilaiUAS(float nilaiUAS) {
        this.nilaiUAS = nilaiUAS;
    }


}
