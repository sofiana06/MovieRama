package com.skoukio.movierama.mvp.presenter.movieDetails

import com.skoukio.movierama.models.data.home.MovieModel

interface MovieDetailsPresenter {
    fun detach()
    fun getMovieCredits(movieId: Int)
    fun getSimilarMovies(movieId: Int)
    fun getMovieReviews(movieId: Int)
    fun toggleFavorite(movie: MovieModel)
}