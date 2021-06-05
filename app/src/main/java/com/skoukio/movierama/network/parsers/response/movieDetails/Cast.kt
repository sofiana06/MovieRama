package com.skoukio.movierama.network.parsers.response.movieDetails

import com.google.gson.annotations.SerializedName

data class Cast (
    @SerializedName("original_name")
    var name: String? = null
)