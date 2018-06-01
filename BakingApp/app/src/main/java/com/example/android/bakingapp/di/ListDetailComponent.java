package com.example.android.bakingapp.di;

import com.example.android.bakingapp.list_detail.view.ListDetailFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {ListDetailModule.class})
public interface ListDetailComponent {

    void inject(ListDetailFragment listDetailFragment);
}
