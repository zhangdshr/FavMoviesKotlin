package com.duosi.favmovieskotlin.repositories

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.duosi.favmovieskotlin.models.Movie
import com.duosi.favmovieskotlin.persistence.MovieDao
import com.duosi.favmovieskotlin.persistence.MovieDatabase
import com.duosi.favmovieskotlin.requests.MovieSearchResponse
import com.duosi.favmovieskotlin.requests.RetrofitService
import com.duosi.favmovieskotlin.utils.Constants
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object MovieRepository {

    private var movies: MutableLiveData<MovieSearchResponse> = MutableLiveData()

    fun getMovies(): LiveData<MovieSearchResponse> {
        return movies
    }

    var job: CompletableJob? = null

    fun searchMovies(s: String) {

        job = Job()
        job?.let { theJob ->
            CoroutineScope(IO + theJob).launch {
                val res = RetrofitService.movieApi.searchMovies(
                    s,
                    Constants.RESPONSE_TYPE,
                    Constants.RESPONSE_R,
                    Constants.API_KEY
                )

                withContext(Main) {
                    movies.postValue(res)
                    theJob.complete()
                }
            }


        }
    }

    fun cancelJobs() {
        job?.cancel()
    }


}

class DBRepository(application: Application) {

    companion object {
        @Volatile
        var instance: DBRepository? = null

        fun getInstance(application: Application): DBRepository {
            if (instance == null) {
                synchronized(DBRepository::class) {
                    if (instance == null) {
                        instance = DBRepository(application)
                    }
                }
            }
            return instance!!
        }
    }

    private var movieDao: MovieDao

    private var allMovies: LiveData<List<Movie>>

    init {
        val database: MovieDatabase = MovieDatabase.getInstance(application.applicationContext)!!
        movieDao = database.movieDao()
        allMovies = movieDao.getMovies()
    }

    fun getFavMovies(): LiveData<List<Movie>> {
        return movieDao.getMovies()
    }

    fun insertFavMovie(movie: Movie) {
        movieDao.insertMovie(movie)
    }

    fun deleteFavMovie(movie: Movie) {
        movieDao.deleteByMovieTitle(movie.Title)
    }

}