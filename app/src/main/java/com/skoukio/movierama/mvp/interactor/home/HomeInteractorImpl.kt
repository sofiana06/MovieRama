package com.skoukio.movierama.mvp.interactor.home

import com.skoukio.movierama.models.common.DataResult
import com.skoukio.movierama.models.data.home.MoviesResponseModel
import com.skoukio.movierama.models.data.home.toModel
import com.skoukio.movierama.network.providers.NetworkProvider
import timber.log.Timber

class HomeInteractorImpl(private val networkProvider: NetworkProvider) : HomeInteractor {

    override suspend fun getPopularMovies(page: Int): DataResult<MoviesResponseModel> {
        return try {
            val popularMoviesResponse = networkProvider.getPopularMoviesAsync(page).await()
            DataResult(popularMoviesResponse.toModel())
        } catch (t: Throwable) {
            Timber.d(t)
            DataResult(throwable = t)
        }
    }

    override suspend fun getSearchResultsMovies(query: String): DataResult<MoviesResponseModel> {
        TODO("Not yet implemented")
    }
}