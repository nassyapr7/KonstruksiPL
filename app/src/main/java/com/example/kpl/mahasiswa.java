package com.example.kpl;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class mahasiswa extends AppCompatActivity {
    List<ModelNilai> listNilai = new ArrayList<>();
    AdapterNilai adapterNilai = new AdapterNilai(listNilai);
    RecyclerView rvNilai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa);
        //mengganti judul action bar
        getSupportActionBar().setTitle("Detail Mahasiswa");

        TextView tvNim = findViewById(R.id.tvNIM);
        TextView tvNama = findViewById(R.id.tvNama);
        TextView tvProdi = findViewById(R.id.tvProdi);
        rvNilai = findViewById(R.id.nilai);
        rvNilai.setLayoutManager(new LinearLayoutManager(mahasiswa.this));
        rvNilai.setAdapter(adapterNilai);

        // mengambil data dari MainActivity
        String nama = getIntent().getStringExtra("nama");
        String nim = getIntent().getStringExtra("nim");
        String prodi = getIntent().getStringExtra("prodi");

        tvNim.setText(nim);
        tvNama.setText(nama);
        tvProdi.setText(prodi);

        FloatingActionButton btnTambahNilai = findViewById(R.id.btnTambahNilai);
        btnTambahNilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mahasiswa.this, NilaiMahasiswa.class);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                String matkul = data.getStringExtra("matkul");
                float nilaiTugas = data.getFloatExtra("nilai_tugas", 0.0f);
                float nilaiQuiz = data.getFloatExtra("nilai_quiz", 0.0f);
                float nilaiUTS = data.getFloatExtra("nilai_uts", 0.0f);
                float nilaiUAS = data.getFloatExtra("nilai_uas", 0.0f);

                ModelNilai modelNilai = new ModelNilai(matkul, nilaiTugas, nilaiQuiz, nilaiUTS, nilaiUAS);
                listNilai.add(modelNilai);
                adapterNilai = new AdapterNilai(listNilai);
                rvNilai.setAdapter(adapterNilai);
            }
        }
    }
}