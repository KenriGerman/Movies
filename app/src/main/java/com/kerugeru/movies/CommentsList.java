package com.kerugeru.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentsList {
    @SerializedName("docs")
    private List<Comments> commentsList;

    public CommentsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }

    public List<Comments> getCommentsList() {
        return commentsList;
    }

    @Override
    public String toString() {
        return "CommentsList{" +
                "commentsList=" + commentsList +
                '}';
    }
}
