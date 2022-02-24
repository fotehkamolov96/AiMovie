package com.kamolovproducts.aimovie.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kamolovproducts.aimovie.R
import com.kamolovproducts.aimovie.adapter.AdapterMoreDetails
import com.kamolovproducts.aimovie.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.view_popular.*

class PopularActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_popular)

        iv_back_arrow_more_popular.setOnClickListener { finish() }

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.adapterM2 = AdapterMoreDetails(this, viewModel.resultsPopularMore)
        recycler_view_more_popular.adapter = viewModel.adapterM2
        val layoutManager = GridLayoutManager(this, 2)
        recycler_view_more_popular.layoutManager = layoutManager
        viewModel.getPopularMoviesMore()

        recycler_view_more_popular.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = viewModel.adapterM2.itemCount

                if ((visibleItemCount + pastVisibleItem) >= total) {
                    viewModel.pageNum++
                    viewModel.getPopularMoviesMore()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }
}