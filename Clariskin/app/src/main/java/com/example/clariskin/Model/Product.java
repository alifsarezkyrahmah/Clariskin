package com.example.clariskin.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Product implements Parcelable {
    private int id;
    private String brand;
    private String name;
    private String description;
    private String howToUse;
    private String whenToUse;
    private String skinTypes;
    private String skinIssues;
    private int imageResId;
    private String goodFor; // Baru: durasi ketahanan produk
    private List<Integer> relatedIngredientIds = new ArrayList<>();
    private List<Integer> doNotUseWithIngredientIds = new ArrayList<>(); // Baru: ingredient yang tidak boleh digabung

    public Product() {}

    public Product(int id, String brand, String name, String description, String howToUse,
                   String whenToUse, String skinTypes, String skinIssues, int imageResId) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.howToUse = howToUse;
        this.whenToUse = whenToUse;
        this.skinTypes = skinTypes;
        this.skinIssues = skinIssues;
        this.imageResId = imageResId;
    }

    protected Product(Parcel in) {
        id = in.readInt();
        brand = in.readString();
        name = in.readString();
        description = in.readString();
        howToUse = in.readString();
        whenToUse = in.readString();
        skinTypes = in.readString();
        skinIssues = in.readString();
        imageResId = in.readInt();
        goodFor = in.readString();
        relatedIngredientIds = new ArrayList<>();
        doNotUseWithIngredientIds = new ArrayList<>();
        in.readList(relatedIngredientIds, Integer.class.getClassLoader());
        in.readList(doNotUseWithIngredientIds, Integer.class.getClassLoader());
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    // Getters dan Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getHowToUse() { return howToUse; }
    public void setHowToUse(String howToUse) { this.howToUse = howToUse; }

    public String getWhenToUse() { return whenToUse; }
    public void setWhenToUse(String whenToUse) { this.whenToUse = whenToUse; }

    public String getSkinTypes() { return skinTypes; }
    public void setSkinTypes(String skinTypes) { this.skinTypes = skinTypes; }

    public String getSkinIssues() { return skinIssues; }
    public void setSkinIssues(String skinIssues) { this.skinIssues = skinIssues; }

    public int getImageResId() { return imageResId; }
    public void setImageResId(int imageResId) { this.imageResId = imageResId; }

    public String getGoodFor() { return goodFor; }
    public void setGoodFor(String goodFor) { this.goodFor = goodFor; }

    public List<Integer> getRelatedIngredientIds() { return relatedIngredientIds; }
    public void setRelatedIngredientIds(List<Integer> relatedIngredientIds) {
        this.relatedIngredientIds = relatedIngredientIds;
    }

    public List<Integer> getDoNotUseWithIngredientIds() { return doNotUseWithIngredientIds; }
    public void setDoNotUseWithIngredientIds(List<Integer> doNotUseWithIngredientIds) {
        this.doNotUseWithIngredientIds = doNotUseWithIngredientIds;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(brand);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(howToUse);
        dest.writeString(whenToUse);
        dest.writeString(skinTypes);
        dest.writeString(skinIssues);
        dest.writeInt(imageResId);
        dest.writeString(goodFor);
        dest.writeList(relatedIngredientIds);
        dest.writeList(doNotUseWithIngredientIds);
    }
}
