package com.duosi.favmovieskotlin.adapters

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.duosi.favmovieskotlin.R
import com.duosi.favmovieskotlin.models.Movie
import com.duosi.favmovieskotlin.repositories.DBRepository
import kotlinx.android.synthetic.main.layout_fav_movie_list_item.view.*
import kotlinx.android.synthetic.main.layout_movie_list_item.view.movie_image
import kotlinx.android.synthetic.main.layout_movie_list_item.view.movie_title
import kotlinx.android.synthetic.main.layout_movie_list_item.view.movie_year

class FavMovieRecyclerAdapter(private val application: Application) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG: String = "FavMovieRecyclerAdapter"

    private var items: List<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_fav_movie_list_item,
                parent,
                false
            ), application
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {

            is MovieViewHolder -> {
                holder.bind(items.get(position))
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(movieList: List<Movie>) {
        items = movieList
    }

    class MovieViewHolder
    constructor(
        itemView: View, private val application: Application
    ) : RecyclerView.ViewHolder(itemView) {

        val movie_title = itemView.movie_title
        val movie_year = itemView.movie_year
        val btn_del_fav = itemView.btn_del_fav
        val movie_image = itemView.movie_image

        fun bind(movie: Movie) {

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(movie.Poster)
                .into(movie_image)
            movie_title.setText(movie.Title)
            movie_year.setText(movie.Year)
            btn_del_fav.setOnClickListener {
                println("click ${movie.Title}")
                DBRepository.getInstance(application).deleteFavMovie(movie)
                Toast.makeText(application, "", Toast.LENGTH_SHORT).show()
            }

        }

    }

}