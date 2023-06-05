package com.kerugeru.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {
private TextView textViewTitle;
private TextView textViewYear;
private TextView textViewDescription;
private ImageView imageViewPoster;
private MovieDetailViewModel viewModel;
private RecyclerView rcTrailers;
private RecyclerView recyclerViewReviews;
private ReviewsAdapter reviewsAdapter;
private TrailersAdapter trailersAdapter;


private RecyclerView rcComment;
private CommentsAdapter commentsAdapter;

private static final String EXTRA_MOVIE = "Movie";
private static final String TAG = "MovieDetailActivity";
private static final String TAG2 = "MovieActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        viewModel = new  ViewModelProvider(this).get(MovieDetailViewModel.class);
        initView();
        trailersAdapter = new TrailersAdapter();
        rcTrailers.setAdapter(trailersAdapter);

//
        reviewsAdapter = new ReviewsAdapter();
        recyclerViewReviews.setAdapter(reviewsAdapter);
//

        //
        commentsAdapter = new CommentsAdapter();
        rcComment.setAdapter(commentsAdapter);
//

        Movie movie =(Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        Glide.with(this)
                .load(movie.getPoster().getUrl())
                .into(imageViewPoster);
        textViewTitle.setText(movie.getName());
        textViewYear.setText(String.valueOf(movie.getYesr()));
        textViewDescription.setText(movie.getDescription());
        viewModel.loadTrailers(movie.getId());






viewModel.getTrailers().observe(this, new Observer<List<Trailer>>() {
    @Override
    public void onChanged(List<Trailer> trailers) {
        trailersAdapter.setTrailers(trailers);
    }
});

trailersAdapter.setOnTrailerClickListener(new TrailersAdapter.OnTrailerClickListener() {
    @Override
    public void onTrailerClick(Trailer trailer) {
    Intent intent= new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse(trailer.getUrl()));
    startActivity(intent);
    }
});




        viewModel.loadComments(movie.getId());//тут тоже все ок
        viewModel.getComments().observe(this, new Observer<List<Comments>>() {
            @Override
            public void onChanged(List<Comments> comments) {
                commentsAdapter.setCommentsList(comments);
            }
        });//тут тоже все ок


viewModel.getRewiews().observe(this,new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviewList) {
                reviewsAdapter.setReviews(reviewList);
                Log.d(TAG,reviewList.toString());
            }
        });
        viewModel.loadReviews(movie.getId());









    }



    private void initView(){
        rcTrailers=findViewById(R.id.rcTrailers);
          textViewTitle= findViewById(R.id.textViewTitle);
          textViewYear= findViewById(R.id.textViewYear);
          textViewDescription= findViewById(R.id.textViewDescription);
          imageViewPoster= findViewById(R.id.imageViewPoster);
          rcComment=findViewById(R.id.rcComment);
          recyclerViewReviews = findViewById(R.id.recyclerViewReviews);

    }

    public static Intent newIntent (Context context,Movie movie){
        Intent intent = new Intent(context,MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE,movie);
        return intent;
    }

}