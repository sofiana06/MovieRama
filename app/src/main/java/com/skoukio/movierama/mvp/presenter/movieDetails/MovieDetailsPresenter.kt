package com.skoukio.movierama.mvp.presenter.movieDetails

interface MovieDetailsPresenter {

    fun detach()
    fun getMovieCredits(movieId: Int)
    fun getSimilarMovies(movieId: Int)
    fun getMovieReviews(movieId: Int)
}