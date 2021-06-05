package com.skoukio.movierama.mvp.presenter.home

interface HomePresenter {

    fun detach()
    fun getPopularMovies()
    fun resetPagination()
}