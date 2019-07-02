package com.example.recipes.test;


import android.app.Application;
import android.content.Context;

import androidx.test.runner.AndroidJUnitRunner;

import com.example.recipes.injection.TestRecipeApplication;

public class CustomTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return super.newApplication(cl, TestRecipeApplication.class.getName(), context);
    }
}
