package com.duosi.favmovieskotlin.requests

import com.duosi.favmovieskotlin.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val movieApi: MovieApi by lazy {

        retrofitBuilder
            .build()
            .create(MovieApi::class.java)
    }

}
