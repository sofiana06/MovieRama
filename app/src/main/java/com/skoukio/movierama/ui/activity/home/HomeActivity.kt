package com.skoukio.movierama.ui.activity.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.skoukio.movierama.R
import com.skoukio.movierama.common.DefinitionsApi
import com.skoukio.movierama.common.DefinitionsApi.BUNDLE.MOVIE_REQUEST
import com.skoukio.movierama.common.application.MovieRamaApplication
import com.skoukio.movierama.models.data.home.MovieModel
import com.skoukio.movierama.mvp.interactor.home.HomeInteractorImpl
import com.skoukio.movierama.mvp.presenter.home.HomePresenter
import com.skoukio.movierama.mvp.presenter.home.HomePresenterImpl
import com.skoukio.movierama.mvp.view.home.HomeView
import com.skoukio.movierama.ui.activity.movieDetails.MovieDetailsActivity
import com.skoukio.movierama.ui.adapter.home.PopularMoviesRecyclerViewAdapter
import com.skoukio.movierama.ui.custom.itemDecoration.BottomTopDividerItemDecoration
import com.skoukio.movierama.ui.custom.pagination.PaginationScrollListener
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener, HomeView {

    private lateinit var moviesRecyclerViewAdapter: PopularMoviesRecyclerViewAdapter
    private lateinit var homePresenter: HomePresenter
    private var paginationScrollListener: PaginationScrollListener? = null

    companion object {
        private const val PAGINATION_SIZE = 20
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homePresenter = HomePresenterImpl(
            view = this,
            interactor = HomeInteractorImpl(
                networkProvider = MovieRamaApplication.get().networkProvider,
                sharedPreferencesProvider = MovieRamaApplication.get().sharedPreferencesProvider
            )
        )
        initLayout()
        initSearchView()
        homePresenter.getPopularMovies()
    }

    override fun onDestroy() {
        super.onDestroy()
        homePresenter.detach()
    }

    private fun initLayout() {
        moviesRecyclerViewAdapter = PopularMoviesRecyclerViewAdapter(
            movieClicked = { movie ->
                val movieDetailsIntent = Intent(this, MovieDetailsActivity::class.java)
                movieDetailsIntent.putExtra(DefinitionsApi.BUNDLE.MOVIE, movie)
                startActivityForResult(movieDetailsIntent, MOVIE_REQUEST)
                overridePendingTransition(
                    R.anim.animation_slide_in_right,
                    R.anim.animation_zoom_out
                )
            },
            onFavoriteClicked = { movie ->
                homePresenter.toggleFavorite(movie)
            })
        val layoutManager = LinearLayoutManager(this)
        moviesRecyclerView?.layoutManager = layoutManager
        val topBottomMargin =
            resources?.getDimensionPixelSize(R.dimen.common_ten_dp) ?: 0
        val betweenMargin =
            resources?.getDimensionPixelSize(R.dimen.common_ten_dp) ?: 0
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

        paginationScrollListener = PaginationScrollListener(
            layoutManager, {
                homePresenter.getPopularMovies()
            },
            PAGINATION_SIZE
        )
        paginationScrollListener?.let { paginationScrollListener ->
            moviesRecyclerView?.addOnScrollListener(paginationScrollListener)
        }

        moviesRecyclerView?.adapter = moviesRecyclerViewAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val movie = data?.getParcelableExtra<MovieModel>(DefinitionsApi.BUNDLE.MOVIE) ?: return
            moviesRecyclerViewAdapter.refreshItem(movie)
        }
    }

    private fun initSearchView() {
        movieSearchView?.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                homePresenter.filterMovies(query ?: "")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        movieSearchView?.findViewById<View>(androidx.appcompat.R.id.search_close_btn)
            ?.setOnClickListener {
                homePresenter.filterMovies(null)
                movieSearchView?.setQuery(null, true)
            }
    }

    override fun onRefresh() {
        moviesRecyclerViewAdapter.clearData()
        homePresenter.resetPagination()
        homePresenter.getPopularMovies()
    }

    override fun showPopularMovies(popularMovies: List<MovieModel>) {
        moviesRecyclerViewAdapter.addPopularMoviesList(popularMovies)
        moviesSwipeRefreshLayout?.isRefreshing = false
    }

    override fun showLoading() {
        moviesSwipeRefreshLayout?.isRefreshing = true
    }

    override fun showFetching() {
        moviesRecyclerViewAdapter.setFetching(true)
    }

    override fun showError() {
        Toast.makeText(this, "Something went wrong...", Toast.LENGTH_LONG).show()
    }

    override fun clearPreviousMovies() {
        moviesRecyclerViewAdapter.clearData()
    }
}