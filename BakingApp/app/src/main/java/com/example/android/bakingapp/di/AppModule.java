package com.example.android.bakingapp.di;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context context;

    public AppModule(Application application){
        context = application;
    }

    @Provides
    public Context providesContext(){
        return context;
    }

    @Provides
    public Resources provideResources(Context context){
        return context.getResources();
    }
}
