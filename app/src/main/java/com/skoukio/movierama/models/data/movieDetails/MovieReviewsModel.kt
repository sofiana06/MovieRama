package com.skoukio.movierama.models.data.movieDetails

import android.os.Parcelable
import com.skoukio.movierama.network.parsers.response.movieDetails.MovieReviewsResponse
import com.skoukio.movierama.network.parsers.response.movieDetails.MovieReviewsResults
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieReviewsResponseModel(
    val id: Long? = null,
    val page: Int? = null,
    val results: MovieReviewsModel
) : Parcelable

@Parcelize
data class MovieReviewsModel(
    val author: String? = null,
    val review: String? = null
) : Parcelable

fun MovieReviewsResponse?.toModel(): MovieReviewsResponseModel {
    return MovieReviewsResponseModel(
        id = this?.id,
        page = this?.page,
        results = this?.results.toModel()
    )
}

fun MovieReviewsResults?.toModel(): MovieReviewsModel {
    return MovieReviewsModel(
        author = this?.author,
        review = this?.review
    )
}