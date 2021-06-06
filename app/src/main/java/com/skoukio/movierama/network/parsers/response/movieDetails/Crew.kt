package com.skoukio.movierama.network.parsers.response.movieDetails

import com.google.gson.annotations.SerializedName

data class Crew (
    @SerializedName("name")
    val name: String? = null,

    @SerializedName("job")
    val job: String? = null
)