package com.example.android.bakingapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.view.View;

import com.example.android.bakingapp.detail.view.StepDetailActivity;

import org.hamcrest.Matcher;
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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNot.not;

@RunWith(JUnit4.class)
public class StepDetailActivityTestRule {

    @Rule
    public IntentsTestRule<StepDetailActivity> mIntentsTestRule = new IntentsTestRule<>(StepDetailActivity.class);

    @Before
    public void stubAllExternalIntents(){
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void clickNextStepButton(){
        onView(withId(R.id.step_detail_next_button)).check(matches(isDisplayed())).perform(click());
        intending(hasComponent(".StepDetailActivity")).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

}
