package fdffd.com.tutorials.hp.movieapp;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static fdffd.com.tutorials.hp.movieapp.MainActivity.*;


public class DetailsOfMovie extends AppCompatActivity {


String content;

int k = 0;
int j = 0;
int counter = 0;

    String []results = new String[2000];
    int index2 = 0;
    ListView listView;

    String []youtubeUrl1 = new String[2000];
    String [][] multidimensionalStringArray = new String[50][50];
    Vector<String[]>newVector = new Vector<String[]>();


    boolean true1= true;
    String s1;
    String s2;
    int x;
    String url1;
   static  ListView lv;
    static int index1 = 0;
    static Movies [] mStringArray;
    List<String[]> youtubeTrailers = new ArrayList<>();
    List<String[] > reviews1 = new ArrayList<>();

     StringBuilder buffer1;
    static int [] movieIds;
    static Context mcontext;
    public static final String LIFECYCLE_CALLBACKS_TEXT_KEY= "callbacks";
    private static final String ON_SAVE_INSTANCE_STATE = "onSaveInstanceState";
    Button button1;
     String url12;







    DetailsOfMovie(){

    }


    DetailsOfMovie(String url){
        url12 = url;
    }


    DetailsOfMovie(int [] movieIds){
        this.movieIds = movieIds;
    }

    DetailsOfMovie(Movies [] array1,Context context){
        mStringArray = array1;
        mcontext = context;

    }


