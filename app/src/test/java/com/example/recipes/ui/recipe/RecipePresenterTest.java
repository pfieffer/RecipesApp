package com.example.recipes.ui.recipe;

import com.example.recipes.data.local.Favorites;
import com.example.recipes.data.model.RecipeStore;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class RecipePresenterTest {
    private RecipeStore store;
    private Favorites favorites;
    private RecipeContract.View view;
    private RecipePresenter presenter;

    @Before
    public void setUp(){
        //Mocking the dependencies for the Presenter
        store = Mockito.mock(RecipeStore.class);
        favorites = Mockito.mock(Favorites.class);
        view = Mockito.mock(RecipeContract.View.class);

        //actual presenter from the mocks
        presenter = new RecipePresenter(store, view, favorites);
    }

    @Test
    public void recipeNotFound(){
        Mockito.when(store.getRecipe(Mockito.anyString())).thenReturn(null);
        presenter.loadRecipe("no_such_id");
        //confirm that showRecipeNotFoundError() method is called on the view
        Mockito.verify(view, Mockito.times(1)).showRecipeNotFoundError();
    }

}