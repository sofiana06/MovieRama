package com.skoukio.movierama.network.api

import com.skoukio.movierama.network.parsers.response.home.MoviesResponse
import com.skoukio.movierama.network.parsers.response.movieDetails.MovieCreditsResponse
import com.skoukio.movierama.network.parsers.response.movieDetails.MovieReviewsResponse
import com.skoukio.movierama.network.parsers.response.movieDetails.SimilarMoviesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieRamaApi {

    @GET("movie/popular")
    fun getPopularMoviesAsync(
        @Query("page") page: Int
    ): Deferred<MoviesResponse>

    @GET("search/movie")
    fun getSearchResultsMoviesAsync(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Deferred<MoviesResponse>

    @GET("movie/{movie_id}/reviews")
    fun getMoviesReviewsAsync(
        @Path("movie_id") movieId: Int
    ): Deferred<MovieReviewsResponse>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMoviesAsync(
        @Path("movie_id") movieId: Int
    ): Deferred<SimilarMoviesResponse>

    @GET("movie/{movie_id}")
    fun getMovieCreditsAsync(
        @Path("movie_id") movieId: Int,
        @Query("append_to_response") appendToResponse: String
    ): Deferred<MovieCreditsResponse>
}