package com.example.clariskin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clariskin.Adapter.ProductIngredientAdapter;
import com.example.clariskin.Model.Ingredient;
import com.example.clariskin.Model.Product;
import com.example.clariskin.Model.StaticData;
import com.example.clariskin.R;

import java.util.ArrayList;
import java.util.List;
public class HomeFragment extends Fragment {

    private TextView tvBrowseCategories, tvNotFound;
    private LinearLayout layoutIngredientsHeader;

    private RecyclerView recyclerViewProducts, recyclerViewIngredients;
    private ProductIngredientAdapter productAdapter, ingredientAdapter;
    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tvBrowseCategories = view.findViewById(R.id.tvBrowseCategories);
        layoutIngredientsHeader = view.findViewById(R.id.layoutIngredientsHeader);
        tvNotFound = view.findViewById(R.id.tvNotFound);
        recyclerViewProducts = view.findViewById(R.id.recyclerViewProducts);
        recyclerViewIngredients = view.findViewById(R.id.recyclerViewIngredients);
        searchView = view.findViewById(R.id.searchView);

        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewIngredients.setLayoutManager(new LinearLayoutManager(getContext()));

        // Pastikan data dummy sudah dimuat
        StaticData.initData();

        // Gunakan data dari StaticData
        List<Object> productObjects = new ArrayList<>(StaticData.productList);
        List<Object> ingredientObjects = new ArrayList<>(StaticData.ingredientList);

        productAdapter = new ProductIngredientAdapter(getContext(), productObjects);
        ingredientAdapter = new ProductIngredientAdapter(getContext(), ingredientObjects);

        recyclerViewProducts.setAdapter(productAdapter);
        recyclerViewIngredients.setAdapter(ingredientAdapter);

        setupSearchListener();

        return view;
    }

    private void setupSearchListener() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterData(query.trim());
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterData(newText.trim());
                return true;
            }
        });
    }

    private void filterData(String query) {
        List<Object> filteredProducts = new ArrayList<>();
        List<Object> filteredIngredients = new ArrayList<>();

        for (Product p : StaticData.productList) {
            if (p.getName().toLowerCase().contains(query.toLowerCase()) ||
                    p.getBrand().toLowerCase().contains(query.toLowerCase()) ||
                    p.getDescription().toLowerCase().contains(query.toLowerCase())) {
                filteredProducts.add(p);
            }
        }

        for (Ingredient ing : StaticData.ingredientList) {
            if (ing.getName().toLowerCase().contains(query.toLowerCase()) ||
                    ing.getDescription().toLowerCase().contains(query.toLowerCase())) {
                filteredIngredients.add(ing);
            }
        }

        // Update adapter
        productAdapter.updateData(filteredProducts);
        ingredientAdapter.updateData(filteredIngredients);

        // Visibility logic
        if (filteredProducts.isEmpty() && filteredIngredients.isEmpty()) {
            // Tidak ada hasil
            recyclerViewProducts.setVisibility(View.GONE);
            recyclerViewIngredients.setVisibility(View.GONE);
            tvBrowseCategories.setVisibility(View.GONE);
            layoutIngredientsHeader.setVisibility(View.GONE);
            tvNotFound.setVisibility(View.VISIBLE);
        } else {
            tvNotFound.setVisibility(View.GONE);

            if (filteredProducts.isEmpty()) {
                recyclerViewProducts.setVisibility(View.GONE);
                tvBrowseCategories.setVisibility(View.GONE);
            } else {
                recyclerViewProducts.setVisibility(View.VISIBLE);
                tvBrowseCategories.setVisibility(View.VISIBLE);
            }

            if (filteredIngredients.isEmpty()) {
                recyclerViewIngredients.setVisibility(View.GONE);
                layoutIngredientsHeader.setVisibility(View.GONE);
            } else {
                recyclerViewIngredients.setVisibility(View.VISIBLE);
                layoutIngredientsHeader.setVisibility(View.VISIBLE);
            }
        }
    }


}
