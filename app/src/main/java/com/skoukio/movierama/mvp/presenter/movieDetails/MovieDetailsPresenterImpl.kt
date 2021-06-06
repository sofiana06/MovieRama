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

    override fun getMovieCredits(movieId: Int) {
        uiScope.launch {
            val movieCreditsDataResult = interactor.getMovieCredits(movieId)
            val movieCastData = movieCreditsDataResult.data
            if (movieCastData != null) {
                getView()?.showMovieCast(movieCastData)
            }
        }
    }

    override fun getSimilarMovies(movieId: Int) {
        uiScope.launch {
            val similarMoviesDataResult = interactor.getSimilarMovies(movieId)
            val similarMoviesData = similarMoviesDataResult.data?.results
            if (similarMoviesData != null) {
                getView()?.showSimilarMoviesData(similarMoviesData)
            }
        }
    }

    override fun getMovieReviews(movieId: Int) {
        uiScope.launch {
            val movieReviewsDataResult = interactor.getMovieReviews(movieId)
            val movieReviewsData = movieReviewsDataResult.data?.results
            if (movieReviewsData != null) {
                getView()?.showMovieReviewsData(movieReviewsData)
            }
        }
    }

    private fun getView(): MovieDetailsView? {
        return viewRef.get()
    }
}