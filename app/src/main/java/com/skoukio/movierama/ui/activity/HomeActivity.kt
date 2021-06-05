package com.skoukio.movierama.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.skoukio.movierama.R
import com.skoukio.movierama.common.DefinitionsApi
import com.skoukio.movierama.common.application.MovieRamaApplication
import com.skoukio.movierama.mvp.interactor.HomeInteractorImpl
import com.skoukio.movierama.mvp.presenter.HomePresenter
import com.skoukio.movierama.mvp.presenter.HomePresenterImpl
import com.skoukio.movierama.mvp.view.HomeView
import com.skoukio.movierama.ui.adapter.PopularMoviesRecyclerViewAdapter
import com.skoukio.movierama.ui.custom.itemDecoration.BottomTopDividerItemDecoration
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener, HomeView {

    private lateinit var moviesRecyclerViewAdapter: PopularMoviesRecyclerViewAdapter
    private lateinit var homePresenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homePresenter = HomePresenterImpl(
            view = this,
            interactor = HomeInteractorImpl(
                networkProvider = MovieRamaApplication.get().networkProvider
            )
        )
        initResources()
        initLayout()
    }

    override fun onDestroy() {
        super.onDestroy()
        homePresenter.detach()
    }

    private fun initResources() {}
    private fun initLayout() {
        moviesRecyclerViewAdapter = PopularMoviesRecyclerViewAdapter { movie ->
            val movieDetailsIntent = Intent(this, MovieDetailsActivity::class.java)
            movieDetailsIntent.putExtra(DefinitionsApi.BUNDLE.MOVIE, movie)
            startActivity(movieDetailsIntent)
            overridePendingTransition(R.anim.animation_slide_in_right, R.anim.animation_zoom_out)
        }
        moviesRecyclerView?.layoutManager = LinearLayoutManager(this)
        moviesRecyclerView?.adapter = moviesRecyclerViewAdapter
        val topBottomMargin =
            resources?.getDimensionPixelSize(R.dimen.common_ten_dp) ?: 0
        val betweenMargin =
            resources?.getDimensionPixelSize(R.dimen.common_ten_dp) ?: 0
        moviesRecyclerView?.layoutManager = LinearLayoutManager(this)
        moviesRecyclerView?.itemAnimator = null
        moviesRecyclerView?.addItemDecoration(
            BottomTopDividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL,
                topBottomMargin,
                topBottomMargin,
                betweenMargin
            )
        )
        moviesSwipeRefreshLayout.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        TODO("Not yet implemented")
    }
}