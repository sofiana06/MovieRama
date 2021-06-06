package com.skoukio.movierama.ui.adapter.movieDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skoukio.movierama.R
import com.skoukio.movierama.models.data.movieDetails.MovieReviewsModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.row_review.*

class MovieReviewsRecyclerViewAdapter :
    RecyclerView.Adapter<MovieReviewsRecyclerViewAdapter.MovieReviewsViewHolder>() {

    private var movieReviewsList: List<MovieReviewsModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewsViewHolder {
        return MovieReviewsRecyclerViewAdapter.MovieReviewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_review,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieReviewsViewHolder, position: Int) {
        val movieReview = movieReviewsList[position]
        holder.bind(movieReview)
    }

    override fun getItemCount(): Int {
        return movieReviewsList.size
    }

    fun addMovieReviewsList(movieReviews: List<MovieReviewsModel>) {
        movieReviewsList = movieReviews
        notifyDataSetChanged()
    }

    class MovieReviewsViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(movieReview: MovieReviewsModel) {
            reviewerName?.text = movieReview.author
            reviewText?.text = movieReview.review
        }
    }
}