package com.example.popularmovies.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.popularmovies.FavoritesAdapter;
import com.example.popularmovies.Movies;
import com.example.popularmovies.R;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    ImageView mFavorite;
    private FavoritesAdapter favoritesAdapter;
    private RecyclerView fRecyclerView;
    private TextView errorMessage;
    private ArrayList<Movies> fMovies = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        fRecyclerView = findViewById(R.id.fav_recycler_view);

        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        fRecyclerView.setLayoutManager(mLayoutManager);

        fRecyclerView.setHasFixedSize(true);

        favoritesAdapter = new FavoritesAdapter(fMovies);
        fRecyclerView.setAdapter(favoritesAdapter);

        errorMessage = findViewById(R.id.error_fav_massage);


//        mFavorite = findViewById(R.id.fav);
//        //if(mFavorite.getResources().getDrawable() == R.drawable.ic_unfav)
//        mFavorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mFavorite.setImageResource(R.drawable.ic_unfav);
//                //Todo: delete movie data from the DB
//            }
//        });
    }
}
