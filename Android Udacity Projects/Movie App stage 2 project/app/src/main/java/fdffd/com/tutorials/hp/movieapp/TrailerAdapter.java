package fdffd.com.tutorials.hp.movieapp;

import android.content.Context;
import android.net.Uri;
import android.sax.Element;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;

public class TrailerAdapter extends ArrayAdapter<Trailer> {

    private final Context context;
    public final ArrayList<Trailer> values;


    public TrailerAdapter(@NonNull Context context,  ArrayList<Trailer> values) {
        super(context, R.layout.trailer,values);
        this.context = context;
        this.values = values;


    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.trailer,parent, false);
        TextView tvTrailer = rowView.findViewById(R.id.tvTrailer);
        tvTrailer.setText(values.get(position).getText());










        return rowView;
    }
}
