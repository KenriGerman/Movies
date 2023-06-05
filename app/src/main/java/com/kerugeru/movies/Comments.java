package com.kerugeru.movies;

import com.google.gson.annotations.SerializedName;

public class Comments {

        @SerializedName("author")
        private String author;
        @SerializedName("review")
        private String comments;
        @SerializedName("type")
        private String type;

    public Comments(String author, String comments, String type) {
        this.author = author;
        this.comments = comments;
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public String getComments() {
        return comments;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "author='" + author + '\'' +
                ", comments='" + comments + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

