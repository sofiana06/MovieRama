package com.skoukio.movierama.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skoukio.movierama.R
import com.skoukio.movierama.common.DefinitionsApi.GLOBAL_PATTERN_DD_MM_YYYY
import com.skoukio.movierama.common.extensions.getFormattedDate
import com.skoukio.movierama.common.extensions.loadImage
import com.skoukio.movierama.models.data.home.MovieModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.row_movie.*

class PopularMoviesRecyclerViewAdapter
    (private val movieClicked: (MovieModel) -> Unit) :
    RecyclerView.Adapter<PopularMoviesRecyclerViewAdapter.PopularMoviesViewHolder>() {

    private var movieList: List<MovieModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        return PopularMoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_movie,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
//        val movie = movieList[position]
//        holder.bind(movie)
//        holder.itemView.setOnClickListener {
//            movieClicked.invoke(movie)
//        }
    }

    override fun getItemCount(): Int {
        return 10
        //movieList.size
    }

    class PopularMoviesViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
 //       fun bind(movie: MovieModel) {
//            movieImage?.loadImage(movie.image)
//            movieTitle?.text = movie.title
//            dateText?.text = movie.releaseDate.getFormattedDate(GLOBAL_PATTERN_DD_MM_YYYY)
//            favouriteImage.setOnClickListener {
//            }
 //       }
    }
}