package com.example.popularmovies.Utils;

import com.example.popularmovies.Models.Movies;
import com.example.popularmovies.Models.Reviews;
import com.example.popularmovies.Models.Trailers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public final class JsonUtils {

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

        if (results.length() != 0) {
            for (int i = 0; i <= results.length() - 1; i++) {
                JSONObject movieData = results.getJSONObject(i);
                Movies movie = new Movies(
                        movieData.optString("title"),
                        movieData.optString("poster_path"),
                        movieData.optString("overview"),
                        movieData.optDouble("vote_average"),
                        movieData.optString("release_date"),
                        movieData.optInt("id")
                );
                moviesArrayList.add(movie);

            }

        }
        return moviesArrayList;
    }

    public static ArrayList<Trailers> parseTrailersJson(String json) throws JSONException {
/* {
   "id":429203,
   "results":[
      {
         "id":"5b16c40492514178960195f6",
         "iso_639_1":"en",
         "iso_3166_1":"US",
         "key":"d7rlUe-Thvk",
         "name":"THE OLD MAN & THE GUN | Official Trailer [HD] | FOX Searchlight",
         "site":"YouTube",
         "size":1080,
         "type":"Trailer"
      }, */

        JSONObject trailers = new JSONObject(json);
        JSONArray results = trailers.getJSONArray("results");

        ArrayList<Trailers> trailerArrayList = new ArrayList<>();

        if (results.length() != 0) {
            for (int i = 0; i <= results.length() - 1; i++) {
                JSONObject trailerData = results.getJSONObject(i);

                Trailers trailer = new Trailers(trailerData.optString("key"));

                trailerArrayList.add(trailer);

            }

        }
        return trailerArrayList;
    }

    public static ArrayList<Reviews> parseReviewsJson(String json) throws JSONException {
/* {
   "id":429203,
   "page":1,
   "results":[
      {
         "author":"Stephen Campbell",
         "content":"**_A well-made, old-fashioned yarn, but the laid-back ballad-like tone will be too insubstantial for some_**\r\n\r\n>
         _In the old days, the stickup men were like cowboys. They would just go in shooting, yelling for everyone to lie down. But to me violence is the
         first sign of an
          */

        JSONObject reviews = new JSONObject(json);
        JSONArray results = reviews.getJSONArray("results");

        ArrayList<Reviews> reviewsArrayList = new ArrayList<>();

        if (results.length() != 0) {
            for (int i = 0; i <= results.length() - 1; i++) {
                JSONObject reviewsData = results.getJSONObject(i);

                Reviews review = new Reviews(
                        reviewsData.optString("author"),
                        reviewsData.optString("content")
                );

                reviewsArrayList.add(review);

            }

        }
        return reviewsArrayList;
    }

}
