package com.example.siddarth.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import java.util.List;

import io.paperdb.Paper;

public class BakingWidget extends AppWidgetProvider {
    public static String EXTRA_WORD=
            "com.commonsware.android.appwidget.lorem.WORD";
    static List<String> ingredient;

    static double [] array1;

    static List<String> measure;

    static int count = 0;
    static AppWidgetManager widgetProvider;
    static Context context;
    static int [] appWidgetIds1;





    @Override
    public void onUpdate(Context ctxt, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {


        context = ctxt;
        widgetProvider = appWidgetManager;
        appWidgetIds1 = appWidgetIds;
        count++;


        Paper.init(ctxt);
        ingredient = Paper.book().read("Content");
        array1 = Paper.book().read("Quantity");
         measure = Paper.book().read("Units");




        for (int i=0; i<appWidgetIds.length; i++) {
            Intent svcIntent=new Intent(ctxt, WidgetService.class);

            svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
            svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));

            RemoteViews widget=new RemoteViews(ctxt.getPackageName(),
                    R.layout.baking_widget);

            widget.setRemoteAdapter(R.id.lvWidget, svcIntent);
      /*Intent clickIntent=new Intent(ctxt, LoremActivity.class);
      PendingIntent clickPI=PendingIntent
                              .getActivity(ctxt, 0,
                                            clickIntent,
                                            PendingIntent.FLAG_UPDATE_CURRENT);

      widget.setPendingIntentTemplate(R.id.words, clickPI);*/

            appWidgetManager.updateAppWidget(appWidgetIds[i], widget);
        }

        super.onUpdate(context, widgetProvider, appWidgetIds1);
    }
}