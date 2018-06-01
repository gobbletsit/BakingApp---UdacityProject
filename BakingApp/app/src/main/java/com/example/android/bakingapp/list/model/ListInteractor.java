package com.example.android.bakingapp.list.model;

import com.example.android.bakingapp.data.Recipe;

import java.util.List;

import io.reactivex.Observable;

public interface ListInteractor {

    Observable<List<Recipe>> getRecipeList();

}
