package com.example.recipes.ui.recipe;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.recipes.R;
import com.example.recipes.data.local.Favorites;
import com.example.recipes.data.model.Recipe;
import com.example.recipes.data.model.RecipeStore;
import com.example.recipes.injection.RecipeApplication;

public class RecipeActivity extends AppCompatActivity implements RecipeContract.View {

    public static final String KEY_ID = "id";

    private TextView titleTV;
    private TextView descriptionTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //Step 1: Setup UI
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        titleTV = findViewById(R.id.title);
        descriptionTV = findViewById(R.id.description);

        //Step 2: Load recipe from store
        RecipeStore recipeStore = new RecipeStore(this, "recipes");
        String id = getIntent().getStringExtra(KEY_ID);
        RecipeApplication recipeApplication = (RecipeApplication) getApplication();
        final Favorites favorites = recipeApplication.getFavorites();
        final RecipePresenter presenter = new RecipePresenter(recipeStore, this, favorites);
        presenter.loadRecipe(id);

        //Step 3: If Recipe is null, show error => This is now done in the presenter

        //Step 4: If Recipe is not null, show recipe => This is now done in the presenter

        //Step 5: When the title is clicked, toggle favorites
        titleTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.toggleFavorite();

            }
        });

    }

    @Override
    public void showRecipeNotFoundError() {
        titleTV.setVisibility(View.GONE);
        descriptionTV.setText(R.string.recipe_not_found);
    }

    @Override
    public void setTitle(String title) {
        titleTV.setText(title);
    }

    @Override
    public void setDescription(String description) {
        descriptionTV.setText(description);
    }

    @Override
    public void setFavorite(boolean favorite) {
        titleTV.setSelected(favorite);
    }
}