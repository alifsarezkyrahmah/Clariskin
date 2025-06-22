package com.example.clariskin.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clariskin.Model.Product;
import com.example.clariskin.R;

import java.util.List;

public class RelatedProductAdapter extends RecyclerView.Adapter<RelatedProductAdapter.ViewHolder> {

    public interface OnProductClickListener {
        void onProductClick(Product product);
    }

    private List<Product> productList;
    private OnProductClickListener listener;

    public RelatedProductAdapter(List<Product> productList, OnProductClickListener listener) {
        this.productList = productList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RelatedProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_products, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelatedProductAdapter.ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.tvProductName.setText(product.getBrand() + " - " + product.getName());
        holder.ivProductImage.setImageResource(product.getImageResId());
        holder.tvProductBrand.setText(product.getBrand());
        holder.tvDesc.setText(product.getDescription());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onProductClick(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvProductName, tvProductBrand, tvDesc;
        ImageView ivProductImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tv_product_name);
            ivProductImage = itemView.findViewById(R.id.iv_product_image); // Pastikan ID ini ada di XML
            tvProductBrand = itemView.findViewById(R.id.tv_brand);
            tvDesc = itemView.findViewById(R.id.tv_product_description); // Pastikan ID ini ada di XML

        }
    }
}
