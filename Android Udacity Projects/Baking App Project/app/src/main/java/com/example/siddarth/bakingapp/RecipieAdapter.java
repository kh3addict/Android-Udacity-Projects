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

public class RecipieAdapter extends RecyclerView.Adapter<RecipieAdapter.NumberViewHolder> {



    public interface ListItemClickListener{
        void onListItemClick(int clickedPosition);
    }

    public ListItemClickListener mOnCLickListener;


    static List<String> recipieList = new ArrayList<>();

    Context mcontext;


    public RecipieAdapter(List<String> mrecipieList, Context context){
       recipieList = mrecipieList;
       mcontext = context;



    }



    private int mNumberOfItems;




    public RecipieAdapter(int numberOfItems, ListItemClickListener licl){
        mNumberOfItems = numberOfItems;
        mOnCLickListener = licl;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.recipielist;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem,viewGroup,false);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder numberViewHolder, int i) {
        numberViewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        return mNumberOfItems;
    }

    class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvRecipies;




        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRecipies = itemView.findViewById(R.id.tvRecipies);
            itemView.setOnClickListener(this);

        }


        void bind(int listIndex){


            tvRecipies.setText(recipieList.get(listIndex));

        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnCLickListener.onListItemClick(clickedPosition);
        }
    }
}
