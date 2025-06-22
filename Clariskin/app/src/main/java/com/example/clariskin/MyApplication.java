// MyApplication.java
package com.example.clariskin;

import android.app.Application;
import com.example.clariskin.Model.StaticData;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        StaticData.initData(); // Panggil initData di sini
        android.util.Log.d("MyApplication", "StaticData.initData() called from Application class.");
    }
}