package com.example.android.bakingapp.di;

import com.example.android.bakingapp.detail.presenter.StepDetailPresenter;
import com.example.android.bakingapp.detail.presenter.StepDetailPresenterImp;

import dagger.Module;
import dagger.Provides;

@Module
public class StepDetailModule {

    @Provides
    StepDetailPresenter providesDetailPresenter(){
        return new StepDetailPresenterImp();
    }

}
