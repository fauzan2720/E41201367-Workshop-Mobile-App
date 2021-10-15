package com.example.intentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class OutputActivity extends AppCompatActivity {

    // membuat variabel
    private TextView outputNama, output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        // menginisialisasi id
        outputNama = findViewById(R.id.outputNama);
        output = findViewById(R.id.output);

        // memanggil data yang telah di kirim ke sini yang awalnya terdapat pada string di MainActivity
        String nama = getIntent().getExtras().getString("key_nama");
        String notelp = getIntent().getExtras().getString("key_notelp");
        String alamat = getIntent().getExtras().getString("key_alamat");
        String browsing = getIntent().getExtras().getString("key_browsing");

        outputNama.setText("Haii, " +nama);
        output.setText("Anda mengirim pesan ke: " +notelp
                +". Lokasi yang akan dituju: " +alamat
                +". Dan mengunjungi url: " +browsing);
    }
}