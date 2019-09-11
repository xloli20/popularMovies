package com.example.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.popularmovies.Models.Reviews;

import java.util.ArrayList;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder> {


    private static int viewHolderCount;
    final private ListItemClickListener mOnClickListener;
    Context context;
    Reviews reviewsdata;
    private ArrayList<Reviews> reviews;

    public ReviewsAdapter(ArrayList<Reviews> Reviews, ListItemClickListener listener) {
        this.reviews = Reviews;
        mOnClickListener = listener;
        viewHolderCount = 0;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reviews_items, viewGroup, false);
        return new ReviewViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsAdapter.ReviewViewHolder reviewViewHolder, int i) {
        reviewViewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView authorTextView;
        TextView contentTextView;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);

            authorTextView = itemView.findViewById(R.id.author);
            contentTextView = itemView.findViewById(R.id.content);
            itemView.setOnClickListener(this);
        }

        void bind(int position) {
            authorTextView.setText(reviews.get(position).getrAuthor());
            contentTextView.setText(reviews.get(position).getrContent());

        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}

