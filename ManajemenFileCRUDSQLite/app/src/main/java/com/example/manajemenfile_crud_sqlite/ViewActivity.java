package com.example.manajemenfile_crud_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ViewActivity extends AppCompatActivity {

    private TextView showText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        // menginisialisasi
        showText = findViewById(R.id.getText);
    }

    public void back(View view) { // kembali ke halaman sebelumnya
        Intent intent = new Intent(ViewActivity.this, AddFileActivity.class); // pindah ke halaman addFileActivity
        startActivity(intent); // mulai proses berpindah
        finish(); // menyelesaikan activity
    }

    public void getPublic(View view) { // memanggil file yang disimpan kedalam penyimpanan public, biasanya di folder download
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS); // nama folder
        File myFile = new File(folder, "myData1.txt"); // nama file
        String text = getData(myFile);
        if (text != null) {
            showText.setText(text); // membaca file
        } else {
            showText.setText("No Data"); // jika file tidak ada
        }
    }

    public void getPrivate(View view) { // memanggil file yang disimpan kedalam penyimpanan private, yang beradda didalam folder fauzan
        File folder = getExternalFilesDir("fauzan"); // nama folder
        File myFile = new File(folder, "myData2.txt"); // nama file
        String text = getData(myFile);
        if (text != null) {
            showText.setText(text); // membaca file
        } else {
            showText.setText("No Data"); // jika file tidak ada
        }
    }

    private String getData(File myFile) { // mengambil data
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(myFile);
            int i = -1;
            StringBuffer buffer = new StringBuffer();
            while ((i = fileInputStream.read()) != -1) {
                buffer.append((char) i);
            }
            return buffer.toString(); // mengembalikan nilai buffer.toString()
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null; // mengembalikan nilai null
    }


}