package com.example.signuploginfirebase.Models;

import java.util.List;

public class RandomRecipeApiResponse {
    public List<Recipe> recipes;

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

}
