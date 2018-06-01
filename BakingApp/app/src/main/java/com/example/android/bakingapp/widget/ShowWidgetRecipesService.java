package com.example.android.bakingapp.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Ingredient;
import com.example.android.bakingapp.data.Recipe;

import java.util.ArrayList;

public class ShowWidgetRecipesService extends IntentService {

    public static final String TAG = ShowWidgetRecipesService.class.getSimpleName();

    ArrayList<Ingredient> mIngredientsList;


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public ShowWidgetRecipesService() {
        super("ShowWidgetRecipesService");
    }


    public static void startShowWidgetIngredients(Context context, ArrayList<Ingredient> ingredients, String recipeName) {
        Intent intent = new Intent(context, ShowWidgetRecipesService.class);
        intent.putExtra("ingredients_list", ingredients);
        intent.putExtra("recipe_name", recipeName);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent != null){
            mIngredientsList = intent.getParcelableArrayListExtra("ingredients_list");
            String recipeName = intent.getStringExtra("recipe_name");
            if (mIngredientsList.size() != 0 && recipeName != null){
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, BakingWidgetProvider.class));
                BakingWidgetProvider.updateIngredientsWidget(this, appWidgetManager, appWidgetIds, mIngredientsList, recipeName);
            }
        }
    }
}
