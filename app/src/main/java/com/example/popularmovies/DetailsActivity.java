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
        mTitleTextView.setText(intent.getStringExtra("mtitle"));
        mPlotTextView.setText(intent.getStringExtra("mPlot"));
        mRatingTextView.setText(intent.getStringExtra("mRating"));
        mDateTextView.setText(intent.getStringExtra("mDate"));
        Picasso.get()
                .load(intent.getStringExtra("mPoster"))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(mImageView);
    }
}
