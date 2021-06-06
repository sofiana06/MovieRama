package com.skoukio.movierama.network.parsers.response.movieDetails

import com.google.gson.annotations.SerializedName

class MovieReviewsResponse(
    @SerializedName("id") var id: Long = 0,
    @SerializedName("page") var page: Int = 0,
    @SerializedName("results") var results: List<MovieReviewsResults>
)