package com.example.clariskin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clariskin.Model.StaticData; // Penting: import StaticData

public class WelcomeActivity extends AppCompatActivity {

    // Durasi tampilan splash screen dalam milidetik (misalnya 3 detik)
    private static final int SPLASH_DURATION = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Opsional: Sembunyikan ActionBar/Toolbar jika ada
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Opsional: Sembunyikan Status Bar untuk tampilan fullscreen
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //         WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // Inisialisasi data statis di sini (HANYA SEKALI)
        // Ini adalah tempat TERBAIK untuk memanggil StaticData.initData()
        StaticData.initData();

        // Gunakan Handler untuk menunda transisi ke MainActivity
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Buat Intent untuk memulai MainActivity
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class); // Ganti MainActivity.class dengan kelas utama Anda
                startActivity(intent);

                // Tutup Splash Screen Activity agar pengguna tidak bisa kembali ke sana
                finish();
            }
        }, SPLASH_DURATION);
    }
}