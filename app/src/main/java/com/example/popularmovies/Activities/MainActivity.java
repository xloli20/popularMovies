package com.example.popularmovies.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.popularmovies.Adapters.MovieAdapter;
import com.example.popularmovies.Models.Movies;
import com.example.popularmovies.R;
import com.example.popularmovies.Utils.JsonUtils;
import com.example.popularmovies.Utils.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ListItemClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    //global URL object
    URL urL = null;
    private RecyclerView mRecyclerView;
    private ArrayList<Movies> mMovies = new ArrayList<>();
    private ProgressBar progressBar;
    //views
    private TextView errorMessage;

    public final static String LIST_STATE_KEY = "recycler_list_state";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.my_recycler_view);

        //set layout manager to the recycler view
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, numberOfColumns());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //views
        errorMessage = findViewById(R.id.error_massage);
        progressBar = findViewById(R.id.progress_bar);

        //check if there's any saved state
        if (savedInstanceState != null) {
            showJsonDataView();
            // Retrieve list state and list/item positions
            mMovies = savedInstanceState.getParcelableArrayList(LIST_STATE_KEY);
            Log.d(TAG, "onCreate: receiving parcelable" + mMovies);
            setMovieAdapter();

        } else {
            //build url that will populate the popular movies
            urL = NetworkUtils.buildUrl("popular");
            new MoviesQueryTask().execute(urL);

            //set the adapter
            setMovieAdapter();
        }
    }

    private int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // You can change this divider to adjust the size of the item
        int widthDivider = 450;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < 2) return 2; //to keep the grid aspect
        return nColumns;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save list state
        outState.putParcelableArrayList(LIST_STATE_KEY, mMovies);
        Log.d(TAG, "onSaveInstanceState: sending parcelable" + mMovies);
    }

    public void setMovieAdapter() {
        //for movies
        MovieAdapter movieAdapter = new MovieAdapter(mMovies, this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(movieAdapter);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        //send the clicked movie info to DetailsActivity using Parcelable
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("Movies", mMovies.get(clickedItemIndex));
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
            //http://api.themoviedb.org/3/movie/popular?api_key=53060cc351a94100316d9fdab87ffc7e
            urL = NetworkUtils.buildUrl("popular");
            new MoviesQueryTask().execute(urL);

        } else if (ItemThatWasClickedId == R.id.highestRatedM) {
            Objects.requireNonNull(getSupportActionBar()).setTitle("Highest Rated Movies");
            //http://api.themoviedb.org/3/movie/top_rated?api_key=53060cc351a94100316d9fdab87ffc7e
            urL = NetworkUtils.buildUrl("top_rated");
            new MoviesQueryTask().execute(urL);

        } else if (ItemThatWasClickedId == R.id.favs) {
            Intent intent = new Intent(this, FavoritesActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void showJsonDataView() {
        errorMessage.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        progressBar.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
        errorMessage.setVisibility(View.VISIBLE);
    }

    public class MoviesQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

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
                    //parse the movies from API
                    mMovies = JsonUtils.parseMoviesJson(s);//
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
