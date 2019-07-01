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

    public Recipe readFromStream(InputStream inputStream){
        return null;
    }
}
