package com.example.recipes.ui;

import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import com.example.recipes.R;

import org.junit.Rule;
import org.junit.Test;


import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;
import static androidx.test.espresso.Espresso.onView;

public class RecipeActivityTest {
    @Rule
    public ActivityTestRule<RecipeActivity> activityTestRule =
            new ActivityTestRule<>(RecipeActivity.class, true, false);

    @Test
    public void recipeNotFound(){
        activityTestRule.launchActivity(null);

        onView(withId(R.id.description))
                .check(matches(withText(R.string.recipe_not_found)));

        onView(withId(R.id.title))
                .check(matches(not(isDisplayed())));
    }

    @Test
    public void clickToFavorite(){
        Intent intent = new Intent();
        intent.putExtra(RecipeActivity.KEY_ID, "creamed_carrots");
        activityTestRule.launchActivity(intent);

        onView(withId(R.id.title))
                .check(matches(withText("Creamed Carrots")))
                .check(matches(not(isSelected()))) //see if title's star is not selected
                .perform(click()) //perform a click
                .check(matches(isSelected())); //see if title's star is selected
    }
}