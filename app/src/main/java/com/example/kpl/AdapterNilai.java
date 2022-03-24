package com.example.kpl;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterNilai extends RecyclerView.Adapter<AdapterNilai.MyViewHolder> {
    private List<ModelNilai> mList;

    public AdapterNilai(List<ModelNilai>mList){
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Membuat View untuk menyiapkan dan memasang layout yang akan digunakan pada RecyclerView
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.layout_nilai_item, parent, false);
        return new MyViewHolder(viewItem);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // menampilkan data ke dalam view
        final ModelNilai data = mList.get(position);
        holder.tvMatkul.setText("" + data.getMatkul());
        holder.tvNilaiTugas.setText("Nilai Tugas : " + data.getNilaiTugas());
        holder.tvNilaiQuiz.setText("Nilai Quiz : " + data.getNilaiQuiz());
        holder.tvNilaiUTS.setText("Nilai UTS : " + data.getNilaiUTS());
        holder.tvNilaiUAS.setText("Nilai UAS : " + data.getNilaiUAS());

        // perhitungan penilaian
        float nilaiTugas = data.getNilaiTugas() * (10.0f / 100.0f);
        float nilaiQuiz = data.getNilaiQuiz() * (20.0f / 100.0f);
        float nilaiUTS = data.getNilaiUTS() * (30.0f / 100.0f);
        float nilaiUAS = data.getNilaiUAS() * (40.0f / 100.0f);
        float totalNilai = nilaiTugas + nilaiQuiz + nilaiUTS + nilaiUAS;

        // membuat nilai rata-rata yang ditampilkan hanya 2 angka dibelakang koma
        holder.tvNilaiAkhir.setText(String.format("Rata-Rata : %.2f", totalNilai));

        // seleksi kondisi nilai rata-rata yang dihasilkan dengan indeks nilai beserta keterangan yang akan keluar
        if (totalNilai >= 92 && totalNilai <= 100) {
            holder.tvIndexNilai.setText("A");
            holder.tvKeterangan.setText("Istimewa");
        } else if (totalNilai >= 86 && totalNilai <= 91) {
            holder.tvIndexNilai.setText("B");
            holder.tvKeterangan.setText("Hampir Istimewa");
        } else if (totalNilai >= 81 && totalNilai <= 85) {
            holder.tvIndexNilai.setText("B+");
            holder.tvKeterangan.setText("Baik Sekali");
        } else if (totalNilai >= 76 && totalNilai <= 80) {
            holder.tvIndexNilai.setText("B");
            holder.tvKeterangan.setText("Baik");
        } else if (totalNilai >= 71 && totalNilai <= 75) {
            holder.tvIndexNilai.setText("B-");
            holder.tvKeterangan.setText("Cukup Baik");
        } else if (totalNilai >= 66 && totalNilai <= 70) {
            holder.tvIndexNilai.setText("C+");
            holder.tvKeterangan.setText("Lebih Dari Cukup");
        } else if (totalNilai >= 60 && totalNilai <= 65) {
            holder.tvIndexNilai.setText("C");
            holder.tvKeterangan.setText("Cukup");
        } else if (totalNilai >= 55 && totalNilai <= 59) {
            holder.tvIndexNilai.setText("D");
            holder.tvKeterangan.setText("Kurang");
        } else if (totalNilai <= 55) {
            holder.tvIndexNilai.setText("E");
            holder.tvKeterangan.setText("Gagal");
        } else {
            holder.tvIndexNilai.setText("Tidak Valid");
            holder.tvKeterangan.setText("Tidak Valid");
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvMatkul, tvNilaiTugas, tvNilaiQuiz, tvNilaiUTS, tvNilaiUAS, tvNilaiAkhir, tvIndexNilai, tvKeterangan;
        CardView cardHasilNilai;
        public MyViewHolder(@NonNull View itemView) {
            //inisialisasi View-View yang terpasang pada layout RecyclerView
            super(itemView);
            tvMatkul = itemView.findViewById(R.id.tvMatkul);
            tvNilaiTugas = itemView.findViewById(R.id.tvNilaiTugas);
            tvNilaiQuiz = itemView.findViewById(R.id.tvNilaiQuiz);
            tvNilaiUTS = itemView.findViewById(R.id.tvNilaiUTS);
            tvNilaiUAS = itemView.findViewById(R.id.tvNilaiUAS);
            tvNilaiAkhir = itemView.findViewById(R.id.tvNilaiAkhir);
            tvIndexNilai = itemView.findViewById(R.id.tvIndexNilai);
            tvKeterangan = itemView.findViewById(R.id.tvKeterangan);
            cardHasilNilai = itemView.findViewById(R.id.cardHasilNilai);
        }
    }
}
