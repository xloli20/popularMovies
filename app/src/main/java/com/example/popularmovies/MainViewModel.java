package com.example.popularmovies;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.popularmovies.Database.AppDatabase;
import com.example.popularmovies.Database.FavoritesMovies;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<List<FavoritesMovies>> favorites;

    public MainViewModel(Application application) {
        super(application);
        // use the loadAllTasks of the taskDao to initialize the favorites variable
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        Log.d(TAG, "Actively retrieving the favorites movies from the DataBase");
        favorites = database.favoritesMoviesDao().getAll();
    }

    // a getter for the favorites variable
    public LiveData<List<FavoritesMovies>> getFavorites() {
        return favorites;
    }
}
