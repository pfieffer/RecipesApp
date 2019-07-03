package com.example.recipes.ui;

import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import com.example.recipes.R;
import com.example.recipes.test.RecipeRobot;

import org.junit.Rule;
import org.junit.Test;

public class RecipeActivityTest {
    private static final String CARROTS_ID = "creamed_carrots";
    private static final String CARROTS_TITLE = "Creamed Carrots";

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
        new RecipeRobot()
                .launch(activityTestRule, CARROTS_ID)
                .title(CARROTS_TITLE) //check if the view has the string
                .checkIsNotSelected() //see if title's star is not selected
                .performClick(R.id.title) //perform a click
                .checkIsSelected(R.id.title); //see if title's star is selected
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