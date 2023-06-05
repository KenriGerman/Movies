package com.kerugeru.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailerViewHolder> {
    private List<Trailer> trailers = new ArrayList<>();

    private OnTrailerClickListener onTrailerClickListener;





    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailers_ellements,parent,false);

        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
    Trailer trailer = trailers.get(position);
    holder.trailerTextName.setText(trailer.getName());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (onTrailerClickListener!= null){
                onTrailerClickListener.onTrailerClick(trailer);
            }
        }
    });

    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }









interface OnTrailerClickListener {
        void onTrailerClick(Trailer trailer);
}

    public void setOnTrailerClickListener(OnTrailerClickListener onTrailerClickListener) {
        this.onTrailerClickListener = onTrailerClickListener;
    }














    static class TrailerViewHolder extends RecyclerView.ViewHolder{
        private final TextView trailerTextName;
        public TrailerViewHolder(@NonNull View itemView) {
            super(itemView);
            trailerTextName=itemView.findViewById(R.id.trailerTextName);


        }
    }
}
