package com.skoukio.movierama.mvp.presenter.movieDetails

import com.skoukio.movierama.mvp.interactor.movieDetails.MovieDetailsInteractor
import com.skoukio.movierama.mvp.view.movieDetails.MovieDetailsView
import kotlinx.coroutines.*
import java.lang.ref.WeakReference

class MovieDetailsPresenterImpl(
    view: MovieDetailsView,
    private val interactor: MovieDetailsInteractor
) : MovieDetailsPresenter {

    private val viewRef = WeakReference(view)

    private var uiDispatcher: CoroutineDispatcher = Dispatchers.Main
    private var bgDispatcher: CoroutineDispatcher = Dispatchers.IO

    private var job = SupervisorJob()
    private var uiScope = CoroutineScope(Dispatchers.Main + job)

    override fun detach() {
        uiScope.coroutineContext.cancelChildren()
    }

    private fun getView(): MovieDetailsView? {
        return viewRef.get()
    }
}