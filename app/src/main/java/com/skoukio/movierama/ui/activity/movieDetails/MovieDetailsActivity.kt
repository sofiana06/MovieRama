package com.skoukio.movierama.ui.activity.movieDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.skoukio.movierama.R
import com.skoukio.movierama.common.DefinitionsApi
import com.skoukio.movierama.common.application.MovieRamaApplication
import com.skoukio.movierama.common.extensions.getFormattedDate
import com.skoukio.movierama.common.extensions.loadImage
import com.skoukio.movierama.models.data.home.MovieModel
import com.skoukio.movierama.models.data.movieDetails.*
import com.skoukio.movierama.mvp.interactor.movieDetails.MovieDetailsInteractorImpl
import com.skoukio.movierama.mvp.presenter.movieDetails.MovieDetailsPresenter
import com.skoukio.movierama.mvp.presenter.movieDetails.MovieDetailsPresenterImpl
import com.skoukio.movierama.mvp.view.movieDetails.MovieDetailsView
import com.skoukio.movierama.ui.adapter.movieDetails.MovieReviewsRecyclerViewAdapter
import com.skoukio.movierama.ui.adapter.movieDetails.SimilarMoviesRecyclerViewAdapter
import com.skoukio.movierama.ui.custom.itemDecoration.BottomTopDividerItemDecoration
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.activity_movie_details.movieImage
import kotlinx.android.synthetic.main.activity_movie_details.movieTitle

class MovieDetailsActivity : AppCompatActivity(), MovieDetailsView {

    private lateinit var similarMoviesRecyclerViewAdapter: SimilarMoviesRecyclerViewAdapter
    private lateinit var movieReviewsRecyclerViewAdapter: MovieReviewsRecyclerViewAdapter
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
        val movieDetails = intent?.extras?.getParcelable<MovieModel>(DefinitionsApi.BUNDLE.MOVIE)
        movieDetailsPresenter.getMovieCredits(movieDetails?.id ?: -1)
        movieDetailsPresenter.getMovieReviews(movieDetails?.id ?: -1)
        movieDetailsPresenter.getSimilarMovies(movieDetails?.id ?: -1)
        movieImage?.loadImage(movieDetails?.poster)
        movieTitle?.text = movieDetails?.title
        dateTitle?.text = movieDetails?.releaseDate?.getFormattedDate()
        ratingBarWidget?.rating = (movieDetails?.rating?.toFloat() ?: 0f) * 5 / 10
        movieDescription?.text = movieDetails?.overview
    }

    private fun initLayout() {
        backButtonImageView?.setOnClickListener {
            onBackPressed()
            finish()
        }
        val topBottomMargin =
            resources?.getDimensionPixelSize(R.dimen.common_ten_dp) ?: 0
        val betweenMargin =
            resources?.getDimensionPixelSize(R.dimen.common_ten_dp) ?: 0
        similarMoviesRecyclerViewAdapter = SimilarMoviesRecyclerViewAdapter()
        similarMoviesRecyclerView?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        similarMoviesRecyclerView?.adapter = similarMoviesRecyclerViewAdapter
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
        movieReviewsRecyclerViewAdapter = MovieReviewsRecyclerViewAdapter()
        reviewsRecyclerView?.layoutManager = LinearLayoutManager(this)
        reviewsRecyclerView?.adapter = movieReviewsRecyclerViewAdapter
        reviewsRecyclerView?.itemAnimator = null
        reviewsRecyclerView?.addItemDecoration(
            BottomTopDividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL,
                topBottomMargin,
                topBottomMargin,
                betweenMargin
            )
        )
    }

    override fun showMovieCast(movieCredits: MovieCreditsModel) {
        genreText?.text = movieCredits.getGenres()
        movieCastNames?.text = movieCredits.getCast()
        movieDirectorName?.text = movieCredits.getDirector()
    }

    override fun showSimilarMoviesData(similarMovies: List<SimilarMoviesModel>) {
        similarMoviesRecyclerViewAdapter.addSimilarMoviesList(similarMovies)
    }

    override fun showMovieReviewsData(movieReviews: List<MovieReviewsModel>) {
        movieReviewsRecyclerViewAdapter.addMovieReviewsList(movieReviews)
    }
}