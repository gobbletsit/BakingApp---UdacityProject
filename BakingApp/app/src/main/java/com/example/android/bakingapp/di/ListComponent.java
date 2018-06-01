package com.example.android.bakingapp.di;

import com.example.android.bakingapp.list.view.ListFragment;

import dagger.Subcomponent;

@Subcomponent (modules = {ListModule.class})
public interface ListComponent {

    void inject (ListFragment listFragment);

}
