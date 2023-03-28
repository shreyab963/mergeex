package com.example.signuploginfirebase.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.signuploginfirebase.Listeners.RecipeClickListener;
import com.example.signuploginfirebase.Models.Recipe;
import com.example.signuploginfirebase.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder>{

    Context context;
    List<Recipe> list;
    RecipeClickListener listener;

    public RandomRecipeAdapter(List<Recipe> list,Context context, RecipeClickListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipe,parent,false ));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        holder.textView_title.setText(list.get(position).title);
        holder.textView_title.setSelected(true);
        holder.textView_ready_time.setText(list.get(position).readyInMinutes+" Min");
        holder.textView_calories.setText(String.valueOf(list.get(position).healthScore)+" Calories");
        holder.textView_serving_cost.setText(String.valueOf(list.get(position).servings)+" Servings");
        Picasso.get().load(list.get(position) .image).into(holder.imageView_food);

        holder.random_list_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setRecipes(List<Recipe> recipes) {
        this.list = recipes;
        notifyDataSetChanged();
    }



}
class RandomRecipeViewHolder extends RecyclerView.ViewHolder {

    TextView textView_title, textView_ready_time, textView_serving_cost, textView_calories, textView_tags_frees, textView_tags_includes;
    ImageView imageView_food;
    CardView random_list_container ;

    public RandomRecipeViewHolder(@NonNull View itemView) {
        super(itemView);

        textView_title = itemView.findViewById(R.id.textView_title);
        textView_ready_time = itemView.findViewById(R.id.textView_ready_time);
        imageView_food = itemView.findViewById(R.id.imageView_food);
        textView_calories = itemView.findViewById(R.id.textView_calories);
        textView_serving_cost = itemView.findViewById(R.id.textView_serving_cost);
        textView_tags_includes = itemView.findViewById(R.id.textView_tags_includes);
        textView_tags_frees = itemView.findViewById(R.id.textView_tags_frees);
        random_list_container = itemView.findViewById(R.id.random_list_container);
    }
}
