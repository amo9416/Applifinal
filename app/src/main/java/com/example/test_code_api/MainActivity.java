package com.example.test_code_api;





import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;



import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.test_code_api.model.ExampleItem;


import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ExampleAdapter.OnItemClickListener {


    public static final String EXTRA_URL = "imageUrl"; // sert pr le detailActivity
    public static final String EXTRA_CREATOR = "overview";  // sert pr le detailActivity

    public String overview;

    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdaptper;
    private ArrayList<ExampleItem> mExamplelist;
    private RequestQueue mRequestQueue;
    private SearchView searchView;

    public int k = 1;
    TextView mTextViewResult;
    ImageView imageView;
    RequestQueue mQueue;
    String urlimage;
    int mode_filter_on = 0 ;
    ArrayList<ExampleItem> filteredList ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchView);
        CharSequence query = searchView.getQuery();
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String texte = newText;
                filteredList = filterList(newText);
                mode_filter_on = 1 ;
                return true;
            }
        });

        /*mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mExamplelist = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }*/

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExamplelist = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    public  ArrayList<ExampleItem> filterList(String text) {
        ArrayList<ExampleItem> filteredList = new ArrayList<>();
        for(ExampleItem item : mExamplelist){
            if(item.getCreator().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }

        if(filteredList.isEmpty()){
            Toast.makeText(this,"Aucune donnée trouvée",Toast.LENGTH_SHORT).show();
        }else{
            mExampleAdaptper.setFilteredList(filteredList);

        }

        return filteredList;
    }


    private void parseJSON() {

        for (k = 0; k < 50; k++)
        {

            String Url = "https://api.themoviedb.org/3/movie/top_rated?api_key=94919f610cee6635900db1b211be75e7&language=en-US&page=" + k;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject results = jsonArray.getJSONObject(i);
                                String title = results.getString("title");
                                String date = results.getString("release_date");
                                double rating = results.getInt("vote_average");
                                overview = results.getString("overview");
                                String image = results.getString("poster_path");
                                urlimage = "https://image.tmdb.org/t/p/w500" + image;
                                mExamplelist.add(new ExampleItem(urlimage, title, rating, date, overview));
                            }

                            mExampleAdaptper = new ExampleAdapter(MainActivity.this, mExamplelist);
                            mRecyclerView.setAdapter(mExampleAdaptper);
                            mExampleAdaptper.setOnItemClickListener(MainActivity.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);

    }

}


    /*@Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        ExampleItem clickedItem = mExamplelist.get(position);

        detailIntent.putExtra(EXTRA_URL, clickedItem.getImageUrl());
        detailIntent.putExtra(EXTRA_CREATOR,clickedItem.getOverview());

        startActivity(detailIntent);

    }*/
    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        ExampleItem clickedItem;
        if(mode_filter_on == 1) {
            clickedItem = filteredList.get(position);

        }else{
            clickedItem = mExamplelist.get(position);
            mode_filter_on = 0;
        }

        detailIntent.putExtra(EXTRA_URL, clickedItem.getImageUrl());
        detailIntent.putExtra(EXTRA_CREATOR,clickedItem.getOverview());

        startActivity(detailIntent);
    }
}
