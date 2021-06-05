package com.skoukio.movierama.network.parsers.response.movieDetails

import com.google.gson.annotations.SerializedName

class SimilarMoviesResponse (
    @SerializedName("page") var page: Int = 0,
    @SerializedName("results") var results: SimilarMoviesResults
)