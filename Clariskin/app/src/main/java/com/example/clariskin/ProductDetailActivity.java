package com.example.clariskin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clariskin.Database.ProductHelper;
import com.example.clariskin.Model.Ingredient;
import com.example.clariskin.Model.Product;
import com.example.clariskin.Model.StaticData;
import com.google.android.flexbox.FlexboxLayout;

import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView tvProductName, tvDescription, tvHowToUse, tvWhenToUse;
    private TextView tvGoodFor, tvSkinType, tvSkinIssues, tvEmptyIncompatible;
    private ImageView ivProductImage, ivBack;
    private FlexboxLayout flexboxKeyIngredients, flexboxIncompatibleIngredients;
    private Button btnAddToRoutine;

    private Product product;
    private ProductHelper productHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Inisialisasi view
        tvProductName = findViewById(R.id.tv_product_name);
        tvDescription = findViewById(R.id.tv_description);
        tvHowToUse = findViewById(R.id.tv_how_to_use);
        tvWhenToUse = findViewById(R.id.tv_when_to_use);
        tvGoodFor = findViewById(R.id.tv_good_for);
        tvSkinType = findViewById(R.id.tv_skin_types);
        tvSkinIssues = findViewById(R.id.tv_skin_issues);
        ivProductImage = findViewById(R.id.iv_product_image);
        ivBack = findViewById(R.id.iv_back);
        flexboxKeyIngredients = findViewById(R.id.flexbox_key_ingredients);
        flexboxIncompatibleIngredients = findViewById(R.id.flexbox_incompatible_ingredients);
        tvEmptyIncompatible = findViewById(R.id.tv_empty_incompatible);
        btnAddToRoutine = findViewById(R.id.btn_add_to_routine);

        ivBack.setOnClickListener(v -> onBackPressed());

        // Inisialisasi database
        productHelper = new ProductHelper(this);
        productHelper.open();

        // Ambil data produk dari intent
        product = getIntent().getParcelableExtra("product");

        if (product != null) {
            // Tampilkan data produk
            tvProductName.setText(product.getBrand() + " - " + product.getName());
            tvDescription.setText(product.getDescription());
            tvHowToUse.setText(product.getHowToUse());
            tvWhenToUse.setText(product.getWhenToUse());
            tvGoodFor.setText(product.getGoodFor());
            tvSkinType.setText(product.getSkinTypes());
            tvSkinIssues.setText(product.getSkinIssues());
            ivProductImage.setImageResource(product.getImageResId());

            // Key Ingredients
            flexboxKeyIngredients.removeAllViews();
            List<Integer> keyIngredientIds = product.getRelatedIngredientIds();
            if (keyIngredientIds != null && !keyIngredientIds.isEmpty()) {
                for (int id : keyIngredientIds) {
                    Ingredient ing = StaticData.getIngredientById(id);
                    if (ing != null) {
                        TextView chip = createChipView(ing.getName());
                        chip.setOnClickListener(v -> {
                            Intent intent = new Intent(this, DetailIngredientActivity.class);
                            intent.putExtra("ingredient", ing);
                            startActivity(intent);
                        });
                        flexboxKeyIngredients.addView(chip);
                    }
                }
            } else {
                TextView noIng = new TextView(this);
                noIng.setText("No key ingredients listed");
                noIng.setTextColor(Color.GRAY);
                noIng.setTextSize(14);
                flexboxKeyIngredients.addView(noIng);
            }

            // Incompatible Ingredients
            flexboxIncompatibleIngredients.removeAllViews();
            List<Integer> doNotUseWithIds = product.getDoNotUseWithIngredientIds();
            if (doNotUseWithIds != null && !doNotUseWithIds.isEmpty()) {
                flexboxIncompatibleIngredients.setVisibility(View.VISIBLE);
                tvEmptyIncompatible.setVisibility(View.GONE);
                for (int id : doNotUseWithIds) {
                    Ingredient ing = StaticData.getIngredientById(id);
                    if (ing != null) {
                        TextView chip = createChipView(ing.getName());
                        chip.setOnClickListener(v -> {
                            Intent intent = new Intent(this, DetailIngredientActivity.class);
                            intent.putExtra("ingredient", ing);
                            startActivity(intent);
                        });
                        flexboxIncompatibleIngredients.addView(chip);
                    }
                }
            } else {
                flexboxIncompatibleIngredients.setVisibility(View.GONE);
                tvEmptyIncompatible.setVisibility(View.VISIBLE);
            }

            // Tombol Rutin
            boolean isFavorite = productHelper.isFavorite(product.getId());
            updateButtonText(isFavorite);

            btnAddToRoutine.setOnClickListener(v -> {
                boolean isFav = productHelper.isFavorite(product.getId());
                if (!isFav) {
                    productHelper.addToFavorites(product);
                    Toast.makeText(this, "Added to routine", Toast.LENGTH_SHORT).show();
                } else {
                    productHelper.removeFromFavorites(product.getId());
                    Toast.makeText(this, "Removed from routine", Toast.LENGTH_SHORT).show();
                }
                updateButtonText(!isFav);
            });
        }
    }

    private TextView createChipView(String text) {
        TextView chip = new TextView(this);
        chip.setText(text);
        chip.setBackgroundResource(R.drawable.rounded_grey_button);
        chip.setTextColor(Color.BLACK);
        chip.setTextSize(14);
        chip.setPadding(32, 16, 32, 16);

        FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 24, 24);
        chip.setLayoutParams(params);
        return chip;
    }

    private void updateButtonText(boolean isFavorite) {
        btnAddToRoutine.setText(isFavorite ? "REMOVE FROM ROUTINE" : "ADD TO ROUTINE");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        productHelper.close();
    }
}
