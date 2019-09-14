package com.example.popularmovies.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.popularmovies.Adapters.ReviewsAdapter;
import com.example.popularmovies.Adapters.TrailerAdapter;
import com.example.popularmovies.AppExecutors;
import com.example.popularmovies.Database.AppDatabase;
import com.example.popularmovies.Database.FavoritesMovies;
import com.example.popularmovies.Models.Movies;
import com.example.popularmovies.Models.Reviews;
import com.example.popularmovies.Models.Trailers;
import com.example.popularmovies.R;
import com.example.popularmovies.Utils.JsonUtils;
import com.example.popularmovies.Utils.NetworkUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity implements TrailerAdapter.ListItemClickListener, ReviewsAdapter.ListItemClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private AppDatabase mDB;

    ImageView mImageView;
    TextView mTitleTextView;
    TextView mRatingTextView;
    TextView mPlotTextView;
    TextView mDateTextView;
    ImageView mFavorite;

    RecyclerView trailerRecyclerView;
    RecyclerView reviewRecyclerView;

    TrailerAdapter trailerAdapter;
    private ArrayList<Trailers> trailers = new ArrayList<>();

    ReviewsAdapter reviewsAdapter;
    private ArrayList<Reviews> reviews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mDB = AppDatabase.getInstance(getApplicationContext());

        trailerRecyclerView = findViewById(R.id.trailer_list_view);
        reviewRecyclerView = findViewById(R.id.reviews_list_view);
        mImageView = findViewById(R.id.mPoster);
        mTitleTextView = findViewById(R.id.mTitle);
        mRatingTextView = findViewById(R.id.mRating);
        mPlotTextView = findViewById(R.id.mPlot);
        mDateTextView = findViewById(R.id.mDate);
        mFavorite = findViewById(R.id.fav);


        final Intent intent = getIntent();
        final Movies movies = intent.getParcelableExtra("Movies");
        mTitleTextView.setText(movies.getmTitle());
        mPlotTextView.setText(movies.getmPlot());
        mRatingTextView.setText(Double.toString(movies.getmRating()));
        mDateTextView.setText(movies.getmDate());
        Picasso.get()
                .load(movies.getmImage())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(mImageView);

        mFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFavorite.setImageResource(R.drawable.ic_fav);
                //Todo: add movie data to the DB
                String mTitle = movies.getmTitle();
                int mId = movies.getId();
                String mDate = movies.getmDate();
                String mImage = movies.getmImage();

                final FavoritesMovies favoritesMovies = new FavoritesMovies(mTitle, mImage, mId, mDate);

                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        mDB.favoritesMoviesDao().insertMovie(favoritesMovies);
                        Log.d(TAG, "onClick: movie inserted ");
                        finish();
                    }
                });
            }
        });

        LinearLayoutManager tLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        trailerRecyclerView.setLayoutManager(tLinearLayoutManager);
        setTrailerAdapter();
        URL urL = NetworkUtils.buildUrl2(String.valueOf(movies.getId()), "videos");
        Log.d(TAG, "onCreate: url " + urL);
        new TrailerQueryTask().execute(urL);

        LinearLayoutManager rLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        reviewRecyclerView.setLayoutManager(rLinearLayoutManager);
        setReviewAdapter();
        URL url2 = NetworkUtils.buildUrl2(String.valueOf(movies.getId()), "reviews");
        Log.d(TAG, "onCreate: url " + url2);
        new ReviewQueryTask().execute(url2);
    }

    public void setTrailerAdapter() {
        trailerAdapter = new TrailerAdapter(trailers, this);
        trailerRecyclerView.setHasFixedSize(true);
        trailerRecyclerView.setAdapter(trailerAdapter);
    }

    public void setReviewAdapter() {
        reviewsAdapter = new ReviewsAdapter(reviews, this);
        reviewRecyclerView.setHasFixedSize(true);
        reviewRecyclerView.setAdapter(reviewsAdapter);
    }
    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intentYT = new Intent(Intent.ACTION_VIEW);
        intentYT.setData(Uri.parse("https://www.youtube.com/watch?v=" + trailers.get(clickedItemIndex).gettKey()));
        startActivity(intentYT);
    }



    public class TrailerQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            String mResult = null;
            try {
                mResult = NetworkUtils.getResponseFromHttpURL(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return mResult;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null && !s.equals("")) {
                try {
                    trailers = JsonUtils.parseTrailersJson(s);
                    setTrailerAdapter();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
            }
        }
    }

    public class ReviewQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            String mResult = null;
            try {
                mResult = NetworkUtils.getResponseFromHttpURL(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return mResult;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null && !s.equals("")) {
                try {
                    reviews = JsonUtils.parseReviewsJson(s);
                    setReviewAdapter();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
            }
        }
    }
}
