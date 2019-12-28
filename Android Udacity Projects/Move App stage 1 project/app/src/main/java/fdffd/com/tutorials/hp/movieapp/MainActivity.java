package fdffd.com.tutorials.hp.movieapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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

class Ax{

}

public Intent intent;
static StringBuilder buffer;
JSONObject jo;
Switch switch12;
 StringBuffer buffer1;
 public static boolean popularSelected = false;
 List<String> stringList = new ArrayList<>();
 static Movies [] movies = new Movies[20];
 JSONArray ja;
 JSONObject jo1;
 String movieName;
 String appendedString;
 String movieTitle;
 String plotSynposis;
 double voteAverage;
 String releaseDate;


 Spinner spinner;
 String s1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         switch12 = findViewById(R.id.switch1);

       /* spinner = findViewById(R.id.sorting);

        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.OptionsArray,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);*/


       if(switch12.getText().toString().contentEquals("Popular")){
           new JsonTask().execute("http://api.themoviedb.org/3/movie/popular?api_key=95f62fb6e4d4f64246181381eeeb9cdd");

       }




switch12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked && switch12.getText().toString().contentEquals("Popular")){

            Log.e("tag","clicked on the button");

            switch12.setText("Top-rated");

        }

        if(!isChecked && switch12.getText().toString().contentEquals("Top-rated")){
            switch12.setText("Popular");
        }


        if(switch12.getText().toString().contentEquals("Popular")){
            new JsonTask().execute("http://api.themoviedb.org/3/movie/popular?api_key=95f62fb6e4d4f64246181381eeeb9cdd");

        }


        if(switch12.getText().toString().contentEquals("Top-rated")){
            new JsonTask().execute("http://api.themoviedb.org/3/movie/top_rated?api_key=95f62fb6e4d4f64246181381eeeb9cdd");

        }

















    }
});



       Intent intent = new Intent(MainActivity.this,DetailsOfMovie.class);












    }










    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getSelectedItem().toString();

       if( item.contentEquals("Popular")) {




           popularSelected = true;




       }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public class JsonTask extends AsyncTask<String,String,String>{


        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection)url.openConnection();

                if(connection != null)
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                 buffer = new StringBuilder();
                String line = "";
                while((line = reader.readLine())!= null){
                    buffer.append(line + "\n");

                }









           } catch (MalformedURLException e) {
               e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }



            if(buffer!= null) {

                return buffer.toString();

            }

            else{

                return null;
            }
        }


        @Override
        protected void onPostExecute(String s) {


            super.onPostExecute(s);
            String [] popularMovies = new String[20];

            s1 = s;



            try {
                if (s1 != null)
                    jo = new JSONObject(s1);

                if (jo != null) {

                    ja = jo.getJSONArray("results");
                }


                for (int i = 0; i < 19; i++) {

                    if (ja != null) {
                        jo1 = ja.getJSONObject(i);
                    }


                    if (jo1 != null) {
                        movieName = jo1.getString("poster_path");
                    }
                    String baseUrl = "http://image.tmdb.org/t/p/w185/";
                    if (movieName != null) {
                         appendedString = baseUrl + movieName;
                    }
                    popularMovies[i] = appendedString;
                    if (jo1 != null) {
                         movieTitle = jo1.getString("title");
                         releaseDate = jo1.getString("release_date");
                         plotSynposis = jo1.getString("overview");
                         voteAverage = jo1.getDouble("vote_average");
                    }

                    if(jo1 != null) {
                        Movies movie = new Movies(movieTitle, appendedString, plotSynposis, voteAverage, releaseDate);
                        movies[i] = movie;
                    }


                    }


                    if(jo1 != null) {


                        DetailsOfMovie details = new DetailsOfMovie(movies, MainActivity.this);
                    }


                } catch(JSONException e){
                    e.printStackTrace();
                }



                if(jo1 != null) {


                    GridView gridView = findViewById(R.id.gridView);
                    gridView.setAdapter(new ImageAdapter(MainActivity.this, popularMovies, movies));
                }

                else{
                Toast.makeText(MainActivity.this, "The internet is not there", Toast.LENGTH_LONG);
                }











        }
    }


    }


























