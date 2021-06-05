package com.skoukio.movierama.network.parsers.response.home

import com.google.gson.annotations.SerializedName

class MoviesResponse(
    @SerializedName("page") var page: Int = 0,
    @SerializedName("results") var results: List<MoviesResults>
)