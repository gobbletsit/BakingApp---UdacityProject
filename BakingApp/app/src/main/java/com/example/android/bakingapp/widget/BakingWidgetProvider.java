package com.example.android.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Ingredient;
import com.example.android.bakingapp.list.view.ListActivity;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidgetProvider extends AppWidgetProvider {

    public static final String TAG = BakingWidgetProvider.class.getSimpleName();

    static void updateAppIngredientsWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId, ArrayList<Ingredient> ingredients, String recipeName){

        // Construct the RemoteViews object
        RemoteViews bakingWidgetViews = new RemoteViews(context.getPackageName(), R.layout.baking_widget);
        bakingWidgetViews.setTextViewText(R.id.widget_recipe_text_view_name, recipeName + " " + "ingredients");
        bakingWidgetViews.removeAllViews(R.id.ingredients_widget_linear_layout_container);


        Intent clickIntent = new Intent(context, ListActivity.class);
        PendingIntent clickPendingIntent = PendingIntent.getActivity(context, 0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        bakingWidgetViews.setOnClickPendingIntent(R.id.baking_widget_main_linear_layout, clickPendingIntent);

        // i tried using the RemoteViewsFactory but it was very messy so i decided to use this seen from one of the collegues example code
        loadUpIngredientsViews(context, bakingWidgetViews, ingredients);

        appWidgetManager.updateAppWidget(appWidgetId, bakingWidgetViews);
    }


    public static void updateIngredientsWidget(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds, ArrayList<Ingredient> ingredients, String recipeName){
        for (int appWidgetId : appWidgetIds) {
            updateAppIngredientsWidget(context, appWidgetManager, appWidgetId, ingredients, recipeName);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context,appWidgetManager, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private static void loadUpIngredientsViews(Context context, RemoteViews mainViews, ArrayList<Ingredient> ingredients){
        for (Ingredient ingredient : ingredients) {
            RemoteViews ingredientsViews = new RemoteViews(context.getPackageName(), R.layout.widget_ingredient_item);
            String ingredientInfo = String.valueOf(ingredient.getQuantity()) + " " + ingredient.getMeasure() + " " + ingredient.getName() + "\n";
            Log.e(TAG, "Ingredient description = " + ingredientInfo);
            ingredientsViews.setTextViewText(R.id.widget_ingredients_item_text_view, ingredientInfo);
            mainViews.addView(R.id.ingredients_widget_linear_layout_container, ingredientsViews);
        }
    }
}

