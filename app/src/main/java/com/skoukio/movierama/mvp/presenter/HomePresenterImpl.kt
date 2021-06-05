package com.skoukio.movierama.mvp.presenter

import com.skoukio.movierama.mvp.interactor.HomeInteractor
import com.skoukio.movierama.mvp.view.HomeView
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

    private fun getView(): HomeView? {
        return viewRef.get()
    }
}