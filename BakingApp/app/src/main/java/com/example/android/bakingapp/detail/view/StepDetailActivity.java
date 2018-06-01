package com.example.android.bakingapp.detail.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Step;

import java.util.ArrayList;

public class StepDetailActivity extends AppCompatActivity implements StepDetailFragment.StepCallback{

    public static final String STEP_DETAIL_FRAG = "STEP_DETAIL_FRAG";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent listDetailIntent = getIntent();
        Bundle extras = listDetailIntent.getExtras();

        FragmentManager fragmentManager = getSupportFragmentManager();
        StepDetailFragment  stepDetailFragment = (StepDetailFragment) fragmentManager.findFragmentByTag(STEP_DETAIL_FRAG);

        if (extras != null){
            Step step = extras.getParcelable("step");
            int position = extras.getInt("position");
            ArrayList<Step> stepsList = extras.getParcelableArrayList("steps_list");

            if (stepDetailFragment == null){
                stepDetailFragment = StepDetailFragment.newInstance(step, position, stepsList);
            }
        }

        // for testing purposes
        else {
            if (stepDetailFragment == null){
                stepDetailFragment = new StepDetailFragment();
            }
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.root_activity_detail, stepDetailFragment, STEP_DETAIL_FRAG);
        fragmentTransaction.commit();
    }

    // callback
    @Override
    public void onNextButtonClick(int position, Step nextStep, ArrayList<Step> steps) {
        Intent nextStepIntent = new Intent(this, StepDetailActivity.class);
        nextStepIntent.putExtra("position", position);
        nextStepIntent.putExtra("step", nextStep);
        nextStepIntent.putExtra("steps_list", steps);
        startActivity(nextStepIntent);
    }

}
