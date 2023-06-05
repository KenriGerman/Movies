package com.kerugeru.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {

    // @Headers({"Accept: application/json", "X-API-KEY: 4DBCCS3-XPY4DPX-PP0HXM0-SHYAPW7"})

    // @GET("movie?search=7-10&sortField=votes.kp&sortType=-1&limit=10")
    @GET("movie?token=4DBCCS3-XPY4DPX-PP0HXM0-SHYAPW7&field=rating.kp&search=7-10&sortField=votes.kp&sortType=-1&limit=30")
    Single<MovieResponse> loadMovies(@Query("page") int page);

    @GET("movie?token=4DBCCS3-XPY4DPX-PP0HXM0-SHYAPW7&field=id")
    Single<TrailerResponse> loadTrailer(@Query("search") int id);

    @GET("review?token=4DBCCS3-XPY4DPX-PP0HXM0-SHYAPW7&&field=movieId")
    Single<CommentsList> loadCommets(@Query("search") int id);

    @GET("review?token=4DBCCS3-XPY4DPX-PP0HXM0-SHYAPW7&&field=movieId")
    Single<ReviewResponse> loadReviews(@Query("search") int id);

}
