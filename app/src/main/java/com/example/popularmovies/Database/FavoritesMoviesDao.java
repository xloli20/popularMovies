package com.example.popularmovies.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface FavoritesMoviesDao {
    @Query("SELECT * FROM FavoritesMovies")
    List<FavoritesMovies> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(FavoritesMovies favoritesMovies);

    @Delete
    void deleteMovie(FavoritesMovies favoritesMovies);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMovie(FavoritesMovies favoritesMovies);
}
