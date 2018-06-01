package com.example.android.bakingapp;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.bakingapp.di.AppComponent;
import com.example.android.bakingapp.di.AppModule;
import com.example.android.bakingapp.di.DaggerAppComponent;
import com.example.android.bakingapp.di.IngredientsListDetailComponent;
import com.example.android.bakingapp.di.IngredientsListDetailModule;
import com.example.android.bakingapp.di.ListComponent;
import com.example.android.bakingapp.di.ListDetailComponent;
import com.example.android.bakingapp.di.ListDetailModule;
import com.example.android.bakingapp.di.ListModule;
import com.example.android.bakingapp.di.NetworkModule;
import com.example.android.bakingapp.di.StepDetailComponent;
import com.example.android.bakingapp.di.StepDetailModule;

public class BakingApplication extends Application {

    private AppComponent appComponent;

    private ListComponent listComponent;

    private ListDetailComponent listDetailComponent;

    private StepDetailComponent stepDetailComponent;

    private IngredientsListDetailComponent ingredientsListDetailComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = createAppComponent();
    }

    public AppComponent createAppComponent(){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule()).build();
    }

    public ListComponent createListComponent(){
        listComponent = appComponent.plus(new ListModule());
        return listComponent;
    }

    public ListDetailComponent createListDetailComponent(){
        listDetailComponent = appComponent.plus(new ListDetailModule());
        return listDetailComponent;
    }

    public StepDetailComponent createStepDetailComponent(){
        stepDetailComponent = appComponent.plus(new StepDetailModule());
        return stepDetailComponent;
    }

    public IngredientsListDetailComponent createIngredientsListComponent(){
        ingredientsListDetailComponent = appComponent.plus(new IngredientsListDetailModule());
        return ingredientsListDetailComponent;
    }

    public void releaseListComponent(){listComponent = null;}

    public void releaseListDetailComponent(){listDetailComponent = null;}

    public void releaseStepDetailComponent(){stepDetailComponent = null;}

    public void releaseIngredientsListDetailComponent(){ingredientsListDetailComponent = null;}
}
