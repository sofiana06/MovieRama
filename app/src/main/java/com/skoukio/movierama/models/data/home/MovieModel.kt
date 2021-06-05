package com.skoukio.movierama.models.data.home


import android.os.Parcelable
import com.skoukio.movierama.common.DefinitionsApi
import com.skoukio.movierama.models.common.ImageModel
import com.skoukio.movierama.network.parsers.response.home.MoviesResponse
import com.skoukio.movierama.network.parsers.response.home.MoviesResults
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesResponseModel(
    val page: Int? = null,
    val results: List<MovieModel>?
) : Parcelable

@Parcelize
data class MovieModel(
    val title: String? = null,
    val releaseDate: String? = null,
    val rating: Double? = null,
    val poster: ImageModel = ImageModel()
) : Parcelable

fun MoviesResponse?.toModel(): MoviesResponseModel {
    return MoviesResponseModel(
        page = this?.page,
        results = this?.results?.map { it.toModel() } ?: listOf()
    )
}

fun MoviesResults?.toModel(): MovieModel {
    return MovieModel(
        title = this?.title,
        releaseDate = this?.releaseDate,
        rating = this?.rating,
        poster = ImageModel(url = DefinitionsApi.DOMAIN_IMAGE + this?.poster ?: "")
    )
}