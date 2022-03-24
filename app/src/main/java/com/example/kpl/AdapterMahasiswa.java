package com.example.kpl;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdapterMahasiswa extends RecyclerView.Adapter<AdapterMahasiswa.MyViewHolder> {
    private List<ModelMahasiswa> mList;
    StudentClicked studentClicked;

    //Konstruktor untuk menerima input dari Database
    public AdapterMahasiswa(List<ModelMahasiswa>mList, StudentClicked studentClicked){
        this.mList = mList;
        this.studentClicked = studentClicked;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Membuat View untuk menyiapkan dan memasang layout yang akan digunakan pada RecyclerView
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.layout_item, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // menampilkan data ke dalam view
        final ModelMahasiswa data = mList.get(position);
        holder.tvNIM.setText("" + data.getNIM());
        holder.tvNama.setText("" + data.getNama());
        holder.tvProdi.setText("" + data.getProdi());
        holder.cardHasil.setOnClickListener(view -> {
            studentClicked.onStudentClicked(data);
        });
    }

    @Override
    public int getItemCount() {
        //menghitung jumlah data yang akan ditampilkan pada RecyclerView
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNIM, tvNama, tvProdi;
        CardView cardHasil;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //inisialisasi View-View yang terpasang pada layout RecyclerView
            tvNIM = itemView.findViewById(R.id.tvNIM);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvProdi = itemView.findViewById(R.id.tvProdi);
            cardHasil = itemView.findViewById(R.id.cardHasil);
        }
    }

    interface StudentClicked{
        void onStudentClicked(ModelMahasiswa mahasiswa);
    }
}
