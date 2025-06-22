package com.example.clariskin.Model;

public class ProductIngredient {
    private int id;
    private int productId;
    private int ingredientId;

    public ProductIngredient(int id, int productId, int ingredientId) {
        this.id = id;
        this.productId = productId;
        this.ingredientId = ingredientId;
    }

    public ProductIngredient() {}

    // Getter dan Setter ...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }
}

