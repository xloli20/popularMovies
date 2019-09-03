package com.example.popularmovies;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    final static String MOVIES_BASE_URL =
            "https://api.themoviedb.org/3/discover/movie?";

    final static String PARAM_API_KEY = "api_key";
    final static String API_KEY = "53060cc351a94100316d9fdab87ffc7e";

    final static String PARAM_SORT = "sort_by";
    final static String sortByPopular = "popular";


    public static URL buildUrl(String Query) {
        Uri builtUri = Uri.parse(MOVIES_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_API_KEY, API_KEY)
                .appendQueryParameter(PARAM_SORT, Query)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


    public static String getResponseFromHttpURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
