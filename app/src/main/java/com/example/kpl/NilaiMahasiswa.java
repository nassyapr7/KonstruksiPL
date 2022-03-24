package com.example.kpl;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class NilaiMahasiswa extends AppCompatActivity {
    EditText edMatkul, edTugas, edQuiz, edUTS, edUAS;
    Button btnSimpanNilai;

    // @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai_mahasiswa);
        getSupportActionBar().setTitle("Tambah Nilai"); // set the top title

        View showInfo = findViewById(R.id.showInfo);
        showInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyCustomAlertDialog();
            }

            public void MyCustomAlertDialog() {
                final Dialog MyDialog = new Dialog(NilaiMahasiswa.this);
                MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                MyDialog.setContentView(R.layout.customdialog);

                Button close = MyDialog.findViewById(R.id.close);
                close.setEnabled(true);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MyDialog.cancel();
                    }
                });

                MyDialog.show();
            }
        });

        edMatkul = findViewById(R.id.edMatkul);
        edTugas = findViewById(R.id.edTugas);
        edQuiz = findViewById(R.id.edQuiz);
        edUTS = findViewById(R.id.edUTS);
        edUAS = findViewById(R.id.edUAS);
        btnSimpanNilai = findViewById(R.id.btnSimpanNilai);

        btnSimpanNilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edMatkul.getText().toString().isEmpty()) {
                    edMatkul.setError("Masukkan Mata Kuliah");
                } else if (edTugas.getText().toString().isEmpty()) {
                    edTugas.setError("Masukkan Nilai Tugas");
                } else if (edQuiz.getText().toString().isEmpty()) {
                    edQuiz.setError("Masukkan Nilai Quiz");
                } else if (edUTS.getText().toString().isEmpty()) {
                    edUTS.setError("Masukkan Nilai UTS");
                } else if (edUAS.getText().toString().isEmpty()) {
                    edUAS.setError("Masukkan Nilai UAS");
                } else {
                    String getMatkul = edMatkul.getText().toString();
                    float getNilaiTugas = Float.parseFloat(edTugas.getText().toString());
                    float getNilaiQuiz = Float.parseFloat(edQuiz.getText().toString());
                    float getNilaiUTS = Float.parseFloat(edUTS.getText().toString());
                    float getNilaiUAS = Float.parseFloat(edUAS.getText().toString());

                    Intent intent = new Intent();
                    intent.putExtra("matkul", getMatkul);
                    intent.putExtra("nilai_tugas", getNilaiTugas);
                    intent.putExtra("nilai_quiz", getNilaiQuiz);
                    intent.putExtra("nilai_uts", getNilaiUTS);
                    intent.putExtra("nilai_uas", getNilaiUAS);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });

    }
}