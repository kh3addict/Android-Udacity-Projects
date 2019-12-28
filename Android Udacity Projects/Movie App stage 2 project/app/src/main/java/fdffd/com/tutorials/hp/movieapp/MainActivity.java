package fdffd.com.tutorials.hp.movieapp;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;




 public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {





      int index;
     static  GridView gridView;

     static List<Movie> movies1;
     int j = 0;
     int k = 0;
     int l = 0;
     int m = 0;
     int z = 0;
     int a = 0;
     int b = 0;
     List<String[]> youtubeTrailers = new ArrayList<>();
     List<String[] > reviews1 = new ArrayList<>();
     boolean isPopular;
     boolean isTopRated;
     boolean isFavorites;

     int counter1 = 0;
     static int[] movieIds = new int[20];


     public Intent intent;
     String[] youtubeUrl1 = new String[2000];
     static StringBuilder buffer;
     String[] results = new String[2000];
     String[] popularMovies2 = new String[20];
     String[] popularMovies3 = new String[20];
     Switch switch12;
     static String[] popularMovies = new String[100];
     StringBuffer buffer1;
     boolean finishedExecuting = false;
     public static boolean popularSelected = false;
     List<String> stringList = new ArrayList<>();
     static Movies[] movies = new Movies[20];
     Movies[] movies12 = new Movies[20];
     public static MyAppDatabase db;
     int counter = 0;


      String url1;

     Spinner spinner;
     String s1;


     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
          gridView = findViewById(R.id.gridView);





         db = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "favMovieDb").allowMainThreadQueries().fallbackToDestructiveMigration().build();

         spinner = findViewById(R.id.spinner);

         spinner.setOnItemSelectedListener(this);

         ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.OptionsArray, android.R.layout.simple_spinner_item);
         adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         spinner.setAdapter(adapter1);










         Intent intent = new Intent(MainActivity.this, DetailsOfMovie.class);


     }

     @Override
     protected void onSaveInstanceState(Bundle outState) {



         super.onSaveInstanceState(outState);


         outState.putInt("position", gridView.getFirstVisiblePosition());


     }







     @Override
     protected void onRestoreInstanceState(Bundle savedInstanceState) {
         index = savedInstanceState.getInt("position");
         gridView.setSelection(index);
         super.onRestoreInstanceState(savedInstanceState);


     }



     @Override
     public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         String item = parent.getSelectedItem().toString();









         if (item.contentEquals("Popular")) {


             try {
                 new JsonTask().execute("http://api.themoviedb.org/3/movie/popular?api_key=95f62fb6e4d4f64246181381eeeb9cdd").get();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             } catch (ExecutionException e) {
                 e.printStackTrace();
             }



             if(Movies.isFinishedExecuting()) {
                 for (int item1 : Movies.getId()) {
                     Log.e("tag", Integer.toString(item1));
                     new JsonTask().execute("http://api.themoviedb.org/3/movie/" + item1 + "/videos?api_key=95f62fb6e4d4f64246181381eeeb9cdd");

                 }
             }


         }


         if (item.contentEquals("Favorite")) {


             Toast.makeText(this, "HEllo", Toast.LENGTH_LONG);

             movies1 = db.myDao().getMovie();




                 for (int i = 0; i < popularMovies.length; i++) {
                     popularMovies[i] = null;
                 }

                 for (int j = 0; j < movies.length; j++) {
                     movies[j] = null;
                 }


                 GridView gridView = findViewById(R.id.gridView);


                 gridView.setAdapter(new ImageAdapter(
                         getApplicationContext(), popularMovies, movies));


                 




             for (Movie movie : movies1) {
                new JsonTask().execute("http://api.themoviedb.org/3/movie/" + movie.getId() + "?api_key=95f62fb6e4d4f64246181381eeeb9cdd");
             }
         }


         if (item.contentEquals("Top-rated")) {
             new JsonTask().execute("http://api.themoviedb.org/3/movie/top_rated?api_key=95f62fb6e4d4f64246181381eeeb9cdd");

         }


     }

     @Override
     public void onNothingSelected(AdapterView<?> parent) {

     }

     @Override
     public void onPointerCaptureChanged(boolean hasCapture) {

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
                 buffer = new StringBuilder();
                 String line = "";
                 while ((line = reader.readLine()) != null) {
                     buffer.append(line + "\n");

                 }


                 url1 = strings[0];


             } catch (MalformedURLException e) {
                 e.printStackTrace();
             } catch (IOException e) {
                Log.e("tag","file not found");
             }

             return buffer.toString();
         }


         @Override
         protected void onPostExecute(String s) {
             String endJpg;
             String baseUrl;
             String appendedString;
             String movieTitle;
             String releaseDate;
             String plotSynposis;
             double voteAverage;
             String[] reviews;


             super.onPostExecute(s);


             s1 = s;
             Log.e("tag", s1);

             String youtubeUrl;
             String key;
             String fullUrl;


             DetailsOfMovie dm = new DetailsOfMovie(url1);


             if (url1.contains("videos")) {
                 try {
                     JSONObject jo = new JSONObject(s1);
                     JSONArray ja = jo.getJSONArray("results");

                     for (int i = 0; i < ja.length(); j++) {
                         youtubeUrl = "https://www.youtube.com/watch?v=";
                         JSONObject jo1 = ja.getJSONObject(i);
                         key = jo1.getString("key");
                         fullUrl = youtubeUrl + key;
                         youtubeUrl1[i] = fullUrl;
                         youtubeTrailers.add(youtubeUrl1);


                     }

                 } catch (JSONException e) {
                     e.printStackTrace();
                 }




             }


             if (url1.contains("reviews")) {
                 try {
                     JSONObject jo = new JSONObject(s1);
                     JSONArray ja = jo.getJSONArray("results");
                     for (int i = 0; i < ja.length(); j++) {
                         JSONObject jo1 = ja.getJSONObject(i);
                         String content = jo1.getString("content");
                         results[i] = content;
                         reviews1.add(results);


                     }

                 } catch (JSONException e) {
                     e.printStackTrace();
                 }


             }

             if (url1.contentEquals("http://api.themoviedb.org/3/movie/popular?api_key=95f62fb6e4d4f64246181381eeeb9cdd") || url1.contentEquals("http://api.themoviedb.org/3/movie/top_rated?api_key=95f62fb6e4d4f64246181381eeeb9cdd")) {




                 try {
                     JSONObject jo = new JSONObject(s1);

                     JSONArray ja = jo.getJSONArray("results");

                     for(int i = 0 ; i < ja.length(); i++) {

                         Log.e("in loopz", "in loopzzaaa");
                         JSONObject jo1 = ja.getJSONObject(i);                          endJpg = jo1.getString("poster_path");
                         baseUrl = "http://image.tmdb.org/t/p/w185/";
                         appendedString = baseUrl + endJpg;
                         popularMovies[i] = appendedString;
                         movieTitle = jo1.getString("original_title");
                         releaseDate = jo1.getString("release_date");
                         plotSynposis = jo1.getString("overview");
                         voteAverage = jo1.getDouble("vote_average");
                         int id = jo1.getInt("id");


                         movieIds[i] = id;
                         Movies movie = new Movies(movieTitle, appendedString, plotSynposis, voteAverage, releaseDate, id, youtubeTrailers, reviews1);

                         if(url1.contentEquals("http://api.themoviedb.org/3/movie/popular?api_key=95f62fb6e4d4f64246181381eeeb9cdd")){
                             movie.setInPopular(true);
                         }

                         if(url1.contentEquals("http://api.themoviedb.org/3/movie/top_rated?api_key=95f62fb6e4d4f64246181381eeeb9cdd"))
                             movie.setInTopRated(true);
                         movies[i] = movie;



                     }




                     Log.e("tag,","In final loopz");
                     DetailsOfMovie details = new DetailsOfMovie(movies, MainActivity.this);

                     gridView.setAdapter(new ImageAdapter(MainActivity.this, popularMovies, movies));

                     DetailsOfMovie dm1 = new DetailsOfMovie(movieIds);




                 } catch (JSONException e) {
                     e.printStackTrace();
                 }
             }


             if (!url1.contains("popular") && !url1.contains("reviews") && !url1.contentEquals("http://api.themoviedb.org/3/movie/popular?api_key=95f62fb6e4d4f64246181381eeeb9cdd") &&!url1.contentEquals("http://api.themoviedb.org/3/movie/top_rated?api_key=95f62fb6e4d4f64246181381eeeb9cdd") ) {
                  isFavorites = true;
                  isPopular = false;
                  isTopRated = false;

                 if (k == 0) {
                     for (int i = 0; i < popularMovies.length; i++) {
                         popularMovies[i] = null;
                     }
                     k++;
                 }


                 if (j == 0) {
                     for (int i = 0; i < movies.length; i++) {
                         movies[i] = null;
                     }
                     j++;
                 }


                 try {

                     JSONObject jo = new JSONObject(s1);
                     endJpg = jo.getString("poster_path");
                     baseUrl = "http://image.tmdb.org/t/p/w185/";
                     appendedString = baseUrl + endJpg;
                     popularMovies[counter] = appendedString;
                     movieTitle = jo.getString("original_title");
                     releaseDate = jo.getString("release_date");
                     plotSynposis = jo.getString("overview");
                     voteAverage = jo.getDouble("vote_average");
                     int id = jo.getInt("id");


                     movieIds[counter] = id;
                     Movies movie = new Movies(movieTitle, appendedString, plotSynposis, voteAverage, releaseDate, id, youtubeTrailers, reviews1);

                     movies[counter] = movie;
                     counter++;


                     String counter1 = Integer.toString(counter);
                     String counter2 = Integer.toString(movies1.size());


                     Log.e("tag", counter1);
                     Log.e("tag", counter2);

                     if (counter == movies1.size()) {

                         counter = 0;
                         j = 0;
                         k = 0;

                          gridView = findViewById(R.id.gridView);


                         gridView.setAdapter(new ImageAdapter(
                                 MainActivity.this, popularMovies, movies));


                     }


                 } catch (JSONException e) {
                     e.printStackTrace();
                 }

             }



         }


     }
 }


























