package com.skoukio.movierama.network.parsers.response.movieDetails

import com.google.gson.annotations.SerializedName

class MovieCreditsResponse(
    @SerializedName("id") var id: Long = 0,
    @SerializedName("cast") var cast: List<Cast>
)