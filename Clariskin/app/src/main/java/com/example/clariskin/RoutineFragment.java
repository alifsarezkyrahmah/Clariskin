package com.example.clariskin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clariskin.Adapter.FavoriteAdapter;
import com.example.clariskin.Database.ProductHelper;
import com.example.clariskin.Model.Product;
import com.example.clariskin.ProductDetailActivity;

import android.content.Intent;

import java.util.List;

public class RoutineFragment extends Fragment {

    private RecyclerView recyclerView;
    private FavoriteAdapter adapter;
    private ProductHelper productHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_routine, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewFavorites);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        productHelper = new ProductHelper(getContext());
        productHelper.open();

        List<Product> favoriteProducts = productHelper.getAllFavorites();

        adapter = new FavoriteAdapter(getContext(), favoriteProducts, new FavoriteAdapter.OnItemClickListener() {
            @Override
            public void onHeartClick(Product product) {
                productHelper.removeFromFavorites(product.getId());
                loadFavorites();
            }

            @Override
            public void onItemClick(Product product) {
                Intent intent = new Intent(getContext(), ProductDetailActivity.class);
                intent.putExtra("product", product);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);

        return view;
    }

    private void loadFavorites() {
        List<Product> updatedList = productHelper.getAllFavorites();
        adapter.updateData(updatedList);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        productHelper.close();
    }
}
