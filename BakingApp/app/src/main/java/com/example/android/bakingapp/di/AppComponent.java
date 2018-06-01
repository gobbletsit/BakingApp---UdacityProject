package com.example.android.bakingapp.di;

import dagger.Component;

@Component (modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    ListComponent plus (ListModule listModule);

    ListDetailComponent plus(ListDetailModule listDetailModule);

    StepDetailComponent plus(StepDetailModule stepDetailModule);

    IngredientsListDetailComponent plus(IngredientsListDetailModule ingredientsListDetailModule);

}
