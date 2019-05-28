package com.example.a17010233.c302_p06_sakilaclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FilmAdapter extends ArrayAdapter<Film> {

    private ArrayList<Film> films;
    private Context context;
    private TextView tvTitle;
    private TextView tvYear;
    private TextView tvRate;

    public FilmAdapter(Context context, int resource, ArrayList<Film> objects){
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        films = objects;
        // Store Context object as we would need to use it later
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the film_list.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.film_list, parent, false);

        tvTitle = rowView.findViewById(R.id.tvTitle);
        tvYear = rowView.findViewById(R.id.tvYear);
        tvRate = rowView.findViewById(R.id.tvRating);

        Film currentFilm = films.get(position);

        tvTitle.setText(currentFilm.getTitle());
        tvYear.setText(String.valueOf(currentFilm.getYear()));
        tvRate.setText(currentFilm.getRate());


        return rowView;
    }
}
