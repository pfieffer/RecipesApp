package com.example.recipes.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesFavorites {
    private final SharedPreferences preferences;

    public SharedPreferencesFavorites(Context context) {
        this.preferences = context.getSharedPreferences("favorites.xml", Context.MODE_PRIVATE);
    }

    public boolean get(String id){
        return preferences.getBoolean(id, false);
    }

    public void put(String id, boolean favorite){
        SharedPreferences.Editor editor = preferences.edit();
        if (favorite){
            editor.putBoolean(id, true);
        } else {
            editor.remove(id);
        }
        editor.apply();
    }

    public boolean toggle(String id){
        boolean favortie = get(id);
        put(id, !favortie);
        return !favortie;
    }
}
