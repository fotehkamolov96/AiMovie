package com.kamolovproducts.aimovie.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kamolovproducts.aimovie.R
import com.kamolovproducts.aimovie.adapter.AdapterMoreDetails
import com.kamolovproducts.aimovie.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.view_trending_details.*

class TrendingMovies : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_trending_details)

        iv_back_arrow_more_details.setOnClickListener { finish() }

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.adapterM1 = AdapterMoreDetails(this, viewModel.resultsTrendingMore)
        recycler_view_more_details.adapter = viewModel.adapterM1
        val layoutManager = GridLayoutManager(this, 2)
        recycler_view_more_details.layoutManager = layoutManager
        viewModel.getTrendingMoviesMore()

//      RecyclerView Pagination
        recycler_view_more_details.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = viewModel.adapterM1.itemCount

                if ((visibleItemCount + pastVisibleItem) >= total) {
                    viewModel.pageNum++
                    viewModel.getTrendingMoviesMore()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

    }


}