package com.skoukio.movierama.models.data.movieDetails

import android.os.Parcelable
import com.skoukio.movierama.common.DefinitionsApi
import com.skoukio.movierama.models.common.ImageModel
import com.skoukio.movierama.models.data.home.toModel
import com.skoukio.movierama.network.parsers.response.movieDetails.SimilarMoviesResponse
import com.skoukio.movierama.network.parsers.response.movieDetails.SimilarMoviesResults
import kotlinx.android.parcel.Parcelize

@Parcelize
class SimilarMoviesResponseModel(
    val page: Int? = null,
    val results: List<SimilarMoviesModel>
) : Parcelable

@Parcelize
class SimilarMoviesModel(
    val id: Int? = null,
    val title: String? = null,
    val poster: ImageModel = ImageModel()
) : Parcelable

fun SimilarMoviesResponse?.toModel(): SimilarMoviesResponseModel {
    return SimilarMoviesResponseModel(
        page = this?.page,
        results = this?.results?.map { it.toModel() } ?: listOf()
    )
}

fun SimilarMoviesResults?.toModel(): SimilarMoviesModel {
    return SimilarMoviesModel(
        id = this?.id,
        title = this?.title,
        poster = ImageModel(url = DefinitionsApi.DOMAIN_IMAGE + this?.poster ?: "")
    )
}