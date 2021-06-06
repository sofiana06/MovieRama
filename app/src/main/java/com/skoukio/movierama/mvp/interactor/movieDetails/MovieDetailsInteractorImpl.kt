package com.skoukio.movierama.mvp.interactor.movieDetails

import com.skoukio.movierama.models.common.DataResult
import com.skoukio.movierama.models.data.movieDetails.MovieCreditsModel
import com.skoukio.movierama.models.data.movieDetails.MovieReviewsResponseModel
import com.skoukio.movierama.models.data.movieDetails.SimilarMoviesResponseModel
import com.skoukio.movierama.models.data.movieDetails.toModel
import com.skoukio.movierama.network.providers.NetworkProvider
import timber.log.Timber

class MovieDetailsInteractorImpl(private val networkProvider: NetworkProvider) :
    MovieDetailsInteractor {

    override suspend fun getMovieReviews(movieId: Int): DataResult<MovieReviewsResponseModel> {
        return try {
            val moviesReviewsResponse = networkProvider.getMoviesReviewsAsync(movieId).await()
            DataResult(moviesReviewsResponse.toModel())
        } catch (t: Throwable) {
            Timber.d(t)
            DataResult(throwable = t)
        }
    }

    override suspend fun getSimilarMovies(movieId: Int): DataResult<SimilarMoviesResponseModel> {
        return try {
            val similarReviewsResponse = networkProvider.getSimilarMoviesAsync(movieId).await()
            DataResult(similarReviewsResponse.toModel())
        } catch (t: Throwable) {
            Timber.d(t)
            DataResult(throwable = t)
        }
    }

    override suspend fun getMovieCredits(movieId: Int): DataResult<MovieCreditsModel> {
        return try {
            val moviesCreditsResponse = networkProvider.getMovieCreditsAsync(movieId).await()
            DataResult(moviesCreditsResponse.toModel())
        } catch (t: Throwable) {
            Timber.d(t)
            DataResult(throwable = t)
        }
    }
}