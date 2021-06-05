package com.skoukio.movierama.network.api

import com.skoukio.movierama.network.api.factory.RetrofitFactory
import com.skoukio.movierama.network.providers.NetworkProvider

class MovieRamaClient : NetworkProvider {

    private val movieRamaApi: MovieRamaApi by lazy {
        return@lazy RetrofitFactory().buildMovieRamaApi()
    }

    //    override fun getReceiptsAsync(): Deferred<ListReceiptResponse> {
    //        return coralPayAtPumpApi.getReceiptsAsync()
    //    }
}