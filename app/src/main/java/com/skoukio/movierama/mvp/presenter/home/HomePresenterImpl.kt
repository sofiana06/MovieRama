package com.skoukio.movierama.mvp.presenter.home

import com.skoukio.movierama.models.data.home.MovieModel
import com.skoukio.movierama.mvp.interactor.home.HomeInteractor
import com.skoukio.movierama.mvp.view.home.HomeView
import kotlinx.coroutines.*
import java.lang.ref.WeakReference

class HomePresenterImpl(view: HomeView, private val interactor: HomeInteractor) : HomePresenter {

    private val viewRef = WeakReference(view)

    private var bgDispatcher: CoroutineDispatcher = Dispatchers.IO

    private var job = SupervisorJob()
    private var uiScope = CoroutineScope(Dispatchers.Main + job)

    private var pageIndex: Int = 0
    private var isFetching: Boolean = false
    private var searchText: String? = null

    override fun detach() {
        uiScope.coroutineContext.cancelChildren()
    }

    override fun getPopularMovies() {
        fetchMovies(searchText = null, ignoreSearchText = true)
    }

    override fun resetPagination() {
        pageIndex = 0
        setFetching(false)
    }

    override fun filterMovies(searchText: String?) {
        resetPagination()
        getView()?.clearPreviousMovies()
        fetchMovies(searchText)
    }

    override fun toggleFavorite(movie: MovieModel) {
        interactor.toggleFavorite(movie)
    }

    private fun fetchMovies(searchText: String?, ignoreSearchText: Boolean = false) {
        uiScope.launch {
            if (isFetching()) {
                return@launch
            }
            setFetching(true)
            pageIndex++
            if (pageIndex == 0) {
                getView()?.showLoading()
            } else {
                getView()?.showFetching()
            }
            if (!ignoreSearchText) {
                interactor.setSearchText(searchText)
            }
            val popularMoviesDataResult =
                withContext(bgDispatcher) { interactor.getPopularMovies(pageIndex) }
            val popularMoviesData = popularMoviesDataResult.data?.results
            if (searchText != null) {
                getView()?.clearPreviousMovies()
            }
            if (popularMoviesData != null) {
                getView()?.showPopularMovies(popularMoviesData)
            } else {
                getView()?.showPopularMovies(emptyList())
                getView()?.showError()
            }
            setFetching(false)
        }
    }

    private fun getView(): HomeView? {
        return viewRef.get()
    }

    @Synchronized
    private fun setFetching(isFetching: Boolean) {
        this.isFetching = isFetching
    }

    @Synchronized
    private fun isFetching(): Boolean {
        return this.isFetching
    }
}