package com.example.clariskin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.clariskin.DetailIngredientActivity;
import com.example.clariskin.Model.Product;
import com.example.clariskin.Model.Ingredient;
import com.example.clariskin.Model.StaticData;
import com.example.clariskin.ProductDetailActivity;
import com.example.clariskin.R;

import java.util.List;

public class ProductIngredientAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_PRODUCT = 0;
    private static final int TYPE_INGREDIENT = 1;

    private Context context;
    private List<Object> itemList;

    public ProductIngredientAdapter(Context context, List<Object> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getItemViewType(int position) {
        if (itemList.get(position) instanceof Product) {
            return TYPE_PRODUCT;
        } else {
            return TYPE_INGREDIENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_PRODUCT) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_products, parent, false);
            return new ProductViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_ingredients, parent, false);
            return new IngredientViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object item = itemList.get(position);

        if (holder instanceof ProductViewHolder && item instanceof Product) {
            Product product = (Product) item;
            ProductViewHolder viewHolder = (ProductViewHolder) holder;

            viewHolder.tvBrand.setText(product.getBrand());
            viewHolder.tvProductName.setText(product.getName());
            viewHolder.tvProductDescription.setText(product.getDescription());
            viewHolder.ivProductImage.setImageResource(product.getImageResId());
            holder.itemView.setOnClickListener(v -> {
                StaticData.initData(); // optional, kalau sudah di init sebelumnya
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("product", StaticData.getProductById(product.getId()));
                context.startActivity(intent);
            });

        } else if (holder instanceof IngredientViewHolder && item instanceof Ingredient) {
            Ingredient ingredient = (Ingredient) item;
            IngredientViewHolder viewHolder = (IngredientViewHolder) holder;

            viewHolder.tvIngredientName.setText(ingredient.getName());
            viewHolder.tvIngredientDesc.setText(ingredient.getDescription());
            viewHolder.ivIngredientImage.setImageResource(ingredient.getImageResId());

            holder.itemView.setOnClickListener(v -> {
                StaticData.initData(); // optional, kalau sudah di init sebelumnya
                Intent intent = new Intent(context, DetailIngredientActivity.class);
                intent.putExtra("ingredient", StaticData.getIngredientById(ingredient.getId()));
                context.startActivity(intent);
            });

        }

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
    public void updateData(List<Object> newData) {
        this.itemList = newData;
        notifyDataSetChanged();
    }


    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProductImage, ivFavorite;
        TextView tvBrand, tvProductName, tvProductDescription;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ivProductImage = itemView.findViewById(R.id.iv_product_image);
            tvBrand = itemView.findViewById(R.id.tv_brand);
            tvProductName = itemView.findViewById(R.id.tv_product_name);
            tvProductDescription = itemView.findViewById(R.id.tv_product_description);
        }
    }

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {
        TextView tvIngredientName, tvIngredientDesc;
        ImageView ivIngredientImage;

        public IngredientViewHolder(View itemView) {
            super(itemView);
            tvIngredientName = itemView.findViewById(R.id.tv_ingredient_name);
            tvIngredientDesc = itemView.findViewById(R.id.tv_ingredient_description);
            ivIngredientImage = itemView.findViewById(R.id.iv_ingredient);
        }
    }
}
