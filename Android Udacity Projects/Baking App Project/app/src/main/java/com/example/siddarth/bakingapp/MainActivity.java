package com.example.siddarth.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.widget.LinearLayout;
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

public class MainActivity extends AppCompatActivity implements RecipieAdapter.ListItemClickListener {

    static StringBuilder buffer;
    static int imageIndex;
    private RecipieAdapter ra;
    private RecyclerView rvRecipies;
    String url1;
    String s1;
    double screenInches;


    static double [] qtyForNutellaPie = new double[50];
    static List<String> measureForNutellaPie = new ArrayList<>();
    static List<String> ingredientForNutellaPie = new ArrayList<>();
    static List<String> shortDescForNutellaPie = new ArrayList<>();
    static List<String> longDescForNutellaPie = new ArrayList<>();
    static List<String> videoUrlsForNutellaPie = new ArrayList<>();

    static double [] qtyForBrownies = new double[50];
    static List<String> measureForBrownies = new ArrayList<>();
    static List<String> ingredientsForBrownie = new ArrayList<>();
    static List<String> shortDescForBrownies = new ArrayList<>();
    static List<String> longDescForBrownies = new ArrayList<>();
    static List<String> videoUrlsForBrownies = new ArrayList<>();


    static double [] qtyForYellowCake = new double[50];
    static List<String> measureForYellowCake = new ArrayList<>();
    static List<String> ingredientsForYellowCake = new ArrayList<>();
    static List<String> shortDescForYellowCake = new ArrayList<>();
    static List<String> longDescForYellowCake = new ArrayList<>();
    static List<String> videoUrlsForYellowCake = new ArrayList<>();


    static double [] qtyForCheeseCake = new double[50];
    static List<String> measureForCheeseCake = new ArrayList<>();
    static List<String> ingredientsForCheeseCake = new ArrayList<>();
    static List<String> shortDescForCheeseCake = new ArrayList<>();
    static List<String> longDescForCheeseCake = new ArrayList<>();
    static List<String> videoUrlsForCheeseCake = new ArrayList<>();

    static List<String> recipieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String inputSystem;
        inputSystem = android.os.Build.ID;
        Log.d("hai",inputSystem);
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();  // deprecated
        int height = display.getHeight();  // deprecated
        Log.d("hai",width+"");
        Log.d("hai",height+"");
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        double x = Math.pow(width/dm.xdpi,2);
        double y = Math.pow(height/dm.ydpi,2);
        screenInches = Math.sqrt(x+y);
        Log.d("hai","Screen inches : " + screenInches+"");


        try {
            new JsonTask().execute("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onListItemClick(int clickedPosition) {


        imageIndex = clickedPosition;


            Intent intent = new Intent(MainActivity.this, tabletView.class);
            startActivity(intent);





    }


    public class JsonTask extends AsyncTask<String,String,String>{

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
        super.onPostExecute(s);
        s1 = s;
        try {
            JSONArray ja = new JSONArray(s1);
            for(int i = 0; i < ja.length(); i++){
                JSONObject jo = ja.getJSONObject(i);
                String name = jo.getString("name");
                JSONArray ja1 = jo.getJSONArray("ingredients");
                for(int j = 0 ; j < ja1.length(); j++) {
                    JSONObject jo1 = ja1.getJSONObject(j);
                    double quantity = jo1.getDouble("quantity");
                    String measure = jo1.getString("measure");
                    String ingredient = jo1.getString("ingredient");
                    if(i == 0){

                        qtyForNutellaPie[j] = quantity;
                        measureForNutellaPie.add(measure);
                        ingredientForNutellaPie.add(ingredient);

                    }

                    if( i == 1){
                        qtyForBrownies[j] = quantity;
                        measureForBrownies.add(measure);
                        ingredientsForBrownie.add(ingredient);
                    }


                    if( i == 2){
                        qtyForYellowCake[j] = quantity;
                        measureForYellowCake.add(measure);
                        ingredientsForYellowCake.add(ingredient);
                    }


                    if( i ==3){
                        qtyForCheeseCake[j] = quantity;
                        measureForCheeseCake.add(measure);
                        ingredientsForCheeseCake.add(ingredient);
                    }



                }

                JSONArray ja2 = jo.getJSONArray("steps");

                for(int k = 0 ; k < ja2.length(); k++){
                    JSONObject jo2 = ja2.getJSONObject(k);
                    String shortDescription = jo2.getString("shortDescription");
                    String description = jo2.getString("description");
                    String videoUrl = jo2.getString("videoURL");
                    if( i == 0){
                        shortDescForNutellaPie.add(shortDescription);
                        longDescForNutellaPie.add(description);
                        videoUrlsForNutellaPie.add(videoUrl);
                    }
                    if( i == 1){
                        shortDescForBrownies.add(shortDescription);
                        longDescForBrownies.add(description);
                        videoUrlsForBrownies.add(videoUrl);
                    }
                    if( i == 2){
                        shortDescForYellowCake.add(shortDescription);
                        longDescForYellowCake.add(description);
                        videoUrlsForYellowCake.add(videoUrl);
                    }
                    if( i == 3){
                        shortDescForCheeseCake.add(shortDescription);
                        longDescForCheeseCake.add(description);
                        videoUrlsForCheeseCake.add(videoUrl);
                    }

                }


                recipieList.add(name);

            }




            RecipieAdapter ra = new RecipieAdapter(recipieList,MainActivity.this);
            rvRecipies = findViewById(R.id.rvRecipies);


            if(screenInches <= 4.985714279819696) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                rvRecipies.setLayoutManager(layoutManager);
                ra = new RecipieAdapter(4,MainActivity.this);
                rvRecipies.setAdapter(ra);

            }


            if(screenInches > 4.985714279819696) {
                GridLayoutManager glm =  new GridLayoutManager(MainActivity.this,2);
                rvRecipies.setLayoutManager(glm);
                ra = new RecipieAdapter(4,MainActivity.this);
                rvRecipies.setAdapter(ra);

            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}








}
