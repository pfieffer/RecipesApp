package com.example.recipes.test;

import android.content.Intent;

import androidx.annotation.StringRes;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.recipes.R;
import com.example.recipes.data.local.InMemoryFavorites;
import com.example.recipes.injection.TestRecipeApplication;
import com.example.recipes.ui.RecipeActivity;

public class RecipeRobot extends ScreenRobot<RecipeRobot> {

    private final InMemoryFavorites favorites;

    public RecipeRobot() {
        TestRecipeApplication app = (TestRecipeApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
        favorites = (InMemoryFavorites) app.getFavorites();
        favorites.clear();
    }

    public RecipeRobot launch(ActivityTestRule rule) {
        rule.launchActivity(null);
        return this;
    }

    public RecipeRobot launch(ActivityTestRule rule, String id) {
        Intent intent = new Intent();
        intent.putExtra(RecipeActivity.KEY_ID, id);
        rule.launchActivity(intent);
        return this;
    }

    public RecipeRobot noTitle() {
        return checkIsHidden(R.id.title);
    }

    public RecipeRobot description(@StringRes int stringId) {
        return checkViewHasText(R.id.description, stringId);
    }

    public RecipeRobot setFavorite(String id) {
        favorites.put(id, true);
        return this;
    }

    public RecipeRobot isFavorite() {
        return checkIsSelected(R.id.title);
    }
}
