package com.example.clariskin.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "clariskin.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String SQL_CREATE_FAVORITES =
            "CREATE TABLE " + DBContract.ProductEntry.TABLE_NAME + " (" +
                    DBContract.ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBContract.ProductEntry.COLUMN_PRODUCT_ID + " INTEGER," +
                    DBContract.ProductEntry.COLUMN_BRAND + " TEXT," +
                    DBContract.ProductEntry.COLUMN_NAME + " TEXT," +
                    DBContract.ProductEntry.COLUMN_DESCRIPTION + " TEXT," +
                    DBContract.ProductEntry.COLUMN_USAGE + " TEXT," +
                    DBContract.ProductEntry.COLUMN_TIMING + " TEXT," +
                    DBContract.ProductEntry.COLUMN_SKIN_TYPE + " TEXT," +
                    DBContract.ProductEntry.COLUMN_CONCERN + " TEXT," +
                    DBContract.ProductEntry.COLUMN_IMAGE_RES_ID + " INTEGER" +
                    ");";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_FAVORITES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Kosongkan dulu tabel dan buat ulang jika versi berubah
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.ProductEntry.TABLE_NAME);
        onCreate(db);
    }
}
