package com.skoukio.movierama.mvp.interactor.home

import com.skoukio.movierama.models.common.DataResult
import com.skoukio.movierama.models.data.home.MovieModel
import com.skoukio.movierama.models.data.home.MoviesResponseModel
import com.skoukio.movierama.models.data.home.toModel
import com.skoukio.movierama.network.providers.NetworkProvider
import com.skoukio.movierama.network.providers.sharedPreferences.SharedPreferencesProvider
import timber.log.Timber

class HomeInteractorImpl(
    private val networkProvider: NetworkProvider,
    private val sharedPreferencesProvider: SharedPreferencesProvider
) : HomeInteractor {

    private var searchText: String? = null

    override suspend fun getPopularMovies(page: Int): DataResult<MoviesResponseModel> {
        return try {
            val searchText = searchText
            val popularMoviesResponse = if (searchText == null) {
                networkProvider.getPopularMoviesAsync(page).await()
            } else {
                networkProvider.getSearchResultsMoviesAsync(searchText, page).await()
            }
            val data = popularMoviesResponse.toModel()
            DataResult(data.copy(results = data.results?.map {
                it.copy(
                    isFavorite = sharedPreferencesProvider.isFavoriteMovie(
                        it.id
                    )
                )
            }))
        } catch (t: Throwable) {
            Timber.d(t)
            DataResult(throwable = t)
        }
    }

    override fun setSearchText(searchText: String?) {
        this.searchText = searchText
    }

    override fun toggleFavorite(movie: MovieModel) {
        if (movie.id == null) {
            return
        }
        sharedPreferencesProvider.setFavoriteMovie(
            movieId = movie.id,
            isFavorite = !movie.isFavorite
        )
    }
}