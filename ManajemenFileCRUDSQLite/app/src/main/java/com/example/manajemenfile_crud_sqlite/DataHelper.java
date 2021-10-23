package com.example.manajemenfile_crud_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper { // SQLiteOpenHelper untuk memproses SQLite Helper

    private static final String DATABASE_NAME = "biodata_diri.db"; // membuat database
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) { // membuat construktor
        super(context,DATABASE_NAME, null, DATABASE_VERSION); // memanggil database
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        // membuat tabel dan atribut nya
        // primary key artinya data yang tidak boleh sama
        String sql = "CREATE TABLE biodata(no integer PRIMARY KEY, nama text NULL, tgl text NULL, jk text NULL, alamat text NULL);";
        Log.d("Data", "onCreate: " +sql);
        db.execSQL(sql); // mengeksekusi sql
        // buat data
        // sql = "INSERT INTO biodata (no, nama, tgl, jk, alamat) VALUES ('1', 'Fauzan Abdillah', '2001-05-27', 'Laki-Laki', 'Mangli - Jember');";
        // db.execSQL(sql); // mengeksekusi sql
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // TODO Auto-generated method stub
    }
}
