package com.example.android.bakingapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;

import com.example.android.bakingapp.list_detail.view.ListDetailActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(JUnit4.class)
public class ListDetailActivityTest {

    public static final String TEST_RECIPE_STEP = "RECIPE INTRODUCTION";

    /*@Rule
    public ActivityTestRule<ListDetailActivity> mActivityTestRule = new ActivityTestRule<>(ListDetailActivity.class);*/

    @Rule
    public IntentsTestRule<ListDetailActivity> mIntentsTestRule = new IntentsTestRule<>(ListDetailActivity.class);

    @Before
    public void stubAllExternalIntents (){
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void clickStepButton_startStepDetailActivity(){

        onView(withId(R.id.fragment_list_detail_ingredients_button)).check(matches(isDisplayed()));

        onView(withId(R.id.fragment_list_detail_ingredients_button)).perform(click());

        onView(withId(R.id.fragment_list_detail_ingredients_button)).check(matches(withText("INGREDIENTS")));

        intending(hasComponent(".IngredientsListDetailActivity")).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));

        /*

        I would do recycler view testing like this

        onView(withId(R.id.recycler_list_detail_activity)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        intending(hasComponent(".StepDetailActivity")).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));


        Probably would add a check for the intent contents with :

        intended(allOf(toPackage("com.example.android.bakingapp.detail.view"),
        hasExtra("steps_list", stepsList)

        but couldn't figure out how to mock the Parcelable array list from extras which are passed from another activity and
        which contribute to recyclerViewLoading

        */
    }
}
