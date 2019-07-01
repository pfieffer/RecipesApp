package com.example.recipes.data.model;

import java.io.InputStream;

public class Recipe {
    public final String id;
    public final String title;
    public final String description;

    public Recipe(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public static Recipe readFromStream(InputStream inputStream){
        String id = null;
        String title = null;
        StringBuilder descBuilder = new StringBuilder();

        return new Recipe(id, title, descBuilder.toString());
    }
}
