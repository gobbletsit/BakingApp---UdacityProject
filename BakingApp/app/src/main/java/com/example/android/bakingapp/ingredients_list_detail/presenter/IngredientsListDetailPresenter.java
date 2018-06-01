package com.example.android.bakingapp.ingredients_list_detail.presenter;

import com.example.android.bakingapp.data.Ingredient;
import com.example.android.bakingapp.ingredients_list_detail.view.IngredientListDetailsView;

import java.util.ArrayList;

public interface IngredientsListDetailPresenter {

    void setView(IngredientListDetailsView ingredientListDetailsView);

    void showDetails(ArrayList<Ingredient> ingredients);

}
