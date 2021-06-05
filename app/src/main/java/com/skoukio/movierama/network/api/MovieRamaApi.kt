package com.skoukio.movierama.network.api

import com.skoukio.movierama.network.parsers.response.home.MoviesResponse
import com.skoukio.movierama.network.parsers.response.movieDetails.MovieCreditsResponse
import com.skoukio.movierama.network.parsers.response.movieDetails.MovieReviewsResponse
import com.skoukio.movierama.network.parsers.response.movieDetails.SimilarMoviesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieRamaApi {

    @GET("movie/popular")
    fun getPopularMoviesAsync(
        @Query("page") page: Int
    ): Deferred<MoviesResponse>

    @GET("search/movie")
    fun getSearchResultsMoviesAsync(
        @Query("query") query: String
    ): Deferred<MoviesResponse>

    @GET("movie/{movieId}/reviews")
    fun getMoviesReviewsAsync(
        @Query("movie_id") movieId: Int
    ): Deferred<MovieReviewsResponse>

    @GET("movie/{movieId}/similar")
    fun getSimilarMoviesAsync(
        @Query("movie_id") movieId: Int
    ): Deferred<SimilarMoviesResponse>

    @GET("movie/{movieId}/credits")
    fun getMovieCreditsAsync(
        @Query("movie_id") movieId: Int
    ): Deferred<MovieCreditsResponse>
}