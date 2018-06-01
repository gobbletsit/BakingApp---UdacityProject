package com.example.android.bakingapp.list.presenter;

import android.content.Context;

import com.example.android.bakingapp.data.Ingredient;
import com.example.android.bakingapp.data.Recipe;
import com.example.android.bakingapp.data.Step;
import com.example.android.bakingapp.list.view.ListView;

import java.util.ArrayList;

public interface ListPresenter {

    void showRecipes();

    void setListView(ListView listView);

    void onRecipeClick(Context context, ArrayList<Ingredient> ingredientList, ArrayList<Step> stepList, String recipeName);

    void destroy();

}
