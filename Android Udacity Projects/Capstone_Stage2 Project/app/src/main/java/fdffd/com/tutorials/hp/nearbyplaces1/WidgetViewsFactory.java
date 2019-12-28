package fdffd.com.tutorials.hp.nearbyplaces1;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class WidgetViewsFactory implements RemoteViewsService.RemoteViewsFactory {


    private Context ctxt=null;
    private int appWidgetId;

    public WidgetViewsFactory(Context ctxt, Intent intent) {
        this.ctxt=ctxt;
        appWidgetId=
                intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                        AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {

        if(MapsActivity.nearbyPlaces != null) {

            return MapsActivity.nearbyPlaces.size();
        }

        else{
            return 0;
        }
    }

    @Override
    public RemoteViews getViewAt(int i) {


        RemoteViews row=
                new RemoteViews(ctxt.getPackageName(), R.layout.widgetrow);


        row.setTextViewText(R.id.tvNearbyPlace,MapsActivity.nearbyPlaces.get(i));



        return row;

    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
