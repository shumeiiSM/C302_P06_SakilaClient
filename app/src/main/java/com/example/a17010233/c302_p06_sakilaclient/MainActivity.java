package com.example.a17010233.c302_p06_sakilaclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    private ListView lvCategories;
    ArrayList<Category> alCategories = new ArrayList<Category>();
    ArrayAdapter<Category> aaCategories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCategories = (ListView) findViewById(R.id.listViewCategories);
        aaCategories = new ArrayAdapter<Category>(this, android.R.layout.simple_list_item_1, alCategories);
        lvCategories.setAdapter(aaCategories);

        //TODO: Task 1: Consume getCategories.php using AsyncHttpClient


        lvCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Category c = alCategories.get(i);  // Get the selected Category


                Intent intent = new Intent(MainActivity.this, ViewFilmsActivity.class);
                intent.putExtra("category", c);
                startActivity(intent);
            }
        });

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://10.0.2.2/C302_sakila/getCategories.php", new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // called when response HTTP status is "200 OK"

                try {

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject category = (JSONObject) response.get(i);
                        int catId = category.getInt("category_id");
                        String name = category.getString("name");

                        // Category c = new Category(category.getInt("category_id"), category.getString("name"));

                        Category c = new Category(catId, name);
                        alCategories.add(c);
                    }
                    aaCategories.notifyDataSetChanged();

                } catch (JSONException e) {

                } // end try

            } // end onSuccess
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement2
//        if (id == R.id.action_film) {
//            return true;
//
//        } else if (id == R.id.action_rental) {
//            return true;

        if (id == R.id.action_summary) {
            Intent intent = new Intent(getApplicationContext(), Summary.class);
            startActivity(intent);

            return true;

        } else if (id == R.id.action_search) {
            Intent intent = new Intent(getApplicationContext(), SearchFilmActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
