package com.duosi.favmovieskotlin.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.duosi.favmovieskotlin.repositories.MovieRepository
import com.duosi.favmovieskotlin.requests.MovieSearchResponse

class FindMoviesViewModel(application: Application) : AndroidViewModel(application) {

    fun getMovies(): LiveData<MovieSearchResponse> {
        return MovieRepository.getMovies()
    }

    fun searchMovies(s: String) {
        MovieRepository.searchMovies(s)
    }

    fun cancelJobs() {
        MovieRepository.cancelJobs()
    }

}