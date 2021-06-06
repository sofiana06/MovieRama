package com.skoukio.movierama.network.parsers.response.movieDetails

import com.google.gson.annotations.SerializedName

class MovieCreditsResponse(
    @SerializedName("id") val id: Long? = 0,
    @SerializedName("credits") val credits: Credits? = null,
    @SerializedName("genres") val genres: List<Genre>? = null
)

data class Credits(
    @SerializedName("cast") val cast: List<Cast>? = null,
    @SerializedName("crew") val crew: List<Crew>? = null
)

data class Genre(
    @SerializedName("name") val name: String? = null
)

