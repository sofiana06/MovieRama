package com.skoukio.movierama.network.providers

import com.skoukio.movierama.network.parsers.response.home.MoviesResponse
import com.skoukio.movierama.network.parsers.response.movieDetails.MovieCreditsResponse
import com.skoukio.movierama.network.parsers.response.movieDetails.MovieReviewsResponse
import com.skoukio.movierama.network.parsers.response.movieDetails.SimilarMoviesResponse
import kotlinx.coroutines.Deferred

interface NetworkProvider {
    fun getPopularMoviesAsync(page: Int): Deferred<MoviesResponse>
    fun getSearchResultsMoviesAsync(query: String): Deferred<MoviesResponse>
    fun getMoviesReviewsAsync(movieId: Int): Deferred<MovieReviewsResponse>
    fun getSimilarMoviesAsync(movieId: Int): Deferred<SimilarMoviesResponse>
    fun getMovieCreditsAsync(movieId: Int): Deferred<MovieCreditsResponse>
}