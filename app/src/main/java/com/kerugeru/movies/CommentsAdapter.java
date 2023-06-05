package com.kerugeru.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>{
    private static final String TYPE_POSITIVE = "Позитивный";
    private static final String TYPE_NEUTRAL = "Нейтральный";

    private List<Comments> commentsList = new ArrayList<>();

    public void setCommentsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_elements,
                parent,false);
        return new CommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder holder, int position) {
        Comments comments = commentsList.get(position);
        holder.textCommentationName.setText(comments.getAuthor());
        holder.textComment.setText(comments.getComments());
        String type =comments.getType();
        int colorRestId = android.R.color.holo_red_light;
        switch (type){
            case TYPE_POSITIVE:
                colorRestId= android.R.color.holo_green_light;
                break;
            case TYPE_NEUTRAL:
                colorRestId = android.R.color.holo_blue_light;
                break;
        }
        int color = ContextCompat.getColor(holder.itemView.getContext(),colorRestId);
        holder.linearComments.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }


    static class CommentsViewHolder extends RecyclerView.ViewHolder{
private final TextView  textCommentationName;
private final TextView textComment;
private final LinearLayout linearComments;
        public CommentsViewHolder(@NonNull View itemView) {
            super(itemView);
            textCommentationName=itemView.findViewById(R.id.textCommentationName);
            textComment=itemView.findViewById(R.id.textComment);
            linearComments=itemView.findViewById(R.id.linearComments);
        }
    }
}
