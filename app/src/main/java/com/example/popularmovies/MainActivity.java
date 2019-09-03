package com.example.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ListItemClickListener {

    private MovieAdapter movieAdapter;
    private RecyclerView mRecyclerView;

    private ArrayList<Movies> mMovies;

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

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        //TODO: send info
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
            Objects.requireNonNull(getSupportActionBar()).setTitle("@string/popular_movies");
            //TODO: sort by popular movies
        } else if (ItemThatWasClickedId == R.id.highestRatedM) {
            Objects.requireNonNull(getSupportActionBar()).setTitle("@string/highest_rated_movies");
            //TODO: sort by highest rated
        }
        return super.onOptionsItemSelected(item);
    }
}
