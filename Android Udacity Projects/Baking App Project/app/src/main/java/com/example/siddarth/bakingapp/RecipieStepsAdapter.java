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

public class RecipieStepsAdapter extends RecyclerView.Adapter<RecipieStepsAdapter.RecipieStepsHolder> {


    static int mListSize;
   static  List<String> mshortSteps = new ArrayList<>();



    public interface ListItemClickListener1{
        void onListItemClick1(int clickedPosition);
    }


    public ListItemClickListener1 mOnCLickListener;

    RecipieStepsAdapter(int listSize, List<String> shortSteps,ListItemClickListener1 licm ){
        mListSize = listSize;
        mshortSteps = shortSteps;
        mOnCLickListener = licm;

    }

    @NonNull
    @Override
    public RecipieStepsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.tabletrecipiestep;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem,viewGroup,false);
        RecipieStepsHolder holder = new RecipieStepsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipieStepsHolder recipieStepsHolder, int i) {

        recipieStepsHolder.bind(i);

    }

    @Override
    public int getItemCount() {
        return mListSize;
    }

    public class RecipieStepsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        List<String> shortUrl = new ArrayList<>();

        TextView tvsteps;

        public RecipieStepsHolder(@NonNull View itemView) {
            super(itemView);
            tvsteps = itemView.findViewById(R.id.tvRecipieStep);
            itemView.setOnClickListener(this);

        }


        void bind(int index){

            tvsteps.setText(mshortSteps.get(index));

        }


        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnCLickListener.onListItemClick1(clickedPosition);
        }
    }
}
