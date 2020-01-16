package com.duosi.favmovieskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.duosi.favmovieskotlin.adapters.FavMovieRecyclerAdapter
import com.duosi.favmovieskotlin.models.Movie
import com.duosi.favmovieskotlin.utils.TopSpacingItemDecoration
import com.duosi.favmovieskotlin.viewmodels.FavMoviesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.movie_list

class MainActivity : AppCompatActivity() {

    private val TAG: String = "MainActivity"

    lateinit var viewModel: FavMoviesViewModel

    lateinit var favMovieRecyclerAdapter: FavMovieRecyclerAdapter

    lateinit var it: List<Movie>

    var sortFlag = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_find_movies.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, FindMoviesActivity::class.java)
            startActivity(intent)
        }

        button_sort_date.setOnClickListener {
            if (sortFlag == 1) {
                println("sortFlag : $sortFlag")
                button_sort_date.setText("Ascending movie by year")
                favMovieRecyclerAdapter.sortByDataDescending()
                favMovieRecyclerAdapter.notifyDataSetChanged()
                sortFlag = 2
            } else {
                println("sortFlag : $sortFlag")
                button_sort_date.setText("Descending movie by year")
                favMovieRecyclerAdapter.sortByDataAscending()
                favMovieRecyclerAdapter.notifyDataSetChanged()
                sortFlag = 1
            }
        }

        viewModel = ViewModelProviders.of(this).get(FavMoviesViewModel::class.java)

        viewModel.getFavMovies().observe(this, Observer {
            println("DEBUG: ${it.size}")
            it?.let { it1 -> favMovieRecyclerAdapter.submitList(it1) }
            favMovieRecyclerAdapter.notifyDataSetChanged()
        })
        initRecyclerView()

    }

    private fun initRecyclerView() {

        movie_list.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            favMovieRecyclerAdapter = FavMovieRecyclerAdapter(application)
            adapter = favMovieRecyclerAdapter
        }
    }

}
