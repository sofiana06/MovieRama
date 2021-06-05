package com.skoukio.movierama.ui.adapter.movieDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skoukio.movierama.R
import com.skoukio.movierama.common.extensions.getFormattedDate
import com.skoukio.movierama.models.data.home.MovieModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.activity_movie_details.favouriteImage
import kotlinx.android.synthetic.main.activity_movie_details.movieTitle

class SimilarMoviesRecyclerViewAdapter :
    RecyclerView.Adapter<SimilarMoviesRecyclerViewAdapter.SimilarMoviesViewHolder>() {

    private var movieList: List<MovieModel> = listOf()

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

    override fun onBindViewHolder(
        holder: SimilarMoviesViewHolder,
        position: Int
    ) {
      //  val movie = movieList[position]
//        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return 10
      //  return movieList.size
    }

    class SimilarMoviesViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
//        fun bind(movie: MovieModel) {
//          //  movieImage?.loadImage(movie.poster)
//            movieTitle?.text = movie.title
//            dateTitle?.text = movie.releaseDate?.getFormattedDate()
//            ratingBarWidget?.rating = (movie.rating?.toFloat() ?: 0f) * 5 / 10
//            favouriteImage.setOnClickListener {
//            }
//        }
    }
}