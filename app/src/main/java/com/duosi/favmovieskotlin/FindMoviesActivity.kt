package com.duosi.favmovieskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.duosi.favmovieskotlin.adapters.MovieRecyclerAdapter
import com.duosi.favmovieskotlin.utils.TopSpacingItemDecoration
import com.duosi.favmovieskotlin.viewmodels.FindMoviesViewModel
import kotlinx.android.synthetic.main.activity_find_movies.*

class FindMoviesActivity : AppCompatActivity() {

    lateinit var viewModel: FindMoviesViewModel

    lateinit var movieRecyclerAdapter: MovieRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_movies)

        viewModel = ViewModelProviders.of(this).get(FindMoviesViewModel::class.java)

        viewModel.getMovies().observe(this, Observer {
            println("DEBUG: ${it.movies?.size}")
            it.movies?.let { it1 -> movieRecyclerAdapter.submitList(it1) }
            movieRecyclerAdapter.notifyDataSetChanged()
        })

        back.setOnClickListener {
            viewModel.cancelJobs()
            finish()
        }

        btn_find.setOnClickListener {
            if (edit_find.text != null) {
                viewModel.searchMovies(edit_find.text.toString())
            } else {
                Toast.makeText(this, "input search word", Toast.LENGTH_SHORT).show()
            }
        }

        initRecyclerView()

    }

    private fun initRecyclerView() {

        movie_list.apply {
            layoutManager = LinearLayoutManager(this@FindMoviesActivity)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            movieRecyclerAdapter = MovieRecyclerAdapter(application)
            adapter = movieRecyclerAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJobs()
    }
}
