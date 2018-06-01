package com.example.android.bakingapp.network;

import com.example.android.bakingapp.data.Recipe;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface BakingNetworkService {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Observable<List<Recipe>> getRecipeList();

}
