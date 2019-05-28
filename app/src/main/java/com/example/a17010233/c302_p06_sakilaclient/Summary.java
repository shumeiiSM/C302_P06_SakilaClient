package com.example.a17010233.c302_p06_sakilaclient;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Summary extends AppCompatActivity {

    private ListView lvSum;

    ArrayList<Sum> alSum;
    ArrayAdapter<Sum> aaSum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        lvSum = (ListView) findViewById(R.id.listViewSummary);

        alSum = new ArrayList<Sum>();
        aaSum = new SummaryAdapter(this, R.layout.summary_list, alSum);
        lvSum.setAdapter(aaSum);


        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://10.0.2.2/C302_sakila/getCategorySummary.php", new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // called when response HTTP status is "200 OK"

                try {

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject sum = (JSONObject) response.get(i);
                        String cat = sum.getString("category");
                        String name = sum.getString("number_films");

                        // Category c = new Category(category.getInt("category_id"), category.getString("name"));

                        Sum s = new Sum(cat, name);
                        alSum.add(s);
                    }
                    aaSum.notifyDataSetChanged();

                } catch (JSONException e) {

                } // end try

            } // end onSuccess
        });
    }
}
