package com.example.recipes.data.model;

import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;

public class RecipeTest {
    @Test
    public void water(){
        InputStream inputStream = RecipeTest.class.getResourceAsStream("/recipes/water.txt");
        Recipe recipe = Recipe.readFromStream(inputStream);
        assertNotNull(recipe);
    }
}