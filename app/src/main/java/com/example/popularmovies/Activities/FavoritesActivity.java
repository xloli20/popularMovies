package com.example.popularmovies.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.example.popularmovies.Adapters.FavoritesAdapter;
import com.example.popularmovies.AppExecutors;
import com.example.popularmovies.Database.AppDatabase;
import com.example.popularmovies.Database.FavoritesMovies;
import com.example.popularmovies.MainViewModel;
import com.example.popularmovies.R;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {
    private static final String TAG = FavoritesActivity.class.getSimpleName();


    //ImageView mFavorite;

    private FavoritesAdapter favoritesAdapter;
    private RecyclerView fRecyclerView;

    //DB object
    private AppDatabase mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        fRecyclerView = findViewById(R.id.fav_recycler_view);

        //setting the favorites movies recycler view
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        fRecyclerView.setLayoutManager(mLayoutManager);
        fRecyclerView.setHasFixedSize(true);
        favoritesAdapter = new FavoritesAdapter(this);
        fRecyclerView.setAdapter(favoritesAdapter);


        //DB initialization
        mDB = AppDatabase.getInstance(getApplicationContext());

        //
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            // Called when a user swipes left or right on a ViewHolder
            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                // call the diskIO execute method with a new Runnable and implement its run method
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        // get the position from the viewHolder parameter
                        int position = viewHolder.getAdapterPosition();
                        List<FavoritesMovies> favorites = favoritesAdapter.getTasks();
                        //Call deleteTask in the taskDao with the task at that position
                        mDB.favoritesMoviesDao().deleteMovie(favorites.get(position));
                    }
                });
            }
        }).attachToRecyclerView(fRecyclerView);

//        mFavorite = findViewById(R.id.fav);
//        mFavorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View view) {
//                mFavorite.setImageResource(R.drawable.ic_unfav);
//                //delete movie data from the DB
////                AppExecutors.getInstance().diskIO().execute(new Runnable() {
////                    @Override
////                    public void run() {
////                        int position = view.
////                        List<FavoritesMovies> favorites = favoritesAdapter.getFavorites();
////                        mDB.favoritesMoviesDao().deleteMovie(favorites.get(position));
////                        retrieveTasks();
////                    }
////                });
//            }
//        });

        setupViewModel();
    }


    private void setupViewModel() {
        // Declare a ViewModel variable and initialize it by calling ViewModelProviders.of
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // Observe the LiveData object in the ViewModel
        viewModel.getFavorites().observe(this, new Observer<List<FavoritesMovies>>() {
            @Override
            public void onChanged(@Nullable List<FavoritesMovies> favoritesMovies) {
                Log.d(TAG, "Updating list of movies from LiveData in ViewModel");
                favoritesAdapter.setmFavorites(favoritesMovies);
            }
        });
    }

}
