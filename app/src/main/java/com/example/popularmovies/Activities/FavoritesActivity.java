package com.example.popularmovies.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.popularmovies.Database.AppDatabase;
import com.example.popularmovies.Database.FavoritesMovies;
import com.example.popularmovies.FavoritesAdapter;
import com.example.popularmovies.R;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {
    private static final String TAG = FavoritesActivity.class.getSimpleName();


    ImageView mFavorite;
    private FavoritesAdapter favoritesAdapter;
    private RecyclerView fRecyclerView;
    private TextView errorMessage;

    private AppDatabase mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        fRecyclerView = findViewById(R.id.fav_recycler_view);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        fRecyclerView.setLayoutManager(mLayoutManager);
        fRecyclerView.setHasFixedSize(true);
        Log.d(TAG, "onResume: get all ");

        List<FavoritesMovies> aa = mDB.FavoritesMoviesDao().getAll();
        Log.d(TAG, "onResume: get all " + aa);

        favoritesAdapter = new FavoritesAdapter(aa);
        fRecyclerView.setAdapter(favoritesAdapter);

        errorMessage = findViewById(R.id.error_fav_massage);


        mDB = AppDatabase.getInstance(getApplicationContext());

        mFavorite = findViewById(R.id.fav);
//        mFavorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mFavorite.setImageResource(R.drawable.ic_unfav);
//                //Todo: delete movie data from the DB
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<FavoritesMovies> aa = mDB.FavoritesMoviesDao().getAll();
        Log.d(TAG, "onResume: get all " + aa);
        favoritesAdapter = new FavoritesAdapter(mDB.FavoritesMoviesDao().getAll());

    }
}
