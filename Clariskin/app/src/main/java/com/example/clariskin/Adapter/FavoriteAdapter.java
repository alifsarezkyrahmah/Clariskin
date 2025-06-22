package com.example.clariskin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.clariskin.Model.Product;
import com.example.clariskin.Model.StaticData;
import com.example.clariskin.ProductDetailActivity;
import com.example.clariskin.R;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private Context context;
    private List<Product> favoriteList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onHeartClick(Product product);
        void onItemClick(Product product);
    }

    public FavoriteAdapter(Context context, List<Product> favoriteList, OnItemClickListener listener) {
        this.context = context;
        this.favoriteList = favoriteList;
        this.listener = listener;
    }

    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position) {
        Product product = favoriteList.get(position);
        holder.textViewTitle.setText(product.getName());
        holder.imageView.setImageResource(product.getImageResId());

        holder.heart.setOnClickListener(v -> {
            if (listener != null) listener.onHeartClick(product);
        });
        holder.itemView.setOnClickListener(v -> {
            StaticData.initData(); // optional, kalau sudah di init sebelumnya
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("product", StaticData.getProductById(product.getId()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, heart;
        TextView textViewTitle;

        public FavoriteViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewFavorite);
            textViewTitle = itemView.findViewById(R.id.textViewFavoriteTitle);
            heart = itemView.findViewById(R.id.imageViewHeart);
        }
    }

    public void updateData(List<Product> newList) {
        this.favoriteList = newList;
        notifyDataSetChanged();
    }
}
