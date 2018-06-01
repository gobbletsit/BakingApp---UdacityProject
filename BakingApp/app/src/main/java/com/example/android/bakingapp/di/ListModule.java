package com.example.android.bakingapp.di;

import com.example.android.bakingapp.list.model.ListInteractor;
import com.example.android.bakingapp.list.model.ListInteractorImp;
import com.example.android.bakingapp.list.presenter.ListPresenter;
import com.example.android.bakingapp.list.presenter.ListPresenterImp;
import com.example.android.bakingapp.network.BakingNetworkService;

import dagger.Module;
import dagger.Provides;

@Module
public class ListModule {

    @Provides
    ListInteractor providesListInteractor(BakingNetworkService bakingNetworkService){
        return new ListInteractorImp(bakingNetworkService);
    }

    @Provides
    ListPresenter providesListPresenter(ListInteractor listInteractor){
        return new ListPresenterImp(listInteractor);
    }

}
