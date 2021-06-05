package com.skoukio.movierama.mvp.interactor.movieDetails

import com.skoukio.movierama.models.common.DataResult
import com.skoukio.movierama.network.parsers.response.movieDetails.MovieCreditsResponse
import com.skoukio.movierama.network.parsers.response.movieDetails.MovieReviewsResponse
import com.skoukio.movierama.network.parsers.response.movieDetails.SimilarMoviesResponse
import com.skoukio.movierama.network.providers.NetworkProvider

class MovieDetailsInteractorImpl(private val networkProvider: NetworkProvider) : MovieDetailsInteractor{

    override suspend fun getMoviesReviews(movieId: Int): DataResult<MovieReviewsResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getSimilarMovies(movieId: String): DataResult<SimilarMoviesResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieCredits(movieId: String): DataResult<MovieCreditsResponse> {
        TODO("Not yet implemented")
    }
}