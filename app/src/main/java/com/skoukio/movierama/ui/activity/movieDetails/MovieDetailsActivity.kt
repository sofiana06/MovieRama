package com.skoukio.movierama.ui.activity.movieDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.skoukio.movierama.R
import com.skoukio.movierama.common.DefinitionsApi
import com.skoukio.movierama.common.application.MovieRamaApplication
import com.skoukio.movierama.models.data.home.MovieModel
import com.skoukio.movierama.mvp.interactor.movieDetails.MovieDetailsInteractorImpl
import com.skoukio.movierama.mvp.presenter.movieDetails.MovieDetailsPresenter
import com.skoukio.movierama.mvp.presenter.movieDetails.MovieDetailsPresenterImpl
import com.skoukio.movierama.mvp.view.movieDetails.MovieDetailsView
import com.skoukio.movierama.ui.adapter.movieDetails.SimilarMoviesRecyclerViewAdapter
import com.skoukio.movierama.ui.custom.itemDecoration.BottomTopDividerItemDecoration
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity(), MovieDetailsView {

    private lateinit var similarMoviesRecyclerViewAdapter: SimilarMoviesRecyclerViewAdapter
    private lateinit var movieDetailsPresenter: MovieDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        movieDetailsPresenter = MovieDetailsPresenterImpl(
            view = this,
            interactor = MovieDetailsInteractorImpl(
                networkProvider = MovieRamaApplication.get().networkProvider
            )
        )
        initResources()
        initLayout()
    }

    private fun initResources() {
        val movie = intent?.extras?.getParcelable<MovieModel>(DefinitionsApi.BUNDLE.MOVIE)
    }

    private fun initLayout() {
        similarMoviesRecyclerView?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        similarMoviesRecyclerView?.adapter = similarMoviesRecyclerViewAdapter
        val topBottomMargin =
            resources?.getDimensionPixelSize(R.dimen.common_ten_dp) ?: 0
        val betweenMargin =
            resources?.getDimensionPixelSize(R.dimen.common_ten_dp) ?: 0
        similarMoviesRecyclerView?.layoutManager = LinearLayoutManager(this)
        similarMoviesRecyclerView?.itemAnimator = null
        similarMoviesRecyclerView?.addItemDecoration(
            BottomTopDividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL,
                topBottomMargin,
                topBottomMargin,
                betweenMargin
            )
        )
    }
}