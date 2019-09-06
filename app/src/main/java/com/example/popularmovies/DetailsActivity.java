package com.example.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    ImageView mImageView;
    TextView mTitleTextView;
    TextView mRatingTextView;
    TextView mPlotTextView;
    TextView mDateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mImageView = findViewById(R.id.mPoster);
        mTitleTextView = findViewById(R.id.mTitle);
        mRatingTextView = findViewById(R.id.mRating);
        mPlotTextView = findViewById(R.id.mPlot);
        mDateTextView = findViewById(R.id.mDate);


        Intent intent = getIntent();

        Movies movies = intent.getParcelableExtra("Movies");

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
