package com.skoukio.movierama.ui.adapter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skoukio.movierama.R
import com.skoukio.movierama.common.extensions.getFormattedDate
import com.skoukio.movierama.common.extensions.loadImage
import com.skoukio.movierama.models.data.home.MovieModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.row_movie.*

class PopularMoviesRecyclerViewAdapter(
    private val movieClicked: (MovieModel) -> Unit,
    private val onFavoriteClicked: (MovieModel) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val movieList: MutableList<MovieModel> = mutableListOf()
    private var isFetching: Boolean = false

    companion object {
        const val ROW_ITEM = 0
        const val ROW_LOADING = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ROW_ITEM) {
            return PopularMoviesViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.row_movie,
                    parent,
                    false
                )
            )
        } else {
            return MoviesLoadingViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.row_loading,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PopularMoviesViewHolder) {
            val movie = movieList[position]
            holder.bind(movie)
            holder.itemView.setOnClickListener {
                movieClicked.invoke(movie)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (isFetching) movieList.size + 1 else movieList.size
    }

    override fun getItemViewType(position: Int): Int {
        if (isFetching && position == itemCount - 1) {
            return ROW_LOADING
        }
        return ROW_ITEM
    }

    fun clearData() {
        movieList.clear()
        this.isFetching = false
        notifyDataSetChanged()
    }

    fun addPopularMoviesList(popularMovies: List<MovieModel>) {
        movieList.addAll(popularMovies)
        this.isFetching = false
        notifyDataSetChanged()
    }

    fun setFetching(isFetching: Boolean) {
        this.isFetching = isFetching
        notifyDataSetChanged()
    }

    inner class PopularMoviesViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(movie: MovieModel) {
            movieImage?.loadImage(movie.poster)
            movieTitle?.text = movie.title
            dateText?.text = movie.releaseDate?.getFormattedDate()
            ratingBarWidget?.rating = (movie.rating?.toFloat() ?: 0f) * 5 / 10
            favouriteImage.setImageResource(if (movie.isFavorite) R.drawable.ic_icon_favorite else R.drawable.ic_icon_unfavorite)
            favouriteImage.setOnClickListener {
                onFavoriteClicked(movie)
                val index = movieList.indexOf(movie)
                movieList.removeAt(index)
                movieList.add(index, movie.copy(isFavorite = !movie.isFavorite))
                notifyItemChanged(index)
            }
        }
    }

    class MoviesLoadingViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {}
}