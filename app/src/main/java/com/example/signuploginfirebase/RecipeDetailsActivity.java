package com.example.signuploginfirebase;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.signuploginfirebase.Adapters.IngredientsAdapter;
import com.example.signuploginfirebase.Adapters.InstructionsAdapter;
import com.example.signuploginfirebase.Listeners.InstructionsListener;
import com.example.signuploginfirebase.Listeners.RecipeDetailsListener;
import com.example.signuploginfirebase.Models.InstructionsResponse;
import com.example.signuploginfirebase.Models.RecipeDetailsResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity {
    int id;
    TextView textView_meal_name, textView_meal_source;// textView_meal_servings, textView_meal_ready, textView_meal_price;
    TextView textView_meal_summary;
    ImageView imageView_meal_image;
    RecyclerView recycler_meal_ingredients,recycler_meal_instructions;// recycler_meal_similar, recycler_meal_instructions;
   // Button button_nutrition;
    //ScrollView scrollView;
    RequestManager manager;
    ProgressBar progressBar;
    IngredientsAdapter ingredientsAdapter;
    InstructionsAdapter instructionsAdapter;
    Button button_nutrition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        
        findViews();

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        manager = new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListener,id);

      ///  progressBar = (ProgressBar)findViewById(R.id.loader);
       // Sprite doubleBounce = new Wave();
       // progressBar.setIndeterminateDrawable(doubleBounce);
//        dialog.show();
  //      manager.GetSimilarRecipe(similarRecipeListener, id);
        manager.GetInstructions(instructionsListener, id);
        button_nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //https://api.spoonacular.com/recipes/1082038/nutritionWidget.png
//                https://api.spoonacular.com/recipes/641166/nutritionLabel.png

                final Dialog nutritionDialog = new Dialog(RecipeDetailsActivity.this, android.R.style.Theme_Light);
                nutritionDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                nutritionDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                nutritionDialog.setContentView(R.layout.nutrition_dialog);
                ImageView imageView = (ImageView) nutritionDialog.findViewById(R.id.imageView_nutrition);
                Picasso.get().load("https://api.spoonacular.com/recipes/"+id+"/nutritionLabel.png?apiKey="+getString(R.string.api_key)).into(imageView);
                nutritionDialog.show();
            }
        });


    }

    private void findViews() {
        textView_meal_name = findViewById(R.id.textView_meal_name);
        textView_meal_source = findViewById(R.id.textView_meal_source);
        textView_meal_summary= findViewById(R.id.textView_meal_summary);
        imageView_meal_image= findViewById(R.id.imageView_meal_image);
        recycler_meal_ingredients= findViewById(R.id.recycler_meal_ingredients);
        recycler_meal_instructions=findViewById(R.id.recycler_meal_instructions);
        button_nutrition = findViewById(R.id.button_nutrition);
    }
    private final RecipeDetailsListener recipeDetailsListener = new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            textView_meal_name.setText(response.title);
            textView_meal_source.setText(response.sourceName);
            textView_meal_summary.setText(response.summary);
            Picasso.get().load(response.image).into(imageView_meal_image);

            recycler_meal_ingredients.setHasFixedSize(true);
            recycler_meal_ingredients.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL,false));

            ingredientsAdapter = new IngredientsAdapter(RecipeDetailsActivity.this,response.extendedIngredients);

            recycler_meal_ingredients.setAdapter(ingredientsAdapter);

        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();

        }
    };

    private final InstructionsListener instructionsListener = new InstructionsListener() {
        @Override
        public void didFetch(List<InstructionsResponse> response, String message) {
            recycler_meal_instructions.setHasFixedSize(true);
            recycler_meal_instructions.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.VERTICAL,false));
            instructionsAdapter = new InstructionsAdapter(RecipeDetailsActivity.this, response);
            recycler_meal_instructions.setAdapter(instructionsAdapter);

        }

        @Override
        public void didError(String message) {

        }
    };

}