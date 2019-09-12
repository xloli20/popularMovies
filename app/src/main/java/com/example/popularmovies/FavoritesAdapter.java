package com.example.popularmovies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.popularmovies.Models.Movies;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {

    private static int viewHolderCount;
    private ArrayList<Movies> mFavorites;


    public FavoritesAdapter(ArrayList<Movies> movies) {
        mFavorites = movies;
        viewHolderCount = 0;
    }

    @NonNull
    @Override
    public FavoritesAdapter.FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_items, viewGroup, false);
        return new FavoritesAdapter.FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesAdapter.FavoritesViewHolder favoritesViewHolder, int i) {
        //FavoritesViewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        return mFavorites.size();
    }


    class FavoritesViewHolder extends RecyclerView.ViewHolder {
        ImageView mPosterImageView;
        TextView mTitleTextView;

        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);

            mPosterImageView = itemView.findViewById(R.id.mPosterItem);
            mTitleTextView = itemView.findViewById(R.id.mTitle);
        }

        void bind(int position) {
            Picasso.get()
                    .load(mFavorites.get(position).getmImage())
                    .placeholder(R.drawable.laoding)
                    .error(R.drawable.laoding)
                    .into(mPosterImageView);
            mTitleTextView.setText(mFavorites.get(position).getmTitle());
        }
    }

}