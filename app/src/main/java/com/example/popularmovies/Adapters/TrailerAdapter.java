package com.example.popularmovies.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.popularmovies.Models.Trailers;
import com.example.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {


    private static int viewHolderCount;
    final private ListItemClickListener mOnClickListener;
    private ArrayList<Trailers> trailers;

    public TrailerAdapter(ArrayList<Trailers> trailers, ListItemClickListener listener) {
        this.trailers = trailers;
        mOnClickListener = listener;
        viewHolderCount = 0;
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trailers_items, viewGroup, false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerAdapter.TrailerViewHolder trailerViewHolder, int i) {
        trailerViewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final String TAG = TrailerViewHolder.class.getSimpleName();
        ImageView tImageView;

        public TrailerViewHolder(@NonNull View itemView) {
            super(itemView);

            tImageView = itemView.findViewById(R.id.trailer);
            itemView.setOnClickListener(this);
        }

        void bind(int position) {

            Picasso.get()
                    .load("https://img.youtube.com/vi/" + trailers.get(position).gettKey() + "/0.jpg")
                    .placeholder(R.drawable.ic_play)
                    .into(tImageView);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}

