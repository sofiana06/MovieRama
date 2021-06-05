package com.skoukio.movierama.mvp.interactor.movieDetails

import com.skoukio.movierama.models.common.DataResult
import com.skoukio.movierama.network.parsers.response.movieDetails.MovieCreditsResponse
import com.skoukio.movierama.network.parsers.response.movieDetails.MovieReviewsResponse
import com.skoukio.movierama.network.parsers.response.movieDetails.SimilarMoviesResponse

interface MovieDetailsInteractor {

    suspend fun getMoviesReviews(movieId: Int): DataResult<MovieReviewsResponse>
    suspend fun getSimilarMovies(movieId: String): DataResult<SimilarMoviesResponse>
    suspend fun getMovieCredits(movieId: String): DataResult<MovieCreditsResponse>
}