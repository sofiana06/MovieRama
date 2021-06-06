package com.skoukio.movierama.mvp.view.home

import com.skoukio.movierama.models.data.home.MovieModel

interface HomeView {
    fun showPopularMovies(popularMovies: List<MovieModel>)
    fun showLoading()
    fun showFetching()
    fun showError()
    fun clearPreviousMovies()
}