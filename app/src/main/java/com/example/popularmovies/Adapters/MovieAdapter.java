package com.example.popularmovies.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.popularmovies.Models.Movies;
import com.example.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final String TAG = MovieAdapter.class.getSimpleName();

    Context context;
    private ArrayList<Movies> mMovies;
    private static int viewHolderCount;
    Movies movie;


    public MovieAdapter(ArrayList<Movies> movies, ListItemClickListener listener) {
        mMovies = movies;
        mOnClickListener = listener;
        viewHolderCount = 0;
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    final private ListItemClickListener mOnClickListener;

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_items, viewGroup, false);
        return new MovieViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder movieViewHolder, int i) {
        movieViewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mPosterImageView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            mPosterImageView = itemView.findViewById(R.id.mPosterItem);
            itemView.setOnClickListener(this);
        }

        void bind(int position) {
            Log.d(TAG, "bind: mMovies.get(position).getmImage()" + mMovies.get(position).getmImage());
            Picasso.get()
                    .load(mMovies.get(position).getmImage())
                    .placeholder(R.drawable.laoding)
                    .error(R.drawable.laoding)
                    .into(mPosterImageView);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
