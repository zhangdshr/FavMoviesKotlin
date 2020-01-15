package com.duosi.favmovieskotlin.requests

import com.duosi.favmovieskotlin.models.Movie
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class MovieSearchResponse(

    @Expose
    @SerializedName("Search")
    val movies: List<Movie>? = null

)