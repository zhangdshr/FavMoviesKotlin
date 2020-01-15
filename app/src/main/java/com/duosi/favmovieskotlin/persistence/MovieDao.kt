package com.duosi.favmovieskotlin.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.duosi.favmovieskotlin.models.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies ORDER BY title ASC")
    fun getMovies(): LiveData<List<Movie>>

    @Insert(onConflict = REPLACE)
    fun insertMovie(movie: Movie)

    @Query("DELETE FROM movies WHERE title = :title")
    fun deleteByMovieTitle(title: String)

}