package com.example.signuploginfirebase.Listeners;

import com.example.signuploginfirebase.Models.RecipeDetailsResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailsResponse response, String message);
    void didError(String message);
}
