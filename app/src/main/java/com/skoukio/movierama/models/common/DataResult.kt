package com.skoukio.movierama.models.common

data class DataResult<out T>(
    val data: T? = null,
    val throwable: Throwable? = null
)