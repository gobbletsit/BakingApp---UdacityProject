package com.example.android.bakingapp.list_detail.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Ingredient;
import com.example.android.bakingapp.data.Step;
import com.example.android.bakingapp.detail.view.StepDetailActivity;
import com.example.android.bakingapp.detail.view.StepDetailFragment;
import com.example.android.bakingapp.ingredients_list_detail.view.IngredientsListDetailActivity;
import com.example.android.bakingapp.ingredients_list_detail.view.IngredientsListDetailFragment;

import java.util.ArrayList;

public class ListDetailActivity extends AppCompatActivity implements ListDetailFragment.Callback, StepDetailFragment.StepCallback{

    public static final String LIST_DETAIL_FRAG = "LIST_DETAIL_FRAG";

    public static final String STEP_DETAIL_FRAG = "STEP_DETAIL_FRAG";

    public static final String INGREDIENTS_LIST_DETAIL_FRAG = "INGREDIENTS_LIST_DETAIL_FRAG";

    private boolean mTwoPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);

        Intent listIntent = getIntent();
        Bundle extras = listIntent.getExtras();

        FragmentManager fragmentManager = getSupportFragmentManager();
        ListDetailFragment listDetailFragment = (ListDetailFragment) fragmentManager.findFragmentByTag(LIST_DETAIL_FRAG);

        if (extras != null){

            ArrayList<Ingredient> ingredientList = extras.getParcelableArrayList("ingredient_list");
            ArrayList<Step> stepsList = extras.getParcelableArrayList("steps_list");

            if (listDetailFragment == null){
                listDetailFragment = ListDetailFragment.newInstance(ingredientList, stepsList);
            }

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.root_activity_list_detail, listDetailFragment, LIST_DETAIL_FRAG);
            fragmentTransaction.commit();

            if (findViewById(R.id.list_detail_dual_pane_linear_layout) != null){
                mTwoPane = true;
            } else {
                mTwoPane = false;
            }
        }
        // for testing purposes
        else {
            if (listDetailFragment == null){
                listDetailFragment = new ListDetailFragment();
            }

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.root_activity_list_detail, listDetailFragment, LIST_DETAIL_FRAG);
            fragmentTransaction.commit();

        }
    }

    @Override
    public void onStepClick(Step step, int position, ArrayList<Step> stepArrayList) {
        if (mTwoPane){
            loadStepDetailsFragment(step, position, stepArrayList);
        } else {
            startDetailActivity(step, position, stepArrayList);
        }
    }

    @Override
    public void onIngredientsClick(ArrayList<Ingredient> ingredientsList) {
        if (mTwoPane){
            loadIngredientListFragment(ingredientsList);
        } else {
            startIngredientsListActivity(ingredientsList);
        }
    }

    // for mTwoPane
    private void loadStepDetailsFragment(Step step, int position, ArrayList<Step> stepArrayList){
        StepDetailFragment stepDetailFragment = StepDetailFragment.newInstance(step, position, stepArrayList);
        getSupportFragmentManager().beginTransaction().replace(R.id.root_activity_detail, stepDetailFragment, STEP_DETAIL_FRAG)
        .commit();
    }

    // for mTwoPane
    private void startDetailActivity(Step step, int position, ArrayList<Step> stepArrayList){
        Intent intent = new Intent(this, StepDetailActivity.class);
        intent.putExtra("step", step);
        intent.putExtra("position", position);
        intent.putExtra("steps_list", stepArrayList);
        startActivity(intent);
    }

    // for mTwoPane
    private void loadIngredientListFragment(ArrayList<Ingredient> ingredientArrayList){
        IngredientsListDetailFragment ingredientsListDetailFragment = IngredientsListDetailFragment.newInstance(ingredientArrayList);
        getSupportFragmentManager().beginTransaction().replace(R.id.root_activity_detail, ingredientsListDetailFragment, INGREDIENTS_LIST_DETAIL_FRAG)
                .commit();
    }

    // for mTwoPane
    private void startIngredientsListActivity(ArrayList<Ingredient> ingredientArrayList){
        Intent ingredientsDetailIntent = new Intent(this, IngredientsListDetailActivity.class);
        ingredientsDetailIntent.putExtra("ingredient_list", ingredientArrayList);
        startActivity(ingredientsDetailIntent);
    }

    // for mTwoPane
    @Override
    public void onNextButtonClick(int position, Step nextStep, ArrayList<Step> steps) {
        if (mTwoPane){
            loadNextStepFragment(nextStep, position, steps);
        } else {
            // do nothing
        }
    }

    // for mTwoPane
    private void loadNextStepFragment(Step step, int position, ArrayList<Step> stepArrayList){
        StepDetailFragment nextStepDetailFragment = StepDetailFragment.newInstance(step, position, stepArrayList);
        getSupportFragmentManager().beginTransaction().replace(R.id.root_activity_detail, nextStepDetailFragment, STEP_DETAIL_FRAG)
                .commit();
    }
}
