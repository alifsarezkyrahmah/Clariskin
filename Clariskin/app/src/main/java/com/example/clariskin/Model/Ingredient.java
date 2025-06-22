package com.example.clariskin.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Ingredient implements Parcelable {
    private int id;
    private String name;
    private String description;
    private String recommendations;
    private String combinationOk;
    private String combinationBad;
    private String skinTypes;
    private String skinIssues;
    private int imageResId; // ← menggantikan imageName
    private List<Integer> relatedProductIds = new ArrayList<>();


    public Ingredient() {}


    public Ingredient(int id, String name, String description, String recommendations,
                      String combinationOk, String combinationBad,
                      String skinTypes, String skinIssues, int imageResId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.recommendations = recommendations;
        this.combinationOk = combinationOk;
        this.combinationBad = combinationBad;
        this.skinTypes = skinTypes;
        this.skinIssues = skinIssues;
        this.imageResId = imageResId;
    }



    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    // Getters dan Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getRecommendations() { return recommendations; }
    public void setRecommendations(String recommendations) { this.recommendations = recommendations; }

    public String getCombinationOk() { return combinationOk; }
    public void setCombinationOk(String combinationOk) { this.combinationOk = combinationOk; }

    public String getCombinationBad() { return combinationBad; }
    public void setCombinationBad(String combinationBad) { this.combinationBad = combinationBad; }

    public String getSkinTypes() { return skinTypes; }
    public void setSkinTypes(String skinTypes) { this.skinTypes = skinTypes; }

    public String getSkinIssues() { return skinIssues; }
    public void setSkinIssues(String skinIssues) { this.skinIssues = skinIssues; }

    public int getImageResId() { return imageResId; }
    public void setImageResId(int imageResId) { this.imageResId = imageResId; }

    public List<Integer> getRelatedProductIds() { return relatedProductIds; }
    public void setRelatedProductIds(List<Integer> relatedProductIds) {
        this.relatedProductIds = relatedProductIds;
    }
    protected Ingredient(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        recommendations = in.readString();
        combinationOk = in.readString();
        combinationBad = in.readString();
        skinTypes = in.readString();
        skinIssues = in.readString();
        imageResId = in.readInt();
        relatedProductIds = new ArrayList<>();
        in.readList(relatedProductIds, Integer.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(recommendations);
        dest.writeString(combinationOk);
        dest.writeString(combinationBad);
        dest.writeString(skinTypes);
        dest.writeString(skinIssues);
        dest.writeInt(imageResId);
        dest.writeList(relatedProductIds);
    }

}
