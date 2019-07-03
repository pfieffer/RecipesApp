package com.example.recipes.ui;

import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import com.example.recipes.R;
import com.example.recipes.test.RecipeRobot;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

public class RecipeActivityTest {
    private static final String CARROTS_ID = "creamed_carrots";

    @Rule
    public ActivityTestRule<RecipeActivity> activityTestRule =
            new ActivityTestRule<>(RecipeActivity.class, true, false);

    @Test
    public void recipeNotFound() {
        new RecipeRobot()
                .launch(activityTestRule)
                .noTitle()
                .description(R.string.recipe_not_found);
    }

    @Test
    public void clickToFavorite() {
        launchRecipe(CARROTS_ID);

        onView(withId(R.id.title))
                .check(matches(withText("Creamed Carrots")))
                .check(matches(not(isSelected()))) //see if title's star is not selected
                .perform(click()) //perform a click
                .check(matches(isSelected())); //see if title's star is selected
    }

    @Test
    public void alreadyFavorite() {
        new RecipeRobot().setFavorite(CARROTS_ID)
                .launch(activityTestRule, CARROTS_ID)
                .isFavorite();
    }

    private void launchRecipe(String id) {
        Intent intent = new Intent();
        intent.putExtra(RecipeActivity.KEY_ID, id);
        activityTestRule.launchActivity(intent);
    }
}