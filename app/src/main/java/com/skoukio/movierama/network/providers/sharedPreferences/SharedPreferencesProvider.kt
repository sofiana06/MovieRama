package com.skoukio.movierama.network.providers.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.skoukio.movierama.models.data.home.MovieModel

class SharedPreferencesProvider(private val appContext: Context) {

    private val preferences: SharedPreferences =
        appContext.getSharedPreferences(PREFS_FILENAME, PRIVATE_MODE)

    companion object {
        private const val PRIVATE_MODE = 0
        private const val PREFS_FILENAME = "movieRamaSharedPreferences"

        private const val FAVORITE_MOVIES = "favorite_movies"
    }

    @Synchronized
    fun setFavoriteMovies(favoriteMovieList: List<MovieModel>) {
        preferences.edit().putString((FAVORITE_MOVIES), Gson().toJson(favoriteMovieList))
            .apply()
    }

    @Synchronized
    fun getFavoriteMovies(): List<MovieModel> {
        return Gson().fromJson(
            preferences.getString((FAVORITE_MOVIES), ""),
            object : TypeToken<List<MovieModel>>() {}.type
        )
    }
}