package com.skoukio.movierama.mvp.presenter.home

import com.skoukio.movierama.models.data.home.MovieModel

interface HomePresenter {
    fun detach()
    fun getPopularMovies()
    fun resetPagination()
    fun filterMovies(searchText: String?)
    fun toggleFavorite(movie: MovieModel)
}