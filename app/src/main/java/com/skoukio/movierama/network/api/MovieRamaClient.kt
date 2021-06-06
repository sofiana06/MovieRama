package com.skoukio.movierama.network.api

import com.skoukio.movierama.network.api.factory.RetrofitFactory
import com.skoukio.movierama.network.parsers.response.home.MoviesResponse
import com.skoukio.movierama.network.parsers.response.movieDetails.MovieCreditsResponse
import com.skoukio.movierama.network.parsers.response.movieDetails.MovieReviewsResponse
import com.skoukio.movierama.network.parsers.response.movieDetails.SimilarMoviesResponse
import com.skoukio.movierama.network.providers.NetworkProvider
import kotlinx.coroutines.Deferred

class MovieRamaClient : NetworkProvider {

    private val movieRamaApi: MovieRamaApi = RetrofitFactory().buildMovieRamaApi()


    override fun getPopularMoviesAsync(page: Int): Deferred<MoviesResponse> {
        return movieRamaApi.getPopularMoviesAsync(page)
    }

    override fun getSearchResultsMoviesAsync(query: String): Deferred<MoviesResponse> {
        return movieRamaApi.getSearchResultsMoviesAsync(query)
    }

    override fun getMoviesReviewsAsync(movieId: Int): Deferred<MovieReviewsResponse> {
        return movieRamaApi.getMoviesReviewsAsync(movieId)
    }

    override fun getSimilarMoviesAsync(movieId: Int): Deferred<SimilarMoviesResponse> {
        return movieRamaApi.getSimilarMoviesAsync(movieId)
    }

    override fun getMovieCreditsAsync(movieId: Int): Deferred<MovieCreditsResponse> {
        return movieRamaApi.getMovieCreditsAsync(movieId, "credits")
    }
}