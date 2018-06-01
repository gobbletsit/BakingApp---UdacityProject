package com.example.android.bakingapp.di;

import com.example.android.bakingapp.detail.view.StepDetailFragment;

import dagger.Subcomponent;

@Subcomponent (modules = {StepDetailModule.class})
public interface StepDetailComponent {

    void inject(StepDetailFragment stepDetailFragment);
}
