package com.skoukio.movierama.network.parsers.response.movieDetails

import com.google.gson.annotations.SerializedName

data class MovieReviewsResults(
    @SerializedName("author")
    var author: String? = null,
    @SerializedName("content")
    var review: String? = null
)