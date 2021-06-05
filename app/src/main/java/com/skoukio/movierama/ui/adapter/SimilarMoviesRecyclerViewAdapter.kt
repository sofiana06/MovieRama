package com.skoukio.movierama.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skoukio.movierama.R
import com.skoukio.movierama.models.data.home.MovieModel
import kotlinx.android.extensions.LayoutContainer

class SimilarMoviesRecyclerViewAdapter :
    RecyclerView.Adapter<SimilarMoviesRecyclerViewAdapter.SimilarMoviesViewHolder>() {

    private var movieList: List<MovieModel> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SimilarMoviesRecyclerViewAdapter.SimilarMoviesViewHolder {
        return SimilarMoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_similar_movie,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: SimilarMoviesRecyclerViewAdapter.SimilarMoviesViewHolder,
        position: Int
    ) {
        val movie = movieList[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class SimilarMoviesViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(movie: MovieModel) {
//            movieImage?.loadImage(movie.image)
//            movieTitle?.text = movie.title
//            dateText?.text = movie.releaseDate.getFormattedDate(GLOBAL_PATTERN_DD_MM_YYYY)
//            favouriteImage.setOnClickListener {
//            }
        }
    }
}