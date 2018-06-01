package com.example.android.bakingapp.di;

import com.example.android.bakingapp.list_detail.presenter.ListDetailPresenter;
import com.example.android.bakingapp.list_detail.presenter.ListDetailPresenterImp;

import dagger.Module;
import dagger.Provides;

@Module
public class ListDetailModule {

    @Provides
    ListDetailPresenter providesListDetailPresenter(){
        return new ListDetailPresenterImp();
    }

}
