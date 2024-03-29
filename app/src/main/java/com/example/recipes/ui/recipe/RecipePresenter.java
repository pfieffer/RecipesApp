package com.example.recipes.ui.recipe;

import com.example.recipes.data.local.Favorites;
import com.example.recipes.data.model.Recipe;
import com.example.recipes.data.model.RecipeStore;

public class RecipePresenter implements RecipeContract.Listener{
    private final RecipeStore store;
    private final RecipeContract.View view;
    private final Favorites favorites;
    private Recipe recipe;

    public RecipePresenter(RecipeStore store, RecipeContract.View view, Favorites favorites) {
        this.store = store;
        this.view = view;
        this.favorites = favorites;
    }

    public void loadRecipe(String id){
        recipe = store.getRecipe(id);
        if (recipe == null){
            view.showRecipeNotFoundError();
        } else {
            view.setTitle(recipe.title);
            view.setDescription(recipe.description);
            view.setFavorite(favorites.get(recipe.id));
        }
    }

    public void toggleFavorite() {
        boolean result = favorites.toggle(recipe.id);
        view.setFavorite(result);
    }
}
