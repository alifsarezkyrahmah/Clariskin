//package com.example.clariskin.Database;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.util.Log;
//
//import com.example.clariskin.Model.Product;
//import com.example.clariskin.Model.Ingredient;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class SkincareHelper {
//    private DBHelper dbHelper;
//
//    public SkincareHelper(Context context) {
//        dbHelper = new DBHelper(context);
//    }
//
//    // ========================
//    // CREATE
//    // ========================
//    public long insertProduct(Product product) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("brand", product.getBrand());
//        values.put("name", product.getName());
//        values.put("description", product.getDescription());
//        values.put("how_to_use", product.getHowToUse());
//        values.put("when_to_use", product.getWhenToUse());
//        values.put("skin_types", product.getSkinTypes());
//        values.put("skin_issues", product.getSkinIssues());
//        values.put("image_name", product.getImageResId());
//        return db.insert("product", null, values);
//    }
//
//    public long insertIngredient(Ingredient ingredient) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("name", ingredient.getName());
//        values.put("description", ingredient.getDescription());
//        values.put("recommendations", ingredient.getRecommendations());
//        values.put("combination_ok", ingredient.getCombinationOk());
//        values.put("combination_bad", ingredient.getCombinationBad());
//        values.put("skin_types", ingredient.getSkinTypes());
//        values.put("skin_issues", ingredient.getSkinIssues());
//        values.put("image_name", ingredient.getImageResId());
//        return db.insert("ingredient", null, values);
//    }
//
//    // ========================
//    // READ
//    // ========================
//    public List<Product> getAllProducts() {
//        List<Product> productList = new ArrayList<>();
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM product", null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                Product product = new Product();
//                product.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
//                product.setBrand(cursor.getString(cursor.getColumnIndexOrThrow("brand")));
//                product.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
//                product.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("description")));
//                product.setHowToUse(cursor.getString(cursor.getColumnIndexOrThrow("how_to_use")));
//                product.setWhenToUse(cursor.getString(cursor.getColumnIndexOrThrow("when_to_use")));
//                product.setSkinTypes(cursor.getString(cursor.getColumnIndexOrThrow("skin_types")));
//                product.setSkinIssues(cursor.getString(cursor.getColumnIndexOrThrow("skin_issues")));
//                product.setImageResId(cursor.getString(cursor.getColumnIndexOrThrow("image_name")));
//                productList.add(product);
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
//        return productList;
//    }
//
//    public List<Ingredient> getAllIngredients() {
//        List<Ingredient> ingredientList = new ArrayList<>();
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM ingredient", null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                Ingredient ingredient = new Ingredient();
//                ingredient.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
//                ingredient.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
//                ingredient.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("description")));
//                ingredient.setRecommendations(cursor.getString(cursor.getColumnIndexOrThrow("recommendations")));
//                ingredient.setCombinationOk(cursor.getString(cursor.getColumnIndexOrThrow("combination_ok")));
//                ingredient.setCombinationBad(cursor.getString(cursor.getColumnIndexOrThrow("combination_bad")));
//                ingredient.setSkinTypes(cursor.getString(cursor.getColumnIndexOrThrow("skin_types")));
//                ingredient.setSkinIssues(cursor.getString(cursor.getColumnIndexOrThrow("skin_issues")));
//                ingredient.setImageResId(cursor.getString(cursor.getColumnIndexOrThrow("image_name")));
//                ingredientList.add(ingredient);
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
//        return ingredientList;
//    }
//
//    // ========================
//    // UPDATE
//    // ========================
//    public int updateProduct(Product product) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("brand", product.getBrand());
//        values.put("name", product.getName());
//        values.put("description", product.getDescription());
//        values.put("how_to_use", product.getHowToUse());
//        values.put("when_to_use", product.getWhenToUse());
//        values.put("skin_types", product.getSkinTypes());
//        values.put("skin_issues", product.getSkinIssues());
//        values.put("image_name", product.getImageResId());
//        return db.update("product", values, "id=?", new String[]{String.valueOf(product.getId())});
//    }
//
//    public int updateIngredient(Ingredient ingredient) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("name", ingredient.getName());
//        values.put("description", ingredient.getDescription());
//        values.put("recommendations", ingredient.getRecommendations());
//        values.put("combination_ok", ingredient.getCombinationOk());
//        values.put("combination_bad", ingredient.getCombinationBad());
//        values.put("skin_types", ingredient.getSkinTypes());
//        values.put("skin_issues", ingredient.getSkinIssues());
//        values.put("image_name", ingredient.getImageResId());
//        return db.update("ingredient", values, "id=?", new String[]{String.valueOf(ingredient.getId())});
//    }
//
//    // ========================
//    // DELETE
//    // ========================
//    public int deleteProduct(int productId) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        return db.delete("product", "id=?", new String[]{String.valueOf(productId)});
//    }
//
//    public int deleteIngredient(int ingredientId) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        return db.delete("ingredient", "id=?", new String[]{String.valueOf(ingredientId)});
//    }
//}
