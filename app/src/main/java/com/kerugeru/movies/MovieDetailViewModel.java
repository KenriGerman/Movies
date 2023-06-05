package com.kerugeru.movies;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailViewModel extends AndroidViewModel {
        public static final String TAG = "MovieDetailViewModel";
        private final CompositeDisposable compositeDisposable =new CompositeDisposable();
    private final MutableLiveData<List<Trailer>> trailers = new MutableLiveData<>();


    private final MutableLiveData<List<Review>> rewiews = new MutableLiveData<>();
    private final MutableLiveData<List<Comments>> comments = new MutableLiveData<>();


    public LiveData<List<Review>> getRewiews() {
        return rewiews;
    }

    public LiveData<List<Comments>> getComments() {
        return comments;
    }




    public LiveData<List<Trailer>> getTrailers() {
        return trailers;
    }

    public MovieDetailViewModel(@NonNull Application application) {
        super(application);
    }

    public void loadReviews(int id){
    Disposable disposable =    ApiFactory.apiService.loadReviews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
            .map(new Function<ReviewResponse, List<Review>>() {
                @Override
                public List<Review> apply(ReviewResponse reviewResponse) throws Throwable {
                    return reviewResponse.getReviews();
                }
            })
                .subscribe(new Consumer<List<Review>>() {
                    @Override
                    public void accept(List<Review> reviewList) throws Throwable {
                    //   rewiews.setValue(reviewResponse.getReviews());
                        rewiews.setValue(reviewList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG,throwable.toString());
                    }
                });

    }

    public void loadComments(int id) {//тут все работает
        Disposable disposable = ApiFactory.apiService.loadCommets(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<CommentsList, List<Comments>>() {
                    @Override
                    public List<Comments> apply(CommentsList commentsListener) throws Throwable {
                        return commentsListener.getCommentsList();
                    }
                })
                .subscribe(new Consumer<List<Comments>>() {
                    @Override
                    public void accept(List<Comments> commentsLister) throws Throwable {
                        comments.setValue(commentsLister);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }






    public void loadTrailers (int id){
      Disposable disposable = ApiFactory.apiService.loadTrailer(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
              .map(new Function<TrailerResponse, List<Trailer>>() {
                  @Override
                  public List<Trailer> apply(TrailerResponse trailerResponse) throws Throwable {
                      return trailerResponse.getTrailersList().getTrailers();
                  }
              })
                .subscribe(new Consumer<List<Trailer>>() {
                    @Override
                    public void accept(List<Trailer> trailersList) throws Throwable {
                        trailers.setValue(trailersList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());
                    }
                });
      compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
