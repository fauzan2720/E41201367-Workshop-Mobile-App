package com.example.fragmentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    // membuat variabel
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // menginisialisasi
        bottomNavigation = findViewById(R.id.bottomNavigation);

        // menjalankan fragment pada saat pertama kali aplikasi di buka
        loadFragment(new BerandaFragment());

        // memberi fungsi pada bottomNavigation
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null; // membuat nilai awal fragment ketika di klik, yaitu null

                switch (item.getItemId()) { // percabangan switch case, jika item menu yang diklik

                    case R.id.beranda: // jika klik tombol beranda
                        selectedFragment = new BerandaFragment(); // maka memanggil beranda yang disimpan ke dalam objek selected
                        break; // program di hentikan

                    // jika pernyataan diatas menyatakan false, maka akan melanjutkan pengecekan pada pernyataan berikutnya
                    case R.id.kompetisi: // jika klik tombol kompetisi, maka
                        selectedFragment = new KompetisiFragment(); // maka memanggil kompetisi yang disimpan ke dalam objek selected
                        break; // program di hentikan

                    // jika pernyataan diatas menyatakan false, maka akan melanjutkan pengecekan pada pernyataan berikutnya
                    case R.id.profil: // jika klik tombol profil
                        selectedFragment = new ProfilFragment(); // maka memanggil profil yang disimpan ke dalam objek selected
                        break; // program di hentikan
                }

                // memanggil fungsi untuk mengubah setiap fragment
                loadFragment(selectedFragment);

                return true;
            }
        });
    }

    // membuat method yang nantinya akan memanggil fragment
    private void loadFragment(Fragment fragment) {
        // membuat fungsi untuk mengubah setiap fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
    }
}