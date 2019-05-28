package com.example.a17010233.c302_p06_sakilaclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SummaryAdapter extends ArrayAdapter<Sum>  {

    private ArrayList<Sum> sums;
    private Context context;
    private TextView tvCat;
    private TextView tvNum;

    public SummaryAdapter(Context context, int resource, ArrayList<Sum> objects){
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        sums = objects;
        // Store Context object as we would need to use it later
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the film_list.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.summary_list, parent, false);

        tvCat = rowView.findViewById(R.id.tvCat);
        tvNum = rowView.findViewById(R.id.tvNFilms);

        Sum currentSum = sums.get(position);

        tvCat.setText(currentSum.getCat());
        tvNum.setText(currentSum.getNum());

        return rowView;
    }
}
