package com.skoukio.movierama.mvp.view.home

import com.skoukio.movierama.models.data.home.MovieModel

interface HomeView {

    fun showPopularMovies(popularMovies : List<MovieModel>)
}