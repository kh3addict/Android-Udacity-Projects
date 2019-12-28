package fdffd.com.tutorials.hp.movieapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsOfMovie extends AppCompatActivity {
    static Movies [] mStringArray;
    static Context mcontext;



    DetailsOfMovie(){

    }

    DetailsOfMovie(Movies [] array1,Context context){
        mStringArray = array1;
        mcontext = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        Log.e("tag",mStringArray[0].getmPlotAnalysis());
        setContentView(R.layout.activity_details_of_movie);
        TextView tv = findViewById(R.id.tvSynopsis);
        TextView tv1 = findViewById(R.id.tvReleaseDate);
        TextView tv2 = findViewById(R.id.tvUserRating);
        TextView tv3 = findViewById(R.id.tvTitle1);
        tv.setText(mStringArray[ImageAdapter.imageIndex].getmPlotAnalysis());
        tv1.setText(mStringArray[ImageAdapter.imageIndex].getmReleaseDate());
        tv2.setText(Double.toString( mStringArray[ImageAdapter.imageIndex].getmVoteAverage()));
        ImageView iv = findViewById(R.id.imageView2);
        Picasso.with(mcontext).load(mStringArray[ImageAdapter.imageIndex].getmPoster()).resize(350,350).into(iv);
        tv3.setText(mStringArray[ImageAdapter.imageIndex].getmTitle());






    }
}
