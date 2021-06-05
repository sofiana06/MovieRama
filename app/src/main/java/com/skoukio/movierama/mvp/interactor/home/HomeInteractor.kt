package com.skoukio.movierama.mvp.interactor.home

import com.skoukio.movierama.models.common.DataResult
import com.skoukio.movierama.models.data.home.MoviesResponseModel

interface HomeInteractor {

    suspend fun getPopularMovies(page: Int): DataResult<MoviesResponseModel>
    suspend fun getSearchResultsMovies(query: String): DataResult<MoviesResponseModel>
}