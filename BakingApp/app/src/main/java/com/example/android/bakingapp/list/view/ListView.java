package com.example.android.bakingapp.list.view;

import com.example.android.bakingapp.data.Ingredient;
import com.example.android.bakingapp.data.Recipe;
import com.example.android.bakingapp.data.Step;

import java.util.ArrayList;
import java.util.List;

public interface ListView {

    void setUpView (List<Recipe> recipeList);

    void onRecipeClicked(ArrayList<Ingredient> ingredientList, ArrayList<Step> stepsList);
}
