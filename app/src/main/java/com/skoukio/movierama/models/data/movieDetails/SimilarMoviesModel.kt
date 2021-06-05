package com.skoukio.movierama.models.data.movieDetails

import android.os.Parcelable
import com.skoukio.movierama.network.parsers.response.movieDetails.SimilarMoviesResponse
import com.skoukio.movierama.network.parsers.response.movieDetails.SimilarMoviesResults
import kotlinx.android.parcel.Parcelize

@Parcelize
class SimilarMoviesResponseModel(
    val page: Int? = null,
    val results: SimilarMoviesModel
) : Parcelable

@Parcelize
class SimilarMoviesModel(
    val id: Int? = null,
    val title: String? = null,
    val poster: String? = null
) : Parcelable

fun SimilarMoviesResponse?.toModel(): SimilarMoviesResponseModel {
    return SimilarMoviesResponseModel(
        page = this?.page,
        results = this?.results.toModel()
    )
}

fun SimilarMoviesResults?.toModel(): SimilarMoviesModel {
    return SimilarMoviesModel(
        id = this?.id,
        title = this?.title,
        poster = this?.poster
    )
}