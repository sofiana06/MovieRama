package com.skoukio.movierama.network.parsers.response.movieDetails

import com.google.gson.annotations.SerializedName

data class SimilarMoviesResults (
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("original_title")
    var title: String? = null,
    @SerializedName("poster_path")
    var poster: String? = null
)