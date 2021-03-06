package com.kamolovproducts.aimovie.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kamolovproducts.aimovie.R
import com.kamolovproducts.aimovie.adapter.AdapterMoreDetails
import com.kamolovproducts.aimovie.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.view_top_rated.*

class TopRatedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_top_rated)

        iv_back_arrow_more_top_rated.setOnClickListener { finish() }

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.adapterM4 = AdapterMoreDetails(this, viewModel.resultsTopRatedMore)
        recycler_view_more_top_rated.adapter = viewModel.adapterM4
        val layoutManager = GridLayoutManager(this, 2)
        recycler_view_more_top_rated.layoutManager = layoutManager
        viewModel.getTopRatedMoviesMore()

        recycler_view_more_top_rated.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = viewModel.adapterM4.itemCount

                if ((visibleItemCount + pastVisibleItem) >= total) {
                    viewModel.pageNum++
                    viewModel.getTopRatedMoviesMore()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

    }
}