package com.example.signuploginfirebase.Listeners;


import com.example.signuploginfirebase.Models.RandomRecipeApiResponse;

public interface RandomRecipeResponseListener {

        void didFetch(RandomRecipeApiResponse response, String message);
        void didError(String message);
}
