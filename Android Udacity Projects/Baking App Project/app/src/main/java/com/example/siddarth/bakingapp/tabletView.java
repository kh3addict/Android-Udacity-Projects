package com.example.siddarth.bakingapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.esotericsoftware.kryo.util.Util;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.io.IOException;

import io.paperdb.Paper;

public class tabletView extends AppCompatActivity implements RecipieStepsAdapter.ListItemClickListener1  {
VideoView vv;
PlayerView pv;
Button button1;

ExoPlayer player;
long playBackPosition;
int currentWindow;
boolean playWhenReady;
int clickedPosition1;
TextView recipieName;
MediaSource mediaSource;
int index;


TextView tvLongDescription;
BakingWidget bw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablet_view);
        button1 = findViewById(R.id.btnAddToWidged);
        recipieName = findViewById(R.id.tvRecipieName);
        Paper.init(this);

        recipieName.setText(MainActivity.recipieList.get(MainActivity.imageIndex));


        button1.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {

                Paper.book().write("RecipieName",MainActivity.recipieList.get(MainActivity.imageIndex));



                bw = new BakingWidget();




                if(MainActivity.imageIndex == 0) {
                    Paper.book().write("Content", MainActivity.ingredientForNutellaPie);
                    Paper.book().write("Quantity", MainActivity.qtyForNutellaPie);
                    Paper.book().write("Units", MainActivity.measureForNutellaPie);

                }


                if(MainActivity.imageIndex == 1){
                    Paper.book().write("Content", MainActivity.ingredientsForBrownie);
                    Paper.book().write("Quantity", MainActivity.qtyForBrownies);
                    Paper.book().write("Units", MainActivity.measureForBrownies);
                }


                if(MainActivity.imageIndex == 2){
                    Paper.book().write("Content", MainActivity.ingredientsForYellowCake);
                    Paper.book().write("Quantity", MainActivity.qtyForYellowCake);
                    Paper.book().write("Units", MainActivity.measureForYellowCake);
                }


                if(MainActivity.imageIndex == 3){
                    Paper.book().write("Content", MainActivity.ingredientsForCheeseCake);
                    Paper.book().write("Quantity", MainActivity.qtyForCheeseCake);
                    Paper.book().write("Units", MainActivity.measureForCheeseCake);
                }




                if(BakingWidget.count != 0){
                    bw.onUpdate(BakingWidget.context,BakingWidget.widgetProvider,BakingWidget.appWidgetIds1);
                }





            }
        });

        pv = findViewById(R.id.pvVideo);
        tvLongDescription = findViewById(R.id.tvLongDescription);


        RecyclerView rv = findViewById(R.id.rvIngredients);
        RecyclerView rv1 = findViewById(R.id.rvSteps);
        if(MainActivity.imageIndex == 0){
            LinearLayoutManager llm  = new LinearLayoutManager(this);
            rv.setLayoutManager(llm);
            rv.setAdapter(new RecipieIngredientsListTablet(9,MainActivity.ingredientForNutellaPie,MainActivity.measureForNutellaPie,MainActivity.qtyForNutellaPie));
            LinearLayoutManager llm1 = new LinearLayoutManager(this);
            rv1.setLayoutManager(llm1);
            rv1.setAdapter(new RecipieStepsAdapter(MainActivity.shortDescForNutellaPie.size(),MainActivity.shortDescForNutellaPie,this));

        }


        if(MainActivity.imageIndex == 1){
            LinearLayoutManager llm  = new LinearLayoutManager(this);
            rv.setLayoutManager(llm);
            rv.setAdapter(new RecipieIngredientsListTablet(9,MainActivity.ingredientsForBrownie,MainActivity.measureForBrownies,MainActivity.qtyForBrownies));
            LinearLayoutManager llm1 = new LinearLayoutManager(this);
            rv1.setLayoutManager(llm1);
            rv1.setAdapter(new RecipieStepsAdapter(MainActivity.shortDescForBrownies.size(),MainActivity.shortDescForBrownies,this));
        }

        if(MainActivity.imageIndex == 2){
            LinearLayoutManager llm  = new LinearLayoutManager(this);
            rv.setLayoutManager(llm);
            rv.setAdapter(new RecipieIngredientsListTablet(9,MainActivity.ingredientsForYellowCake,MainActivity.measureForYellowCake,MainActivity.qtyForYellowCake));
            LinearLayoutManager llm1 = new LinearLayoutManager(this);
            rv1.setLayoutManager(llm1);
            rv1.setAdapter(new RecipieStepsAdapter(MainActivity.shortDescForYellowCake.size(),MainActivity.shortDescForYellowCake,this));
        }

        if(MainActivity.imageIndex == 3){
            LinearLayoutManager llm  = new LinearLayoutManager(this);
            rv.setLayoutManager(llm);
            rv.setAdapter(new RecipieIngredientsListTablet(9,MainActivity.ingredientsForCheeseCake,MainActivity.measureForCheeseCake,MainActivity.qtyForCheeseCake));
            LinearLayoutManager llm1 = new LinearLayoutManager(this);
            rv1.setLayoutManager(llm1);
            rv1.setAdapter(new RecipieStepsAdapter(MainActivity.shortDescForCheeseCake.size(),MainActivity.shortDescForCheeseCake,this));
        }






    }


    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("exoplayer-codelab")).
                createMediaSource(uri);
    }


    @Override
    public void onListItemClick1(int clickedPosition) {

      clickedPosition1 = clickedPosition;

        if(MainActivity.imageIndex == 0) {


            InitializePlayer(MainActivity.videoUrlsForNutellaPie.get(clickedPosition));







            tvLongDescription.setText(MainActivity.longDescForNutellaPie.get(clickedPosition));







        }
        if(MainActivity.imageIndex == 1) {


            tvLongDescription.setText(MainActivity.longDescForBrownies.get(clickedPosition));
            InitializePlayer(MainActivity.videoUrlsForBrownies.get(clickedPosition));



        }


        if(MainActivity.imageIndex == 2) {


            tvLongDescription.setText(MainActivity.longDescForYellowCake.get(clickedPosition));

           InitializePlayer(MainActivity.videoUrlsForYellowCake.get(clickedPosition));




        }

        if(MainActivity.imageIndex == 3) {


            tvLongDescription.setText(MainActivity.longDescForCheeseCake.get(clickedPosition));

            InitializePlayer(MainActivity.videoUrlsForCheeseCake.get(clickedPosition));


        }






    }



   private void InitializePlayer(String url){
       player = ExoPlayerFactory.newSimpleInstance(
               new DefaultRenderersFactory(this),
               new DefaultTrackSelector(), new DefaultLoadControl());
       pv.setPlayer(player);
       player.setPlayWhenReady(playWhenReady);
       player.seekTo(currentWindow, playBackPosition);
       Uri uri = Uri.parse(url);
       MediaSource mediaSource = buildMediaSource(uri);
       player.prepare(mediaSource, true, false);
   }

    private void releasEPlayer(){
        if(player!= null){
            playBackPosition =  player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;

        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(com.google.android.exoplayer2.util.Util.SDK_INT  <= 23 || player == null){
            if(MainActivity.imageIndex == 0){
                InitializePlayer(MainActivity.videoUrlsForNutellaPie.get(clickedPosition1));
            }

            if(MainActivity.imageIndex == 1){
                InitializePlayer(MainActivity.videoUrlsForBrownies.get(clickedPosition1));
            }

            if(MainActivity.imageIndex == 2){
                InitializePlayer(MainActivity.videoUrlsForYellowCake.get(clickedPosition1));
            }

            if(MainActivity.imageIndex == 3){
                InitializePlayer(MainActivity.videoUrlsForCheeseCake.get(clickedPosition1));
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(com.google.android.exoplayer2.util.Util.SDK_INT  > 23){
            if(MainActivity.imageIndex == 0){
                InitializePlayer(MainActivity.videoUrlsForNutellaPie.get(clickedPosition1));
            }

            if(MainActivity.imageIndex == 1){
                InitializePlayer(MainActivity.videoUrlsForBrownies.get(clickedPosition1));
            }

            if(MainActivity.imageIndex == 2){
                InitializePlayer(MainActivity.videoUrlsForYellowCake.get(clickedPosition1));
            }

            if(MainActivity.imageIndex == 3){
                InitializePlayer(MainActivity.videoUrlsForCheeseCake.get(clickedPosition1));
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(com.google.android.exoplayer2.util.Util.SDK_INT <=23){
            releasEPlayer();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();

        if(com.google.android.exoplayer2.util.Util.SDK_INT  > 23)
            releasEPlayer();
    }
}
