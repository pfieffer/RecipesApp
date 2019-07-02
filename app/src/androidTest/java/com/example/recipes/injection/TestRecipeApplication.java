package com.example.recipes.injection;

import com.example.recipes.data.local.Favorites;
import com.example.recipes.data.local.InMemoryFavorites;

public class TestRecipeApplication extends RecipeApplication {

    private final Favorites favorites = new InMemoryFavorites();

    @Override
    public Favorites getFavorites() {
        return favorites;
    }
}
