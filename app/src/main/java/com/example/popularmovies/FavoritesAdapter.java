package com.example.popularmovies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.popularmovies.Database.FavoritesMovies;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {

    private int viewHolderCount;
    private List<FavoritesMovies> mFavorites;


    public FavoritesAdapter(List<FavoritesMovies> movies) {
        mFavorites = movies;
        viewHolderCount = 0;
    }

    @NonNull
    @Override
    public FavoritesAdapter.FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fav_movie_items, viewGroup, false);
        return new FavoritesAdapter.FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder favoritesViewHolder, int i) {
        favoritesViewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        return mFavorites.size();
    }

    public void setmFavorites(List<FavoritesMovies> favoritesMovies) {
        mFavorites = favoritesMovies;
        notifyDataSetChanged();
    }

    class FavoritesViewHolder extends RecyclerView.ViewHolder {
        ImageView mPosterImageView;
        TextView mTitleTextView;
        TextView mDateTextView;

        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);

            mPosterImageView = itemView.findViewById(R.id.mPosterItem);
            mTitleTextView = itemView.findViewById(R.id.mTitle);
            mDateTextView = itemView.findViewById(R.id.mDate);
        }

        void bind(int position) {
            FavoritesMovies favoritesMovies = mFavorites.get(position);

            Picasso.get()
                    .load(favoritesMovies.getmImage())
                    .placeholder(R.drawable.laoding)
                    .error(R.drawable.laoding)
                    .into(mPosterImageView);
            mTitleTextView.setText(favoritesMovies.getmTitle());
            mDateTextView.setText(favoritesMovies.getmDate());
        }
    }

}