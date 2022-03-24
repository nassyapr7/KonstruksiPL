package com.example.kpl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahMahasiswa extends AppCompatActivity {
    //variable fields edit teks dan button
    EditText edNIM,edNama, edProdi;
    Button btnSimpan;

    //variable yang merujuk ke Firebase Realtime Database
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_list);
        //mengganti judul action bar
        getSupportActionBar().setTitle("Tambah Mahasiswa");

        // inisialisasi element pada user interface
        edNIM = findViewById(R.id.edNIM);
        edNama = findViewById(R.id.edNama);
        edProdi = findViewById(R.id.edProdi);
        btnSimpan = findViewById(R.id.btnSimpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ambil data dari inputan
                String getNIM = edNIM.getText().toString();
                String getNama = edNama.getText().toString();
                String getProdi = edProdi.getText().toString();

                // validasi inputan yang kosong
                if (getNIM.isEmpty()) {
                    edNIM.setError("Masukkan NIM");
                } else if (getNama.isEmpty()) {
                    edNama.setError("Masukkan Nama");
                } else if (getProdi.isEmpty()) {
                    edProdi.setError("Masukkan Prodi");
                } else {
                    // inputan yang tidak kosong akan disimpan ke database pada table Mahasiswa
                    database.child("Mahasiswa").push().setValue(new ModelMahasiswa(getNIM, getNama, getProdi)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        // kondisi jika sukses
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(TambahMahasiswa.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(TambahMahasiswa.this, MainActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        // kondisi jika gagal
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(TambahMahasiswa.this, "Gagal menyimpan data", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}