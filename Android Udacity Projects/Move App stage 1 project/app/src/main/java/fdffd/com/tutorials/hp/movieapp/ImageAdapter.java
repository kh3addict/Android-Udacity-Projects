package fdffd.com.tutorials.hp.movieapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.GridView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by family on 6/7/18.
 */

public class ImageAdapter extends BaseAdapter {
     static int imageIndex;
    static boolean clicked = false;
    Context mContext;
    public String [] popularMoviesArray;
    public Movies [] moviesArray;

    @Override
    public int getCount() {
        return popularMoviesArray.length;
    }


    ImageAdapter(Context c, String [] popularMoviesArray1, Movies [] moviesArray1){
        popularMoviesArray = popularMoviesArray1;
        mContext = c;
        moviesArray = moviesArray1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(350,350));

        }




        else{
            imageView = (ImageView)convertView;
        }



        Picasso.with(mContext).load(popularMoviesArray[position]).resize(350,350).into(imageView);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageIndex = position;
                clicked = true;


                Intent intent = new Intent(mContext,DetailsOfMovie.class);
                v.getContext().startActivity(intent);






            }











        });



        return imageView;
    }
}
