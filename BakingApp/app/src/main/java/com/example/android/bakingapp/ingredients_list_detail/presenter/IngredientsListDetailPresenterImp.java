package com.example.android.bakingapp.ingredients_list_detail.presenter;

import com.example.android.bakingapp.data.Ingredient;
import com.example.android.bakingapp.ingredients_list_detail.view.IngredientListDetailsView;

import java.util.ArrayList;

public class IngredientsListDetailPresenterImp implements IngredientsListDetailPresenter {

    private IngredientListDetailsView mIngredientListDetailView;

    @Override
    public void setView(IngredientListDetailsView ingredientListDetailsView) {
        mIngredientListDetailView = ingredientListDetailsView;
    }

    @Override
    public void showDetails(ArrayList<Ingredient> ingredients) {
        mIngredientListDetailView.showDetails(ingredients);
    }
}
