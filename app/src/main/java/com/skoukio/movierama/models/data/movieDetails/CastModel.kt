package com.skoukio.movierama.models.data.movieDetails

import android.os.Parcelable
import com.skoukio.movierama.network.parsers.response.movieDetails.MovieCreditsResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieCreditsModel(
    val id: Long? = null,
    val genres: List<String>,
    val cast: List<String>,
    val director: List<String>
) : Parcelable


fun MovieCreditsResponse?.toModel(): MovieCreditsModel {
    return MovieCreditsModel(
        id = this?.id,
        genres = this?.genres?.mapNotNull { it.name } ?: emptyList(),
        cast = this?.credits?.cast?.mapNotNull { it.name }?.take(3) ?: emptyList(),
        director = this?.credits?.crew?.filter { it.job == "Director" }?.mapNotNull { it.name }
            ?: emptyList()
    )
}

fun MovieCreditsModel.getGenres(): String {
    val sb = StringBuilder()
    for (index in genres.indices) {
        val name = genres[index]
        sb.append(name)
        if (index < genres.size - 1) {
            sb.append(", ")
        }
    }
    return sb.toString()
}

fun MovieCreditsModel.getCast(): String {
    val sb = StringBuilder()
    for (index in cast.indices) {
        val name = cast[index]
        sb.append(name)
        if (index < cast.size - 1) {
            sb.append(", ")
        }
    }
    return sb.toString()
}

fun MovieCreditsModel.getDirector(): String {
    val sb = StringBuilder()
    for (index in director.indices) {
        val name = director[index]
        sb.append(name)
        if (index < director.size - 1) {
            sb.append(", ")
        }
    }
    return sb.toString()
}

