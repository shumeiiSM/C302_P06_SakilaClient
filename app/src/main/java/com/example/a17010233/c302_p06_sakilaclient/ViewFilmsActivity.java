package com.example.a17010233.c302_p06_sakilaclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ViewFilmsActivity extends AppCompatActivity {

    private ListView lv;
    TextView tvCategory;
    Button btnBack;

    ArrayAdapter<Film> aaFilms;
    ArrayList<Film> alFilms;

//    ArrayAdapter aaFilms;
//    ArrayList<Film> alFilms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_films);

        lv = findViewById(R.id.listViewDetails);
        tvCategory = findViewById(R.id.tvCategory);
        btnBack = findViewById(R.id.btnBack);


        Intent intent = getIntent();
        Category c = (Category) intent.getSerializableExtra("category");
        tvCategory.setText(c.getName());

        alFilms = new ArrayList<Film>();
        aaFilms = new FilmAdapter(this, R.layout.film_list, alFilms);
        lv.setAdapter(aaFilms);

        // set "ID" parameter
        RequestParams params = new RequestParams();
        params.add("id", String.valueOf(c.getId()));
//        params.put("key", "value");
//        params.put("more", "data");


        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://10.0.2.2/C302_sakila/getFilmsByCategoryId.php", params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // called when response HTTP status is "200 OK"

                try {

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject film = (JSONObject) response.get(i);
                        int filmId = film.getInt("film_id");
                        String title = film.getString("title");
                        String year = film.getString("release_year");
                        String rating = film.getString("rating");

                        // Category c = new Category(category.getInt("category_id"), category.getString("name"));

                        Film f = new Film(filmId, title, year, rating);
                        alFilms.add(f);
                    }

                    aaFilms.notifyDataSetChanged();

                } catch (JSONException e) {

                } // end try

            } // end onSuccess
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
