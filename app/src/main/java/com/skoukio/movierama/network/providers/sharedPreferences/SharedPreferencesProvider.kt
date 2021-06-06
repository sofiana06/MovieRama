package com.skoukio.movierama.network.providers.sharedPreferences

interface SharedPreferencesProvider {
    fun isFavoriteMovie(movieId: Int?): Boolean
    fun setFavoriteMovie(movieId: Int, isFavorite: Boolean)
}