package com.example.android.bakingapp.di;

import com.example.android.bakingapp.ingredients_list_detail.view.IngredientsListDetailFragment;

import dagger.Subcomponent;

@Subcomponent (modules = {IngredientsListDetailModule.class})
public interface IngredientsListDetailComponent {

    void inject (IngredientsListDetailFragment ingredientsListDetailFragment);

}
