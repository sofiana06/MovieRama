package com.skoukio.movierama.models.data.home


import android.os.Parcelable
import com.skoukio.movierama.models.common.ImageModel
import com.skoukio.movierama.models.common.Model
import com.skoukio.movierama.models.common.Model.Companion.INVALID_DOUBLE
import com.skoukio.movierama.models.common.Model.Companion.INVALID_INT
import com.skoukio.movierama.models.common.Model.Companion.INVALID_STRING
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(
    val id: Int = INVALID_INT,
    val language: String = INVALID_STRING,
    val title: String = INVALID_STRING,
    val overview: String = INVALID_STRING,
    val popularity: Int = INVALID_INT,
    val image: ImageModel = ImageModel(),
    val content: String = INVALID_STRING,
    val releaseDate: String = INVALID_STRING,
    val averageVotes: Double = INVALID_DOUBLE,
    val votesCount: Double = INVALID_DOUBLE
) : Parcelable, Model