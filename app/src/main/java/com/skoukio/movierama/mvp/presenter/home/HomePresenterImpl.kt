package com.skoukio.movierama.mvp.presenter.home

import com.skoukio.movierama.mvp.interactor.home.HomeInteractor
import com.skoukio.movierama.mvp.view.home.HomeView
import kotlinx.coroutines.*
import java.lang.ref.WeakReference

class HomePresenterImpl(view: HomeView, private val interactor: HomeInteractor) : HomePresenter {

    private val viewRef = WeakReference(view)

    private var uiDispatcher: CoroutineDispatcher = Dispatchers.Main
    private var bgDispatcher: CoroutineDispatcher = Dispatchers.IO

    private var job = SupervisorJob()
    private var uiScope = CoroutineScope(Dispatchers.Main + job)

    override fun detach() {
        uiScope.coroutineContext.cancelChildren()
    }

    override fun getPopularMovies() {
        uiScope.launch {
            val popularMoviesDataResult = interactor.getPopularMovies(1)
            val popularMoviesData= popularMoviesDataResult.data?.results
            if (popularMoviesData != null) {
                getView()?.showPopularMovies(popularMoviesData)
            }
        }
    }

    override fun resetPagination() {
        TODO("Not yet implemented")
    }

    private fun getView(): HomeView? {
        return viewRef.get()
    }
}