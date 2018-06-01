package com.example.android.bakingapp.list_detail.presenter;


import com.example.android.bakingapp.data.Ingredient;
import com.example.android.bakingapp.data.Step;
import com.example.android.bakingapp.list_detail.view.ListDetailView;

import java.util.ArrayList;

public interface ListDetailPresenter {

    void setView(ListDetailView listDetailView);

    void showListDetails(ArrayList<Step> stepsList);

    void onStepDescriptionClick(Step step, int position, ArrayList<Step> steps);

    void onIngredientsButtonClick(ArrayList<Ingredient> ingredientsList);

    void destroy();

}
