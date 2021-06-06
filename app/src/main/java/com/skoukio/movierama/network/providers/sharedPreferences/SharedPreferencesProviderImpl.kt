package com.skoukio.movierama.network.providers.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.skoukio.movierama.models.data.favorite.FavoriteMoviesModel

class SharedPreferencesProviderImpl(private val appContext: Context) : SharedPreferencesProvider {

    private val preferences: SharedPreferences =
        appContext.getSharedPreferences(PREFS_FILENAME, PRIVATE_MODE)

    companion object {
        private const val PRIVATE_MODE = 0
        private const val PREFS_FILENAME = "movieRamaSharedPreferences"

        private const val FAVORITE_MOVIES = "favorite_movies"
    }

    override fun isFavoriteMovie(movieId: Int?): Boolean {
        if (movieId == null) {
            return false
        }
        val favoriteMovies = getFavoriteMovies()
        return favoriteMovies.favoriteMoviesIds.contains(movieId)
    }

    override fun setFavoriteMovie(movieId: Int, isFavorite: Boolean) {
        val favoriteMovies = getFavoriteMovies()
        var updatedFavoriteMovies: FavoriteMoviesModel = favoriteMovies
        if (!isFavorite) {
            updatedFavoriteMovies =
                favoriteMovies.copy(favoriteMoviesIds = favoriteMovies.favoriteMoviesIds.filter { it != movieId }
                    .toMutableList())
        } else {
            updatedFavoriteMovies = favoriteMovies.copy()
            updatedFavoriteMovies.favoriteMoviesIds.add(movieId)
            updatedFavoriteMovies = updatedFavoriteMovies.copy(
                favoriteMoviesIds = updatedFavoriteMovies.favoriteMoviesIds.distinct()
                    .toMutableList()
            )
        }
        setFavoriteMovies(updatedFavoriteMovies)
    }

    @Synchronized
    private fun setFavoriteMovies(favoriteMovies: FavoriteMoviesModel) {
        preferences.edit().putString(FAVORITE_MOVIES, Gson().toJson(favoriteMovies))
            .apply()
    }

    @Synchronized
    private fun getFavoriteMovies(): FavoriteMoviesModel {
        return Gson().fromJson(
            preferences.getString(FAVORITE_MOVIES, "{}"),
            FavoriteMoviesModel::class.java
        )
    }
}