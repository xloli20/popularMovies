package com.example.popularmovies;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {
    //TODO: fetch data
    private static final String TAG = JsonUtils.class.getSimpleName();

    public static ArrayList<Movies> parseMoviesJson(String json) throws JSONException {
/* {

   "results":[
      {
         "popularity":358.378,
         "vote_count":464,
         "video":false,
         "poster_path":"/a4BfxRK8dBgbQqbRxPs8kmLd8LG.jpg",
         "id":429203,
         "adult":false,
         "backdrop_path":"/8bRIfPGDnmWgdy65LO8xtdcFmFP.jpg",
         "original_language":"en",
         "original_title":"The Old Man & the Gun",
         "genre_ids":[  ],
         "title":"The Old Man & the Gun",
         "vote_average":6.4,
         "overview":"The true story of Forrest Tucker, from his audacious escape from San Quentin at the age of 70 to an unprecedented string of heists that confounded authorities and enchanted the public. Wrapped up in the pursuit are a detective, who becomes captivated with Forrest’s commitment to his craft, and a woman, who loves him in spite of his chosen profession.",
         "release_date":"2018-09-28"
      }, */

        JSONObject movies = new JSONObject(json);
        JSONArray results = movies.getJSONArray("results");

        ArrayList<Movies> moviesArrayList = new ArrayList<>();
        //Movies movie = null;

        if (results.length() != 0) {
            for (int i = 0; i <= 19; i++) {
                JSONObject movieData = (JSONObject) results.get(i);
                Movies movie = new Movies(
                        movieData.optString("title"),
                        movieData.optString("poster_path"),
                        movieData.optString("overview"),
                        movieData.optDouble("vote_average"),
                        movieData.optString("release_date")
                );
                Log.d(TAG, "parseMoviesJson: movie:" + movie);
                moviesArrayList.add(movie);

            }
            Log.d(TAG, "parseMoviesJson: moviesArrayList:" + moviesArrayList);

        }

        Log.d(TAG, "parseMoviesJson: moviesArrayList2:" + moviesArrayList);
        return moviesArrayList;

    }
}
