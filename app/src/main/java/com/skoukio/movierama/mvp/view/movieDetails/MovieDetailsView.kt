package com.skoukio.movierama.mvp.view.movieDetails

import com.skoukio.movierama.models.data.movieDetails.MovieCreditsModel
import com.skoukio.movierama.models.data.movieDetails.MovieReviewsModel
import com.skoukio.movierama.models.data.movieDetails.SimilarMoviesModel

interface MovieDetailsView {
    fun showMovieCast(movieCredits: MovieCreditsModel)
    fun showSimilarMoviesData(similarMovies: List<SimilarMoviesModel>)
    fun showMovieReviewsData(movieReviews: List<MovieReviewsModel>)
}