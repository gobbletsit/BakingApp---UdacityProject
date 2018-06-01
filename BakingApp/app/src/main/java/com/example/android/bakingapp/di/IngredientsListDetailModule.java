package com.example.android.bakingapp.di;

import com.example.android.bakingapp.ingredients_list_detail.presenter.IngredientsListDetailPresenter;
import com.example.android.bakingapp.ingredients_list_detail.presenter.IngredientsListDetailPresenterImp;

import dagger.Module;
import dagger.Provides;

@Module
public class IngredientsListDetailModule {

    @Provides
    IngredientsListDetailPresenter providesIngredientsListDetailFragment(){
        return new IngredientsListDetailPresenterImp();
    }

}
