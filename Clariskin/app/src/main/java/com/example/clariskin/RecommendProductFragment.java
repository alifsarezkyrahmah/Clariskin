package com.example.clariskin; // Sesuaikan dengan package Anda

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager; // Import ini
import androidx.recyclerview.widget.RecyclerView;

import com.example.clariskin.PreferenceActivity;
import com.example.clariskin.R;
import com.example.clariskin.Adapter.ProductIngredientAdapter; // Import ProductIngredientAdapter Anda
import com.example.clariskin.Model.Product;
import com.example.clariskin.Model.StaticData;
import com.example.clariskin.AppPreferences;

import java.util.ArrayList;
import java.util.List;

public class RecommendProductFragment extends Fragment {

    private TextView tvUserPreferencesInfo;
    private TextView tvNoRecommendationsMessage;
    private Button btnChangePreferences;
    private RecyclerView rvRecommendedProducts;
    private ProductIngredientAdapter productIngredientAdapter; // Ganti ProductAdapter dengan ProductIngredientAdapter
    private List<Object> recommendedItemList = new ArrayList<>(); // Ganti Product dengan Object untuk adapter campuran

    public RecommendProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend_product, container, false);

        tvUserPreferencesInfo = view.findViewById(R.id.tv_user_preferences_info);
        tvNoRecommendationsMessage = view.findViewById(R.id.tv_no_recommendations_message);
        btnChangePreferences = view.findViewById(R.id.btn_change_preferences);
        rvRecommendedProducts = view.findViewById(R.id.rv_recommended_products);

        // Setup RecyclerView dengan GridLayoutManager
        rvRecommendedProducts.setLayoutManager(new GridLayoutManager(requireContext(), 2)); // 2 kolom untuk grid
        productIngredientAdapter = new ProductIngredientAdapter(requireContext(), recommendedItemList);
        rvRecommendedProducts.setAdapter(productIngredientAdapter);

        // Atur Listener untuk Tombol "Change Preferences"
        btnChangePreferences.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), PreferenceActivity.class);
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        displayProductRecommendations();
    }

    @Override
    public void onResume() {
        super.onResume();
        displayProductRecommendations();
    }

    private void displayProductRecommendations() {
        SharedPreferences sharedPref = requireContext().getSharedPreferences(AppPreferences.PREF_NAME, Context.MODE_PRIVATE);
        String userSkinType = sharedPref.getString(AppPreferences.KEY_SKIN_TYPE, null);
        String userSkinProblem = sharedPref.getString(AppPreferences.KEY_SKIN_PROBLEM, null);

        Log.d("RecommendedProducts", "User Skin Type: " + userSkinType + ", User Skin Problem: " + userSkinProblem);

        // Reset tampilan
        recommendedItemList.clear(); // Bersihkan list untuk adapter
        tvNoRecommendationsMessage.setVisibility(View.GONE);
        rvRecommendedProducts.setVisibility(View.VISIBLE);
        btnChangePreferences.setVisibility(View.VISIBLE);

        // Cek apakah preferensi sudah diatur
        if (userSkinType == null || userSkinProblem == null || userSkinType.startsWith("Select") || userSkinProblem.startsWith("Select")) {
            tvUserPreferencesInfo.setText("Please select your skin type and problem in your Skin Profile to get personalized recommendations.");
            tvNoRecommendationsMessage.setText("Personalized recommendations are not available until you set your preferences.");
            tvNoRecommendationsMessage.setVisibility(View.VISIBLE);
            rvRecommendedProducts.setVisibility(View.GONE);
            btnChangePreferences.setVisibility(View.VISIBLE);
            productIngredientAdapter.updateData(new ArrayList<>()); // Kosongkan adapter
            Log.d("RecommendedProducts", "Preferences not set or default. Hiding RV.");
            return;
        }

        tvUserPreferencesInfo.setText("Showing recommendations for " + userSkinType + " skin with " + userSkinProblem + " issues.");

        List<Product> allProducts = StaticData.productList;
        List<Object> filteredProductsAsObjects = new ArrayList<>(); // Gunakan List<Object>

        Log.d("RecommendedProducts", "Total products in StaticData: " + allProducts.size());

        for (Product product : allProducts) {
            String productSkinTypes = product.getSkinTypes().toLowerCase();
            String productSkinIssues = product.getSkinIssues().toLowerCase();
            String lowerUserSkinType = userSkinType.toLowerCase();
            String lowerUserSkinProblem = userSkinProblem.toLowerCase();

            boolean matchesSkinType = productSkinTypes.contains(lowerUserSkinType);
            boolean matchesSkinProblem = productSkinIssues.contains(lowerUserSkinProblem);

            Log.d("RecommendedProducts", "Checking product: " + product.getName() +
                    " | Product SkinType: '" + productSkinTypes + "' (matches: " + matchesSkinType + ")" +
                    " | Product SkinIssues: '" + productSkinIssues + "' (matches: " + matchesSkinProblem + ")");


            if (matchesSkinType && matchesSkinProblem) {
                filteredProductsAsObjects.add(product); // Tambahkan Product sebagai Object
                Log.d("RecommendedProducts", "Product matched! Adding: " + product.getName());
            }
        }

        if (filteredProductsAsObjects.isEmpty()) {
            tvNoRecommendationsMessage.setText("No specific recommendations found for " + userSkinType + " skin with " + userSkinProblem + " issues. Try exploring all products or adjust your preferences.");
            tvNoRecommendationsMessage.setVisibility(View.VISIBLE);
            rvRecommendedProducts.setVisibility(View.GONE);
            Log.d("RecommendedProducts", "No matched products found.");
        } else {
            productIngredientAdapter.updateData(filteredProductsAsObjects); // Perbarui adapter dengan daftar Object
            tvNoRecommendationsMessage.setVisibility(View.GONE);
            rvRecommendedProducts.setVisibility(View.VISIBLE);
            Log.d("RecommendedProducts", "Displaying " + filteredProductsAsObjects.size() + " recommended products.");
        }
    }
}