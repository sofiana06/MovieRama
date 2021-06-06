package com.skoukio.movierama.mvp.interactor.home

import com.skoukio.movierama.models.common.DataResult
import com.skoukio.movierama.models.data.home.MovieModel
import com.skoukio.movierama.models.data.home.MoviesResponseModel

interface HomeInteractor {
    suspend fun getPopularMovies(page: Int): DataResult<MoviesResponseModel>
    fun setSearchText(searchText: String?)
    fun toggleFavorite(movie: MovieModel)
}