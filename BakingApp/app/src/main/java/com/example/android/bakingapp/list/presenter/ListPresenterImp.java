package com.example.android.bakingapp.list.presenter;

import android.content.Context;
import android.util.Log;

import com.example.android.bakingapp.data.Ingredient;
import com.example.android.bakingapp.data.Recipe;
import com.example.android.bakingapp.data.Step;
import com.example.android.bakingapp.list.model.ListInteractor;
import com.example.android.bakingapp.list.view.ListView;
import com.example.android.bakingapp.widget.ShowWidgetRecipesService;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ListPresenterImp implements ListPresenter {

    private static final String TAG = ListPresenterImp.class.getSimpleName();

    private ListInteractor mListInteractor;

    private Disposable listDisposable;

    private ListView listView;

    public ListPresenterImp(ListInteractor listInteractor){
        this.mListInteractor = listInteractor;
    }

    @Override
    public void showRecipes() {
        listDisposable = mListInteractor.getRecipeList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onRecipeListFetchSuccess, this::onRecipeListFetchFailed);
    }

    @Override
    public void setListView(ListView listView) {
        this.listView = listView;
        showRecipes();
    }

    // context to use for widget service
    @Override
    public void onRecipeClick(Context context, ArrayList<Ingredient> ingredientList, ArrayList<Step> stepsList, String recipeName) {
        listView.onRecipeClicked(ingredientList, stepsList);
        ShowWidgetRecipesService.startShowWidgetIngredients(context, ingredientList, recipeName);
    }

    @Override
    public void destroy() {
        listView = null;
        if (listDisposable != null && !listDisposable.isDisposed()){
            listDisposable.dispose();
        }
    }

    private void onRecipeListFetchSuccess (List<Recipe> recipeList){
        if (isViewAttached()){
            listView.setUpView(recipeList);
        }
    }

    private void onRecipeListFetchFailed(Throwable e){
        Log.e(TAG, "onRecipeFetchFailed = " + e);
    }

    private boolean isViewAttached() {
        return listView != null;
    }
}
