package com.skoukio.movierama.network.parsers.response.home

import com.google.gson.annotations.SerializedName
import com.skoukio.movierama.models.common.ImageModel

data class MoviesResults(
    @SerializedName("original_title")
    var title: String? = null,
    @SerializedName("release_date")
    var releaseDate: String? = null,
    @SerializedName("vote_average")
    var rating: Double? = null,
    @SerializedName("backdrop_path")
    var poster: String? = null
)