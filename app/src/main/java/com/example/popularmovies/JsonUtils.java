package com.example.popularmovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {
    //TODO: fetch data
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
        Movies movie = null;
        if (results.length() != 0) {
            for (int i = 0; i <= results.length(); i++) {
                JSONObject movieData = results.getJSONObject(i);
                movie = new Movies(
                        movieData.optString("title"),
                        movieData.optString("poster_path"),
                        movieData.optString("overview"),
                        movieData.optDouble("vote_average"),
                        movieData.optString("release_date")
                );
            }
            moviesArrayList.add(movie);
        }

        return moviesArrayList;
    }
}
