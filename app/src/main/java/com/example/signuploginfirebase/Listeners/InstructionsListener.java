package com.example.signuploginfirebase.Listeners;

import com.example.signuploginfirebase.Models.InstructionsResponse;

import java.util.List;

public interface InstructionsListener {
    void didFetch(List<InstructionsResponse> response, String message );
    void didError(String message);
}
