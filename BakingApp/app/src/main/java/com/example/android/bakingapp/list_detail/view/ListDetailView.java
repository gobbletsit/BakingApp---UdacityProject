package com.example.android.bakingapp.list_detail.view;

import com.example.android.bakingapp.data.Ingredient;
import com.example.android.bakingapp.data.Step;

import java.util.ArrayList;

public interface ListDetailView {

    void showListDetails(ArrayList<Step> stepsList);

    void onStepClick(Step step, int position, ArrayList<Step> stepArrayList);

    void onIngredientsButtonClick(ArrayList<Ingredient> ingredients);

}
