package com.kamolovproducts.aimovie.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kamolovproducts.aimovie.R
import com.kamolovproducts.aimovie.adapter.MovieAdapter
import com.kamolovproducts.aimovie.ui.views.NowPlayingActivity
import com.kamolovproducts.aimovie.ui.views.PopularActivity
import com.kamolovproducts.aimovie.ui.views.TopRatedActivity
import com.kamolovproducts.aimovie.ui.views.TrendingMovies
import com.kamolovproducts.aimovie.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_recview_four.*
import kotlinx.android.synthetic.main.row_recview_one.*
import kotlinx.android.synthetic.main.row_recview_three.*
import kotlinx.android.synthetic.main.row_recview_two.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainMovieLoad()
        moremovies()
        refreshLayout.setOnRefreshListener {
            mainMovieLoad()
            refreshLayout.isRefreshing = false
        }
    }

    private fun moremovies() {

        cl_trending_movies.setOnClickListener {
            val intent = Intent(this, TrendingMovies::class.java)
            startActivity(intent)
        }

        cl_popular_movies.setOnClickListener {
            val intent = Intent(this, PopularActivity::class.java)
            startActivity(intent)
        }

        cl_now_playing_movies.setOnClickListener {
            val intent = Intent(this, NowPlayingActivity::class.java)
            startActivity(intent)
        }

        cl_top_rated_movies.setOnClickListener {
            val intent = Intent(this, TopRatedActivity::class.java)
            startActivity(intent)
        }
    }

    private fun mainMovieLoad() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.adapter = MovieAdapter(this, viewModel.resultsTrending)
        recycler_view_trending_movies.adapter = viewModel.adapter
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler_view_trending_movies.layoutManager = layoutManager
        viewModel.getTrendingMovies()

        viewModel.adapter2 = MovieAdapter(this, viewModel.resultsPopular)
        recycler_view_popular_movies.adapter = viewModel.adapter2
        val layoutManager2 = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler_view_popular_movies.layoutManager = layoutManager2
        viewModel.getPopularMovies()

        viewModel.adapter3 = MovieAdapter(this, viewModel.resultsNowPlaying)
        recycler_view_now_playing_movies.adapter = viewModel.adapter3
        val layoutManager3 = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler_view_now_playing_movies.layoutManager = layoutManager3
        viewModel.getNowPlayingMovies()

        viewModel.adapter4 = MovieAdapter(this, viewModel.resultsTopRated)
        recycler_view_top_rated_movies.adapter = viewModel.adapter4
        val layoutManager4 = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler_view_top_rated_movies.layoutManager = layoutManager4
        viewModel.getTopRatedMovies()
    }


}