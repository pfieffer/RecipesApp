package com.example.recipes.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.recipes.R;
import com.example.recipes.data.local.SharedPreferencesFavorites;
import com.example.recipes.data.model.Recipe;
import com.example.recipes.data.model.RecipeStore;

public class RecipeActivity extends AppCompatActivity {

    public static final String KEY_ID = "id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        final TextView titleTV = findViewById(R.id.title);
        TextView descriptionTV = findViewById(R.id.description);

        RecipeStore recipeStore = new RecipeStore(this, "recipes");
        String id = getIntent().getStringExtra(KEY_ID);
        final Recipe recipe = recipeStore.getRecipe(id);

        if (recipe == null) {
            titleTV.setVisibility(View.GONE);
            descriptionTV.setText(R.string.recipe_not_found);
            return;
        }

        final SharedPreferencesFavorites sharedPreferences = new SharedPreferencesFavorites(this);
        final boolean favorite = sharedPreferences.get(recipe.id);

        titleTV.setText(recipe.title);
        descriptionTV.setText(recipe.description);
        titleTV.setSelected(favorite);

        titleTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = sharedPreferences.toggle(recipe.id);
                titleTV.setSelected(result);
            }
        });

    }
}