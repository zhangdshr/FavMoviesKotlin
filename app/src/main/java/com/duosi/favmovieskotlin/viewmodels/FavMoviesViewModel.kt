package com.duosi.favmovieskotlin.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.duosi.favmovieskotlin.models.Movie
import com.duosi.favmovieskotlin.repositories.DBRepository

class FavMoviesViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: DBRepository = DBRepository.getInstance(application)

    fun delete(movie: Movie) {
        repository.deleteFavMovie(movie)
    }

    fun getFavMovies(): LiveData<List<Movie>> {
        return repository.getFavMovies()
    }

}