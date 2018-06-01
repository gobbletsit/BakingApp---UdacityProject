package com.example.android.bakingapp.list.model;

import com.example.android.bakingapp.data.Recipe;
import com.example.android.bakingapp.network.BakingNetworkService;

import java.util.List;

import io.reactivex.Observable;

public class ListInteractorImp implements ListInteractor {

    private BakingNetworkService bakingNetworkService;

    public ListInteractorImp(BakingNetworkService bakingNetworkService) {
        this.bakingNetworkService = bakingNetworkService;
    }

    @Override
    public Observable<List<Recipe>> getRecipeList() {
        return bakingNetworkService.getRecipeList();
    }
}
