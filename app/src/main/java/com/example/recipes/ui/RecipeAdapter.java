package com.example.recipes.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipes.R;
import com.example.recipes.data.model.Recipe;
import com.example.recipes.data.model.RecipeStore;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    private final RecipeStore store;

    public RecipeAdapter(RecipeStore recipeStore){
        this.store = recipeStore;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        final Recipe recipe = store.recipes.get(position);

        holder.textView.setText(recipe.title);
    }

    @Override
    public int getItemCount() {
        return store.recipes.size();
    }
}
