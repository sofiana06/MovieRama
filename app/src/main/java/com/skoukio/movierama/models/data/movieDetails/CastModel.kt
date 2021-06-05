package com.skoukio.movierama.models.data.movieDetails

import android.os.Parcelable
import com.skoukio.movierama.network.parsers.response.movieDetails.Cast
import com.skoukio.movierama.network.parsers.response.movieDetails.MovieCreditsResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieCreditsModel(
    val id: Long? = null,
    val cast: List<CastModel>
) : Parcelable

@Parcelize
data class CastModel(
    val name: String? = null
) : Parcelable

fun MovieCreditsResponse?.toModel(): MovieCreditsModel {
    return MovieCreditsModel(
        id = this?.id,
        cast = this?.cast?.map { it.toModel() } ?: listOf()
    )
}

fun Cast?.toModel(): CastModel {
    return CastModel(
        name = this?.name
    )
}