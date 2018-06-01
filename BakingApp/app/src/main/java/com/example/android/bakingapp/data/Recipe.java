package com.example.android.bakingapp.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;

import com.google.gson.annotations.SerializedName;

import java.util.jar.Pack200;

import static com.example.android.bakingapp.data.Ingredient.CREATOR;

public class Recipe implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("ingredients")
    private Ingredient[] ingredients;

    @SerializedName("steps")
    private Step[] steps;

    @SerializedName("servings")
    private int servings;

    @SerializedName("image")
    private String imageURL;

    public Recipe(Parcel in){
        id = in.readInt();
        name = in.readString();
        ingredients = in.createTypedArray(Ingredient.CREATOR);
        steps = in.createTypedArray(Step.CREATOR);
        servings = in.readInt();
        imageURL = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeTypedArray(ingredients, 0);
        dest.writeTypedArray(steps, 0);
        dest.writeInt(servings);
        dest.writeString(imageURL);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }

    public Step[] getSteps() {
        return steps;
    }

    public void setSteps(Step[] steps) {
        this.steps = steps;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
