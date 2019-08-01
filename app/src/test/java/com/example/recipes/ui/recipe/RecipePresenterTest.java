package com.example.recipes.ui.recipe;

import com.example.recipes.data.local.Favorites;
import com.example.recipes.data.model.Recipe;
import com.example.recipes.data.model.RecipeStore;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.InputStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RecipePresenterTest {
    private RecipeStore store;
    private Favorites favorites;
    private RecipeContract.View view;
    private RecipePresenter presenter;

    @Before
    public void setUp() {
        //Mocking the dependencies for the Presenter
        store = Mockito.mock(RecipeStore.class);
        favorites = Mockito.mock(Favorites.class);
        view = Mockito.mock(RecipeContract.View.class);

        //actual presenter from the mocks
        presenter = new RecipePresenter(store, view, favorites);
    }

    @Test
    public void recipeNotFound() {
        Mockito.when(store.getRecipe(Mockito.anyString())).thenReturn(null);
        presenter.loadRecipe("no_such_id");
        //confirm that showRecipeNotFoundError() method is called on the view
        Mockito.verify(view, Mockito.times(1)).showRecipeNotFoundError();
    }

    @Test(expected = NullPointerException.class)
    public void toggleWithoutLoad() {
        //toggle favorite without loading a recipe
        presenter.toggleFavorite();
    }

    @Test
    public void loadWaterAndFavorite() {
        //load water.txt from asset and favorite it
        InputStream inputStream = RecipePresenterTest.class.getResourceAsStream("/recipes/water.txt");
        Recipe recipe = Recipe.readFromStream(inputStream);
        Mockito.when(store.getRecipe(Mockito.anyString())).thenReturn(recipe);
        Mockito.when(favorites.toggle(Mockito.anyString())).thenReturn(true); //change from default false tp true

        presenter.loadRecipe("water");
        presenter.toggleFavorite();

        //captor : a person who catches
        ArgumentCaptor<Boolean> captor = ArgumentCaptor.forClass(Boolean.class);
        Mockito.verify(view, Mockito.times(2)).setFavorite(captor.capture());
        assertFalse(captor.getAllValues().get(0));
        assertTrue(captor.getAllValues().get(1));
    }

}