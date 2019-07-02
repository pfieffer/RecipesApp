package com.example.recipes.ui;

import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.recipes.R;
import com.example.recipes.data.local.InMemoryFavorites;
import com.example.recipes.injection.TestRecipeApplication;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

public class RecipeActivityTest {
    private static final String CARROTS_ID = "creamed_carrots";

    @Rule
    public ActivityTestRule<RecipeActivity> activityTestRule =
            new ActivityTestRule<>(RecipeActivity.class, true, false);

    private InMemoryFavorites favorites;

    @Before
    public void clearFavorites() {
        TestRecipeApplication app = (TestRecipeApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
        favorites = (InMemoryFavorites) app.getFavorites();
        favorites.clear();
    }

    @Test
    public void recipeNotFound() {
        activityTestRule.launchActivity(null);

        onView(withId(R.id.description))
                .check(matches(withText(R.string.recipe_not_found)));

        onView(withId(R.id.title))
                .check(matches(not(isDisplayed())));
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
    public void alreadyFavorite(){
        favorites.put(CARROTS_ID, true);

        launchRecipe(CARROTS_ID);

        onView(withId(R.id.title))
                .check(matches(isSelected()));
    }

    private void launchRecipe(String id) {
        Intent intent = new Intent();
        intent.putExtra(RecipeActivity.KEY_ID, id);
        activityTestRule.launchActivity(intent);
    }
}