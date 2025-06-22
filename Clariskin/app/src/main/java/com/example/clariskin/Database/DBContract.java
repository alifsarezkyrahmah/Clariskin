package com.example.clariskin.Database;

import android.provider.BaseColumns;

public final class DBContract {
    private DBContract() {}

    public static class ProductEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorite_products";
        public static final String COLUMN_PRODUCT_ID = "product_id";
        public static final String COLUMN_BRAND = "brand";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_USAGE = "usage";
        public static final String COLUMN_TIMING = "timing";
        public static final String COLUMN_SKIN_TYPE = "skin_type";
        public static final String COLUMN_CONCERN = "concern";
        public static final String COLUMN_IMAGE_RES_ID = "image_res_id";
    }
}
