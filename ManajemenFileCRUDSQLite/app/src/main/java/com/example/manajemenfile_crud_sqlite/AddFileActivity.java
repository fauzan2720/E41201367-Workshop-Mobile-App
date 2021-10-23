package com.example.manajemenfile_crud_sqlite;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddFileActivity extends AppCompatActivity {

    private EditText editText;
    private int STORAGE_PERMISSION_CODE = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_file);

        // menginisialisasi
        editText = findViewById(R.id.editText);
    }

    public void next(View view) {
        Intent intent = new Intent(AddFileActivity.this, ViewActivity.class); // pindah ke halaman ViewActivity
        startActivity(intent); // mulai proses berpindah
    }

    public void savePublic(View view) { // menyimpan kedalam penyimpanan private, biasanya di file download
        // memberi izin untuk mengakses penyimpanan eksternal
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        String info = editText.getText().toString();
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS); // nama folder
        File myFile = new File(folder,"myData1.txt"); // nama file
        writeData(myFile, info);
        editText.setText("");
    }

    public void savePrivate(View view) { // menyimpan kedalam penyimpanan private
        String info = editText.getText().toString();
        File folder = getExternalFilesDir("fauzan"); // nama folder
        File myFile = new File(folder, "myData2.txt"); // nama file
        writeData(myFile, info);
        editText.setText("");
    }

    private void writeData(File myFile, String data) { // membaca data
        FileOutputStream fileOutputStream = null;
        try {
            System.out.println("TES");
            fileOutputStream = new FileOutputStream(myFile);
            fileOutputStream.write(data.getBytes());
            Toast.makeText(this, "Done" +myFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}