package com.skoukio.movierama.network.api.factory

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.skoukio.movierama.common.DefinitionsApi
import com.skoukio.movierama.network.api.MovieRamaApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactory {
    companion object {
        private const val API_KEY = "3933d5d014e22c33f84e4924ffddaa3f"
        private const val QUERY_API_KEY = "api_key"

        private const val CONNECT_TIMEOUT_SECONDS: Long = 30
        private const val READ_TIMEOUT_SECONDS: Long = 30
    }

    fun buildMovieRamaApi(): MovieRamaApi {
        return getRetrofit(DefinitionsApi.DOMAIN, getOKHTTPClient()).create(
            MovieRamaApi::class.java
        )
    }

    private fun getRetrofit(
        domain: String,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(domain)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private fun getOKHTTPClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val urlBuilder = request.url.newBuilder()

                urlBuilder.addQueryParameter(
                    QUERY_API_KEY,
                    API_KEY
                )
                return@addInterceptor chain.proceed(
                    request.newBuilder().url(urlBuilder.build()).build()
                )

            }
            .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }
}