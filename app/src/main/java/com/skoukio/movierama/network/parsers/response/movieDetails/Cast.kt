package com.skoukio.movierama.network.parsers.response.movieDetails

import com.google.gson.annotations.SerializedName

data class Cast(
    @SerializedName("name")
    val name: String? = null
)