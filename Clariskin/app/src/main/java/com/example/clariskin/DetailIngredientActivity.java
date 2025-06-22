package com.example.clariskin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager; // Import ini
import androidx.recyclerview.widget.RecyclerView; // Import ini

import com.example.clariskin.Adapter.RelatedProductAdapter; // Import Adapter Anda
import com.example.clariskin.Model.Ingredient;
import com.example.clariskin.Model.Product;
import com.example.clariskin.Model.StaticData;

import java.util.ArrayList;
import java.util.List;

public class DetailIngredientActivity extends AppCompatActivity {

    private ImageView ivImage, ivBack;
    private TextView tvName, tvDescription,
            tvRecommendations, tvSkinTypes, tvSkinIssues,
            tvCombinationOk, tvCombinationBad;
    // Ganti LinearLayout dengan RecyclerView
    private RecyclerView rvRelatedProducts;
    private RelatedProductAdapter relatedProductsAdapter;
    private List<Product> relatedProductsList = new ArrayList<>(); // List data untuk adapter
    private TextView tvProductsContainingLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ingredient);


        // Inisialisasi view
        ivImage = findViewById(R.id.iv_image);
        tvName = findViewById(R.id.tv_name);
        tvDescription = findViewById(R.id.tv_description);
        tvRecommendations = findViewById(R.id.tv_recommendations);
        tvSkinTypes = findViewById(R.id.tv_skin_types);
        tvSkinIssues = findViewById(R.id.tv_skin_issues);
        tvCombinationOk = findViewById(R.id.tv_combination_ok);
        tvCombinationBad = findViewById(R.id.tv_combination_bad);
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> onBackPressed());

        // Inisialisasi RecyclerView
        rvRelatedProducts = findViewById(R.id.rv_related_products);
       rvRelatedProducts.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        // Inisialisasi Adapter dan set ke RecyclerView
        relatedProductsAdapter = new RelatedProductAdapter(relatedProductsList, product -> {
            // Logika saat item produk diklik
            Intent intent = new Intent(DetailIngredientActivity.this, ProductDetailActivity.class);
            intent.putExtra("product", product); // Kirim objek Product sebagai Parcelable
            startActivity(intent);
        });
        rvRelatedProducts.setAdapter(relatedProductsAdapter);

        // Pastikan ID ini ada di activity_detail_ingredient.xml
        tvProductsContainingLabel = findViewById(R.id.tv_products_containing_label);


        // Ambil data dari intent
        Ingredient ingredient = getIntent().getParcelableExtra("ingredient");

        if (ingredient != null) {
            Log.d("CHECK", "Ingredient name: " + ingredient.getName());
            Log.d("CHECK", "Related product ids: " + ingredient.getRelatedProductIds());
            tvName.setText(ingredient.getName());
            tvDescription.setText(ingredient.getDescription());
            ivImage.setImageResource(ingredient.getImageResId());

            tvRecommendations.setText(ingredient.getRecommendations());
            tvSkinTypes.setText(ingredient.getSkinTypes());
            tvSkinIssues.setText(ingredient.getSkinIssues());
            tvCombinationOk.setText(ingredient.getCombinationOk());
            tvCombinationBad.setText(ingredient.getCombinationBad());

            // --- Tampilkan produk terkait ---
            populateRelatedProducts(ingredient.getRelatedProductIds());
        } else {
            Toast.makeText(this, "Data ingredient tidak ditemukan!", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void populateRelatedProducts(List<Integer> productIds) {
        relatedProductsList.clear(); // Bersihkan list data sebelum menambahkan yang baru

        if (productIds != null && !productIds.isEmpty()) {
            Log.d("DetailIngredient", "Found " + productIds.size() + " related product IDs for current ingredient.");

            // Pastikan judul "Products Containing This Ingredient" terlihat
            if (tvProductsContainingLabel != null) {
                tvProductsContainingLabel.setVisibility(View.VISIBLE);
            }
            rvRelatedProducts.setVisibility(View.VISIBLE); // Pastikan RecyclerView terlihat

            for (Integer productId : productIds) {
                Product product = StaticData.getProductById(productId);

                if (product != null) {
                    Log.d("DetailIngredient", "Adding product to list: " + product.getName() + " (ID: " + product.getId() + ")");
                    relatedProductsList.add(product); // Tambahkan produk ke list data Adapter
                } else {
                    Log.w("DetailIngredient", "Product with ID " + productId + " not found in StaticData.");
                }
            }
            relatedProductsAdapter.notifyDataSetChanged(); // Beri tahu Adapter bahwa data telah berubah
        } else {
            Log.d("DetailIngredient", "No related product IDs found for current ingredient or list is empty.");
            // Sembunyikan judul jika tidak ada produk terkait
            if (tvProductsContainingLabel != null) {
                tvProductsContainingLabel.setVisibility(View.GONE);
            }
            rvRelatedProducts.setVisibility(View.GONE); // Sembunyikan RecyclerView

            // Tampilkan pesan "No products listed" di tempat lain, atau biarkan kosong jika RV disembunyikan
            Toast.makeText(this, "Tidak ada produk terkait.", Toast.LENGTH_SHORT).show(); // Contoh pesan singkat
        }
    }

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}