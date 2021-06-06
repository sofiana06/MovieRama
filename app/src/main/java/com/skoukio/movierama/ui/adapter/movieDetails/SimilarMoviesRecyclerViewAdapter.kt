package com.skoukio.movierama.ui.adapter.movieDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skoukio.movierama.R
import com.skoukio.movierama.common.extensions.loadImage
import com.skoukio.movierama.models.data.movieDetails.SimilarMoviesModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.row_similar_movie.*

class SimilarMoviesRecyclerViewAdapter :
    RecyclerView.Adapter<SimilarMoviesRecyclerViewAdapter.SimilarMoviesViewHolder>() {

    private var similarMoviesList: List<SimilarMoviesModel> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SimilarMoviesViewHolder {
        return SimilarMoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_similar_movie,
                parent,
                false
            )
        )
    }

    fun addSimilarMoviesList(similarMovies: List<SimilarMoviesModel>) {
        similarMoviesList = similarMovies
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(
        holder: SimilarMoviesViewHolder,
        position: Int
    ) {
        val similarMovie = similarMoviesList[position]
        holder.bind(similarMovie)
    }

    override fun getItemCount(): Int {
        return similarMoviesList.size
    }

    class SimilarMoviesViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(similarMovie: SimilarMoviesModel) {
            holderImage?.loadImage(similarMovie.poster)
        }
    }
}