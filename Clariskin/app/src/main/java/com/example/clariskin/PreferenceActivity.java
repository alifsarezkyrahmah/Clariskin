package com.example.clariskin; // Sesuaikan dengan package Anda

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button; // Tambahkan import
import android.widget.Spinner;
import android.widget.Toast; // Tambahkan import

import androidx.appcompat.app.AppCompatActivity;

import com.example.clariskin.RecommendProductFragment;
// Import jika Anda ingin langsung ke fragment rekomendasi
import com.example.clariskin.MainActivity; // Import MainActivity

import java.util.Arrays; // Tambahkan import
import java.util.List; // Tambahkan import

public class PreferenceActivity extends AppCompatActivity { // Nama Activity Anda

    private Spinner spinnerSkinType;
    private Spinner spinnerSkinProblem;
    private Button btnSavePreferences; // Deklarasi tombol simpan
    private Button btnViewRecommendations; // Deklarasi tombol view rekomendasi

    // Data untuk Spinner - Pastikan ini cocok persis dengan data di Product.getSkinTypes() dan Product.getSkinIssues()
    private final String[] skinTypesArray = {
            "Select Skin Type","Oily", "Dry", "Combination", "Normal", "Sensitive", "Acne-prone", "All Skin Types"
    };
    private final String[] skinProblemsArray = {
            "Select Skin Problem", "Acne", "Dark Spots","Dullness", "Hyperpigmentation", "Redness", "Dehydration",
            "Large Pores", "Fine Lines", "Sensitivity", "Uneven Tone", "Dark Circles"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference); // Mengacu ke layout XML Anda

        // Inisialisasi Spinner dari layout
        spinnerSkinType = findViewById(R.id.spinner_skin_type);
        spinnerSkinProblem = findViewById(R.id.spinner_skin_problem);
        btnSavePreferences = findViewById(R.id.btn_save_preferences); // Inisialisasi tombol
        btnViewRecommendations = findViewById(R.id.btn_view_recommendations); // Inisialisasi tombol

        // Adapter untuk Skin Type Spinner
        ArrayAdapter<String> skinTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, skinTypesArray);
        skinTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSkinType.setAdapter(skinTypeAdapter);

        // Adapter untuk Skin Problem Spinner
        ArrayAdapter<String> skinProblemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, skinProblemsArray);
        skinProblemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSkinProblem.setAdapter(skinProblemAdapter);

        // --- Muat preferensi yang sudah ada saat Activity dimulai ---
        loadUserPreferences();

        // --- Atur Listener untuk Tombol Simpan ---
        btnSavePreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserPreferences();
            }
        });

        // --- Atur Listener untuk Tombol Lihat Rekomendasi ---
        btnViewRecommendations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Opsional: Pastikan preferensi tersimpan sebelum pindah
                saveUserPreferences();

            Intent intent = new Intent(PreferenceActivity.this, MainActivity.class);
            intent.putExtra("navigateToRecommendations", true);
            startActivity(intent);
            finish(); // Tutup PreferenceActivity setelah navigasi
            }
        });

        // --- Opsional: Simpan otomatis saat pilihan spinner berubah ---
        spinnerSkinType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) { // Hindari menyimpan "Select Skin Type"
                    saveUserPreferences();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinnerSkinProblem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) { // Hindari menyimpan "Select Skin Problem"
                    saveUserPreferences();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    /**
     * Menyimpan pilihan pengguna saat ini ke SharedPreferences.
     */
    private void saveUserPreferences() {
        SharedPreferences sharedPref = getSharedPreferences(AppPreferences.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        String selectedSkinType = spinnerSkinType.getSelectedItem().toString();
        String selectedSkinProblem = spinnerSkinProblem.getSelectedItem().toString();

        // Hanya simpan jika pilihan bukan teks default "Select..."
        if (!selectedSkinType.startsWith("Select")) {
            editor.putString(AppPreferences.KEY_SKIN_TYPE, selectedSkinType);
        } else {
            editor.remove(AppPreferences.KEY_SKIN_TYPE); // Hapus jika user kembali ke default
        }

        if (!selectedSkinProblem.startsWith("Select")) {
            editor.putString(AppPreferences.KEY_SKIN_PROBLEM, selectedSkinProblem);
        } else {
            editor.remove(AppPreferences.KEY_SKIN_PROBLEM); // Hapus jika user kembali ke default
        }

        editor.apply(); // Gunakan apply() untuk menyimpan secara asynchronous
        Toast.makeText(this, "Skin profile saved!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Memuat preferensi pengguna yang tersimpan dari SharedPreferences
     * dan mengaturnya ke Spinner.
     */
    private void loadUserPreferences() {
        SharedPreferences sharedPref = getSharedPreferences(AppPreferences.PREF_NAME, Context.MODE_PRIVATE);

        // Ambil nilai yang tersimpan, berikan nilai default jika belum ada
        String savedSkinType = sharedPref.getString(AppPreferences.KEY_SKIN_TYPE, skinTypesArray[0]); // Default ke "Select Skin Type"
        String savedSkinProblem = sharedPref.getString(AppPreferences.KEY_SKIN_PROBLEM, skinProblemsArray[0]); // Default ke "Select Skin Problem"

        // Atur pilihan spinner sesuai dengan preferensi yang dimuat
        ArrayAdapter<String> skinTypeAdapter = (ArrayAdapter<String>) spinnerSkinType.getAdapter();
        int skinTypePosition = skinTypeAdapter.getPosition(savedSkinType);
        if (skinTypePosition >= 0) { // Pastikan posisi ditemukan
            spinnerSkinType.setSelection(skinTypePosition);
        }

        ArrayAdapter<String> skinProblemAdapter = (ArrayAdapter<String>) spinnerSkinProblem.getAdapter();
        int skinProblemPosition = skinProblemAdapter.getPosition(savedSkinProblem);
        if (skinProblemPosition >= 0) { // Pastikan posisi ditemukan
            spinnerSkinProblem.setSelection(skinProblemPosition);
        }
    }
}