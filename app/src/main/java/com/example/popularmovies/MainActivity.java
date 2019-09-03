package com.example.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ListItemClickListener {

    private MovieAdapter movieAdapter;
    private RecyclerView mRecyclerView;

    private TextView errorMessage;
    private ProgressBar progressBar;

    private ArrayList<Movies> mMovies;

    URL urL = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.my_recycler_view);

        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setHasFixedSize(true);
        movieAdapter = new MovieAdapter(mMovies, this);
        mRecyclerView.setAdapter(movieAdapter);

        errorMessage = findViewById(R.id.error_massage);

    }

    public void setMovieAdapter() {
        movieAdapter = new MovieAdapter(mMovies, this);
        mRecyclerView.setAdapter(movieAdapter);
    }
    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        //TODO: send info to detailsActivity
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int ItemThatWasClickedId = item.getItemId();
        if (ItemThatWasClickedId == R.id.popularM) {
            Objects.requireNonNull(getSupportActionBar()).setTitle("Popular Movies");
            //TODO: sort by popular movies
            urL = NetworkUtils.buildUrl("popularity.desc");
            new MoviesQueryTask().execute(urL);

        } else if (ItemThatWasClickedId == R.id.highestRatedM) {
            Objects.requireNonNull(getSupportActionBar()).setTitle("Highest Rated Movies");
            //TODO: sort by highest rated
            urL = NetworkUtils.buildUrl("popular");
            new MoviesQueryTask().execute(urL);
        }
        return super.onOptionsItemSelected(item);
    }

    private void showJsonDataView() {
        errorMessage.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        errorMessage.setVisibility(View.VISIBLE);
    }

    public class MoviesQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            String mResult = null;
            try {
                mResult = NetworkUtils.getResponseFromHttpURL(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return mResult;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null && !s.equals("")) {
                showJsonDataView();
                try {
                    //mMovies.clear();
                    mMovies = JsonUtils.parseMoviesJson(s);
                    setMovieAdapter();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                showErrorMessage();
            }
        }
    }
}
