package com.duosi.favmovieskotlin.requests

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/")
    suspend fun searchMovies(
        @Query("s") s: String,
        @Query("type") type: String,
        @Query("r") r: String,
        @Query("apikey") apikey: String
    ): MovieSearchResponse

}