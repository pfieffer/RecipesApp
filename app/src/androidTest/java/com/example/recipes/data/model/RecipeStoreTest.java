package com.example.recipes.data.model;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeStoreTest {

    @Test
    public void nullDirectory() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        RecipeStore store = new RecipeStore(context, null);
        assertNotNull(store);
        assertNotNull(store.recipes);
        assertEquals(0, store.recipes.size());
    }

    @Test
    public void count() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        RecipeStore store = new RecipeStore(context, "recipes"); //folder name
        assertNotNull(store);
        assertNotNull(store.recipes);
        assertEquals(4, store.recipes.size());
    }

    @Test
    public void getChocolatePudding() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        RecipeStore store = new RecipeStore(context, "recipes"); //folder name
        Recipe recipe = store.getRecipe("chocolate_pudding");
        assertNotNull(recipe);
        assertEquals("chocolate_pudding", recipe.id);
        assertEquals("Chocolate Pudding", recipe.title);
        //can do the same for Description
    }
}