    int count = 0;
    int z= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);











        setContentView(R.layout.activity_details_of_movie);

        listView = findViewById(R.id.lvTrailers);
        for(int i = 0; i < DetailsOfMovie.movieIds.length; i++) {


            new JsonTask().execute("http://api.themoviedb.org/3/movie/" + movieIds[i] + "/reviews?api_key=95f62fb6e4d4f64246181381eeeb9cdd");
        }

        for(int i = 0; i < DetailsOfMovie.movieIds.length; i++) {



                new JsonTask().execute("http://api.themoviedb.org/3/movie/" + movieIds[i] + "/videos?api_key=95f62fb6e4d4f64246181381eeeb9cdd");

        }


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
        button1 = findViewById(R.id.favButton);
        List<Movie> movie = db.myDao().getMovie();





        for(int i = 0 ; i < movie.size(); i++){
                if (movie.get(i).getName().contentEquals(mStringArray[ImageAdapter.imageIndex].getmTitle()) ) {
                    button1.setText("unfavorite");
                }

            }






        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button1.getText().toString().contentEquals("favorite") && count % 2 == 0){

                    Movie movie = new Movie();
                    movie.setId(mStringArray[ImageAdapter.imageIndex].getId1());
                    movie.setName(mStringArray[ImageAdapter.imageIndex].getmTitle());
                    movie.setInFavorites(true);
                    db.myDao().addMovie(movie);
                    mStringArray[ImageAdapter.imageIndex].setInPopular(false);
                    mStringArray[ImageAdapter.imageIndex].setInTopRated(false);



                    button1.setText("unfavorite");

                    count++;

                }







                if(button1.getText().toString().contentEquals("unfavorite") && count % 2 == 0){

                    button1.setText("favorite");
                    List<Movie> movies = db.myDao().getMovie();


                    for(Movie movie: movies){
                        if(movie.getName().contentEquals(mStringArray[ImageAdapter.imageIndex].getmTitle())){
                            movie.setInFavorites(false);
                            db.myDao().deleteMovie(movie);

                        }
                    }

                    List<Movie> movies1 = db.myDao().getMovie();




                      if(movies1.size() == 0){


                          for(int i =0 ; i < MainActivity.popularMovies.length; i++){
                              MainActivity.popularMovies[i] = null;
                          }


                          for(int j = 0 ; j < MainActivity.movies.length; j++){
                              MainActivity.movies[j] = null;
                          }






                          MainActivity.gridView.setAdapter(new ImageAdapter(
                                  getApplicationContext(), MainActivity.popularMovies, MainActivity.movies));
                      }


                        if(movies1.size()> 0){
                          for (Movie movie : movies1) {
                              new JsonTask().execute("http://api.themoviedb.org/3/movie/" + movie.getId() + "?api_key=95f62fb6e4d4f64246181381eeeb9cdd");

                          }
                      }





                    count++;

                }


                count++;








            }
        });


        if(savedInstanceState != null){
            if(savedInstanceState.containsKey(LIFECYCLE_CALLBACKS_TEXT_KEY)){
                String allPreviousCallBacks = savedInstanceState.getString(LIFECYCLE_CALLBACKS_TEXT_KEY);

                button1.setText(allPreviousCallBacks);

            }
        }






    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String lifeCycle = button1.getText().toString();


        outState.putString("buttonText",lifeCycle);

    }

    public class JsonTask extends AsyncTask<String, String, String> {



        int i = 0;



        @Override
        protected String doInBackground(String... strings) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                buffer1 = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer1.append(line + "\n");

                }

                reader.close();





            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                Log.e("tag","file not found");
            }


            url1 = strings[0];

            return buffer1.toString();
        }


        @Override
        protected void onPostExecute(String s1) {
            String endJpg;
            String baseUrl;
            String appendedString;
            String movieTitle;
            String releaseDate;
            String plotSynposis;
            double voteAverage;
            String[] reviews;


            super.onPostExecute(s1);


            s2 = s1;
            Log.e("tag", s1);

            String youtubeUrl;
            String key;
            String fullUrl;


            if (url1.contains("videos")) {


                try {
                    JSONObject jo = new JSONObject(s1);
                    JSONArray ja = jo.getJSONArray("results");


                    final ArrayList<Trailer> trailers = new ArrayList<Trailer>();
                    for (int i = 0; i < ja.length(); i++) {
                        youtubeUrl = "https://www.youtube.com/watch?v=";
                        JSONObject jo1 = ja.getJSONObject(i);
                        key = jo1.getString("key");
                        fullUrl = youtubeUrl + key;
                        Trailer trailer = new Trailer("Trailer",fullUrl);
                        trailers.add(trailer);





                    }













                                  TrailerAdapter adapter = new TrailerAdapter(mcontext, trailers);
                                  listView.setAdapter(adapter);




                             listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                 @Override
                                 public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                     Intent intent = new Intent(Intent.ACTION_VIEW);
                                     intent.setData(Uri.parse(trailers.get(position).getUrl()));
                                     startActivity(intent);
                                 }
                             });





















                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }


            if (!url1.contains("popular") && !url1.contains("reviews") &&!url1.contains("videos")&& !url1.contentEquals("http://api.themoviedb.org/3/movie/popular?api_key=95f62fb6e4d4f64246181381eeeb9cdd") &&!url1.contentEquals("http://api.themoviedb.org/3/movie/top_rated?api_key=95f62fb6e4d4f64246181381eeeb9cdd") ) {


                if (k == 0) {
                    for (int i = 0; i < MainActivity.popularMovies.length; i++) {
                        MainActivity.popularMovies[i] = null;
                    }
                    k++;
                }


                if (j == 0) {
                    for (int i = 0; i < MainActivity.movies.length; i++) {
                        MainActivity.movies[i] = null;
                    }
                    j++;
                }


                gridView.setAdapter(new ImageAdapter(getApplicationContext(),MainActivity.popularMovies,MainActivity.movies));


                try {


                    Log.e("tag",url1);

                    JSONObject jo = new JSONObject(s1);
                    endJpg = jo.getString("poster_path");
                    baseUrl = "http://image.tmdb.org/t/p/w185/";
                    appendedString = baseUrl + endJpg;
                    MainActivity.popularMovies[counter] = appendedString;
                    movieTitle = jo.getString("original_title");
                    releaseDate = jo.getString("release_date");
                    plotSynposis = jo.getString("overview");
                    voteAverage = jo.getDouble("vote_average");
                    int id = jo.getInt("id");


                    movieIds[counter] = id;
                    Movies movie = new Movies(movieTitle, appendedString, plotSynposis, voteAverage, releaseDate, id, youtubeTrailers, reviews1);

                    MainActivity.movies[counter] = movie;
                    counter++;


                    String counter1 = Integer.toString(counter);
                    String counter2 = Integer.toString(movies1.size());


                    Log.e("tag", counter1);
                    Log.e("tag", counter2);

                    if (counter == MainActivity.movies1.size()) {

                        counter = 0;
                        j = 0;
                        k = 0;





                        MainActivity.gridView.setAdapter(new ImageAdapter(getApplicationContext(), MainActivity.popularMovies, MainActivity.movies));


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


            if (url1.contains("reviews")) {
                try {
                    JSONObject jo = new JSONObject(s1);
                    JSONArray ja = jo.getJSONArray("results");



                        for (int i = 0; i < ja.length(); i++) {

                            JSONObject jo1 = ja.getJSONObject(i);
                            content = jo1.getString("content");
                            if(content != null)
                            results[i] = content;







                        }


                        List<String []> listOfReviews = new ArrayList<>();

                        if(results != null)
                        listOfReviews.add(results);

                        if(content == null && index1 == ImageAdapter.imageIndex){
                            TextView tv = findViewById(R.id.tvReviews);
                            tv.append("Reviews not available");
                        }


                        if(index1 == ImageAdapter.imageIndex){
                            for(String [] item : listOfReviews){
                                for(String item1: item){
                                    TextView tv = findViewById(R.id.tvReviews);
                                    if(item1 != null)
                                    tv.append(item1);
                                }
                            }
                        }


                        if(index1 == 20){
                            index1 = 0;
                        }


                        index1++;








































                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }}}





}

