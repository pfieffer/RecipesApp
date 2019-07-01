package com.example.recipes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recipes.R;
import com.example.recipes.data.model.RecipeStore;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recipes_recycler_view);
        RecipeStore store = new RecipeStore(this, "recipes");
        RecipeAdapter recipeAdapter = new RecipeAdapter(store);
        recyclerView.setAdapter(recipeAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
