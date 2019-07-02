package com.example.recipes.test;

import androidx.annotation.StringRes;
import androidx.test.rule.ActivityTestRule;

import com.example.recipes.R;

public class RecipeRobot extends ScreenRobot<RecipeRobot> {
    public RecipeRobot launch(ActivityTestRule rule){
        rule.launchActivity(null);
        return this;
    }

    public RecipeRobot noTitle(){
        return checkIsHidden(R.id.title);
    }

    public RecipeRobot description(@StringRes int stringId){
        return checkViewHasText(R.id.description, stringId);
    }
}
