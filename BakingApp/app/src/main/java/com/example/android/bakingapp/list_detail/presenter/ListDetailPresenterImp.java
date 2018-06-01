package com.example.android.bakingapp.list_detail.presenter;

import com.example.android.bakingapp.data.Ingredient;
import com.example.android.bakingapp.data.Recipe;
import com.example.android.bakingapp.data.Step;
import com.example.android.bakingapp.list.presenter.ListPresenter;
import com.example.android.bakingapp.list_detail.view.ListDetailView;

import java.util.ArrayList;

public class ListDetailPresenterImp implements ListDetailPresenter {

    private ListDetailView mListDetailView;

    @Override
    public void setView(ListDetailView listDetailView) {
        this.mListDetailView = listDetailView;
    }

    @Override
    public void showListDetails(ArrayList<Step> stepsList) {
        mListDetailView.showListDetails(stepsList);
    }

    @Override
    public void onStepDescriptionClick(Step step, int position, ArrayList<Step> steps) {
        mListDetailView.onStepClick(step, position, steps);
    }

    @Override
    public void onIngredientsButtonClick(ArrayList<Ingredient> ingredients) {
        mListDetailView.onIngredientsButtonClick(ingredients);
    }

    @Override
    public void destroy() {

    }

}
