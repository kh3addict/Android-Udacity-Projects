package com.example.siddarth.bakingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecipieIngredientsListTablet extends RecyclerView.Adapter<RecipieIngredientsListTablet.RecipieIngredientsHolder> {

    static List<String> ingredients1 = new ArrayList<>();
    static double [] quantity1 = new double[50];
    static List<String> measure1 = new ArrayList<>();

    int mNumberOfItems;

    RecipieIngredientsListTablet(int numberOfItems, List<String> ingredients, List<String> measure, double[] quantity){
        mNumberOfItems = numberOfItems;
        ingredients1 = ingredients;
        measure1= measure;
        quantity1 = quantity;
    }



    @NonNull
    @Override
    public RecipieIngredientsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.tabletrecipieingredients;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, viewGroup,false);
        RecipieIngredientsHolder holder = new RecipieIngredientsHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipieIngredientsHolder recipieIngredientsHolder, int i) {
       recipieIngredientsHolder.bind(i);
    }

    @Override
    public int getItemCount() {


        return mNumberOfItems;

    }

    class RecipieIngredientsHolder extends RecyclerView.ViewHolder{
        TextView tvIngredients;
        TextView tvQuantity;
        TextView tvMeasure;

        public RecipieIngredientsHolder(@NonNull  View itemView){
            super(itemView);
            tvIngredients = itemView.findViewById(R.id.tvIngredient);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvMeasure = itemView.findViewById(R.id.tvMeasure);




        }


        void bind(int index){

            tvIngredients.setText(ingredients1.get(index));
            tvQuantity.setText(Double.toString(quantity1[index]));
            tvMeasure.setText(measure1.get(index));

        }

    }
}
