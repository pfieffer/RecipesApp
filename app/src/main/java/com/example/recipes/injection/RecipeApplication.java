package com.example.recipes.injection;

import android.app.Application;

import com.example.recipes.data.local.Favorites;
import com.example.recipes.data.local.SharedPreferencesFavorites;

public class RecipeApplication extends Application {

    private Favorites favorites = null;

    public Favorites getFavorites(){
        if (favorites == null){
            favorites = new SharedPreferencesFavorites(this);
        }
        return favorites;
    }
}
