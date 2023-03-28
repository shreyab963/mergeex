package com.example.signuploginfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.signuploginfirebase.Adapters.RandomRecipeAdapter;
import com.example.signuploginfirebase.Listeners.RandomRecipeResponseListener;
import com.example.signuploginfirebase.Listeners.RecipeClickListener;
import com.example.signuploginfirebase.Models.RandomRecipeApiResponse;

import java.util.ArrayList;
import java.util.List;

public class CookbookFragment extends Fragment {
    ProgressBar dialog;
    RequestManager manager;
    RandomRecipeAdapter randomRecipeAdapter;
    RecyclerView recyclerView;
    Spinner spinner;
    List<String> tags = new ArrayList<>();
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cookbook, container, false);

       // dialog = view.findViewById(R.id.progress_bar);
        recyclerView = view.findViewById(R.id.recycler_random);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        randomRecipeAdapter = new RandomRecipeAdapter(new ArrayList<>(), getActivity(),recipeClickListener);
        recyclerView.setAdapter(randomRecipeAdapter);

        searchView = view.findViewById(R.id.searchView_home);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tags.clear();
                tags.add(query);
                manager.getRandomRecipes(randomRecipeResponseListener, tags);
               // Toast.makeText(getActivity(), "Will be added soon!", Toast.LENGTH_SHORT).show();
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


                spinner = view.findViewById(R.id.spinner_tags);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(
                getActivity(),
                R.array.tags,
                R.layout.spinner_text
        );
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(spinnerSelectedListener);

        manager = new RequestManager(getActivity());
       // manager.getRandomRecipes(randomRecipeResponseListener);
       // dialog.setVisibility(View.VISIBLE);

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            float x, y;
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x = motionEvent.getX();
                        y = motionEvent.getY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        float dx = motionEvent.getX() - x;
                        float dy = motionEvent.getY() - y;

                        if (Math.abs(dx) > Math.abs(dy)) {
                            recyclerView.smoothScrollBy((int) dx, 0);
                        }

                        x = motionEvent.getX();
                        y = motionEvent.getY();
                        break;
                }
                return false;
            }

        });

        return view;
    }

    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            randomRecipeAdapter.setRecipes(response.recipes);
           // dialog.setVisibility(View.GONE);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
           // dialog.setVisibility(View.GONE);
        }
    };

    private final AdapterView.OnItemSelectedListener spinnerSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            tags.clear();
            tags.add(adapterView.getSelectedItem().toString());
            manager.getRandomRecipes(randomRecipeResponseListener, tags);
            //recyclerView.setVisibility(View.GONE);
            //progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(getActivity(),RecipeDetailsActivity.class)
                    .putExtra("id",id));

        }
    };


}