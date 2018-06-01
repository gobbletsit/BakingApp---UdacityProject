package com.example.android.bakingapp.ingredients_list_detail.view;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Ingredient;

import java.util.ArrayList;

public class IngredientsListDetailActivity extends AppCompatActivity {

    // ovo sve stavi pod utils ili neku takvu klasu
    public static final String INGREDIENTS_LIST_DETAIL_FRAG = "INGREDIENTS_LIST_DETAIL_FRAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null && extras.containsKey("ingredient_list")){
            ArrayList<Ingredient> ingredientsList = extras.getParcelableArrayList("ingredient_list");

            FragmentManager fragmentManager = getSupportFragmentManager();
            IngredientsListDetailFragment ingredientsListDetailFragment = (IngredientsListDetailFragment) fragmentManager.findFragmentByTag(INGREDIENTS_LIST_DETAIL_FRAG);

            if (ingredientsListDetailFragment == null){
                ingredientsListDetailFragment = IngredientsListDetailFragment.newInstance(ingredientsList);
            }

            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.root_activity_detail, ingredientsListDetailFragment, INGREDIENTS_LIST_DETAIL_FRAG);
            transaction.commit();

        }
    }
}
