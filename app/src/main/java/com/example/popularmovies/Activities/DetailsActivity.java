package com.example.popularmovies.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.popularmovies.Movies;
import com.example.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.sql.Array;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    ImageView mImageView;
    TextView mTitleTextView;
    TextView mRatingTextView;
    TextView mPlotTextView;
    TextView mDateTextView;
    ImageView mFavorite;
    ListView trailerListView;
    ListView reviewsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        mImageView = findViewById(R.id.mPoster);
        mTitleTextView = findViewById(R.id.mTitle);
        mRatingTextView = findViewById(R.id.mRating);
        mPlotTextView = findViewById(R.id.mPlot);
        mDateTextView = findViewById(R.id.mDate);
        mFavorite = findViewById(R.id.fav);
        //if(mFavorite.getResources().getDrawable() == R.drawable.ic_unfav)
        mFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFavorite.setImageResource(R.drawable.ic_fav);
                //Todo: add movie data to the DB
            }
        });

        final Intent intent = getIntent();

        Movies movies = intent.getParcelableExtra("Movies");

        Array trailers = movies.getmTrailers();
        ArrayAdapter<String> trailerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, (List<String>) trailers);

        trailerListView = findViewById(R.id.trailer_list_view);
        trailerListView.setAdapter(trailerAdapter);
        trailerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentYT = new Intent(Intent.ACTION_VIEW);
                //intentYT.setData(Uri.parse("https://www.youtube.com/watch?v="+trailerKey));
                startActivity(intentYT);
            }
        });
        reviewsListView = findViewById(R.id.reviews_list_view);

        mTitleTextView.setText(movies.getmTitle());

        mPlotTextView.setText(movies.getmPlot());
        mRatingTextView.setText(Double.toString(movies.getmRating()));
        mDateTextView.setText(movies.getmDate());
        Picasso.get()
                .load(movies.getmImage())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(mImageView);


    }
}
