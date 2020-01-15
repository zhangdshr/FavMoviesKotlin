package com.duosi.favmovieskotlin.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(
//
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "movie_id")
//    var movie_id: Int,

    @ColumnInfo(name = "title")
    var Title: String,

    @ColumnInfo(name = "year")
    var Year: String,

    @ColumnInfo(name = "imdbID")
    var imdbID: String,

    @ColumnInfo(name = "type")
    var Type: String,

    @ColumnInfo(name = "poster")
    var Poster: String


){

    @PrimaryKey(autoGenerate = true)
    var movie_id: Int = 0

}