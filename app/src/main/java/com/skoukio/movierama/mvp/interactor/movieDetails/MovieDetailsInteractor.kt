package com.skoukio.movierama.mvp.interactor.movieDetails

import com.skoukio.movierama.models.common.DataResult
import com.skoukio.movierama.models.data.home.MovieModel
import com.skoukio.movierama.models.data.movieDetails.MovieCreditsModel
import com.skoukio.movierama.models.data.movieDetails.MovieReviewsResponseModel
import com.skoukio.movierama.models.data.movieDetails.SimilarMoviesResponseModel

interface MovieDetailsInteractor {

    suspend fun getMovieReviews(movieId: Int): DataResult<MovieReviewsResponseModel>
    suspend fun getSimilarMovies(movieId: Int): DataResult<SimilarMoviesResponseModel>
    suspend fun getMovieCredits(movieId: Int): DataResult<MovieCreditsModel>
    fun toggleFavorite(movie: MovieModel)
}