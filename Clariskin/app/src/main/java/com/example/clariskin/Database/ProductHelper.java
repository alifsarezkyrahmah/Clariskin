package com.example.clariskin.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.clariskin.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductHelper {

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public ProductHelper(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long addToFavorites(Product product) {
        ContentValues values = new ContentValues();
        values.put(DBContract.ProductEntry.COLUMN_PRODUCT_ID, product.getId());
        values.put(DBContract.ProductEntry.COLUMN_BRAND, product.getBrand());
        values.put(DBContract.ProductEntry.COLUMN_NAME, product.getName());
        values.put(DBContract.ProductEntry.COLUMN_DESCRIPTION, product.getDescription());
        values.put(DBContract.ProductEntry.COLUMN_USAGE, product.getHowToUse());
        values.put(DBContract.ProductEntry.COLUMN_TIMING, product.getGoodFor());
        values.put(DBContract.ProductEntry.COLUMN_SKIN_TYPE, product.getSkinTypes());
        values.put(DBContract.ProductEntry.COLUMN_CONCERN, product.getSkinIssues());
        values.put(DBContract.ProductEntry.COLUMN_IMAGE_RES_ID, product.getImageResId());

        return database.insert(DBContract.ProductEntry.TABLE_NAME, null, values);
    }

    public boolean removeFromFavorites(int productId) {
        return database.delete(DBContract.ProductEntry.TABLE_NAME,
                DBContract.ProductEntry.COLUMN_PRODUCT_ID + " = ?",
                new String[]{String.valueOf(productId)}) > 0;
    }

    public boolean isFavorite(int productId) {
        Cursor cursor = database.query(DBContract.ProductEntry.TABLE_NAME,
                null,
                DBContract.ProductEntry.COLUMN_PRODUCT_ID + " = ?",
                new String[]{String.valueOf(productId)},
                null, null, null);

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public List<Product> getAllFavorites() {
        List<Product> favoriteList = new ArrayList<>();

        Cursor cursor = database.query(DBContract.ProductEntry.TABLE_NAME,
                null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Product product = new Product(
                        cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.ProductEntry.COLUMN_PRODUCT_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DBContract.ProductEntry.COLUMN_BRAND)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DBContract.ProductEntry.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DBContract.ProductEntry.COLUMN_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DBContract.ProductEntry.COLUMN_USAGE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DBContract.ProductEntry.COLUMN_TIMING)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DBContract.ProductEntry.COLUMN_SKIN_TYPE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DBContract.ProductEntry.COLUMN_CONCERN)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.ProductEntry.COLUMN_IMAGE_RES_ID))
                );
                favoriteList.add(product);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return favoriteList;
    }
}
