package com.example.kpl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton tambah;
    AdapterMahasiswa adapterMahasiswa;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayList<ModelMahasiswa> listMahasiswa;
    RecyclerView tvTampil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //menghilangkan action bar
        getSupportActionBar().hide();

        tambah = findViewById(R.id.btnTambah);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // kelas yang akan dijalankan ketika tombol tambah diklik
                startActivity(new Intent(MainActivity.this, TambahMahasiswa.class));
            }
        });

        tvTampil = findViewById(R.id.tvTampil);
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(this);
        tvTampil.setLayoutManager(mLayout);
        tampilData();
    }

    private void tampilData() {
        // untuk mengambil data dari table mahasiswa pada database
        database.child("Mahasiswa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // saat ada data mahasiswa yang baru, akan dimasukkan datanya ke arraylist
                listMahasiswa = new ArrayList<>();
                for (DataSnapshot item : snapshot.getChildren()) {
                    ModelMahasiswa mhs = item.getValue(ModelMahasiswa.class);
                    mhs.setKey(item.getKey());
                    listMahasiswa.add(mhs);
                }
                // mengirim data dari adapter ke kelas MainActivity
                adapterMahasiswa = new AdapterMahasiswa(listMahasiswa, mahasiswa -> {
                    Intent intent = new Intent(MainActivity.this, mahasiswa.class);
                    // mengirim data dari tampilan MainActivity ke class mahasiswa
                    intent.putExtra("nama", mahasiswa.getNama());
                    intent.putExtra("nim", mahasiswa.getNIM());
                    intent.putExtra("prodi", mahasiswa.getProdi());
                    startActivity(intent);
                });

                // set adapterMahasiswa ke User Interface
                tvTampil.setAdapter(adapterMahasiswa);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}