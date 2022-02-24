package com.kamolovproducts.aimovie.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.kamolovproducts.aimovie.model.MovieDetails
import com.kamolovproducts.aimovie.R
import com.kamolovproducts.aimovie.ui.views.DetailsActivity

class MovieAdapter(private val context: Context, private val movies: List<MovieDetails>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val moviePoster: ImageView = itemView.findViewById(R.id.iv_movie_poster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        val poster = movie.poster_path
        val imgUrl = "https://image.tmdb.org/t/p/original$poster"
        Glide.with(context)
            .load(imgUrl)
            .placeholder(R.drawable.ai_no_connection)
            .error(R.drawable.ai_no_connection)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.moviePoster)



        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("movie_poster", imgUrl)
            intent.putExtra("movie_title", movie.title)
            intent.putExtra("movie_release_date", movie.release_date)
            intent.putExtra("movie_rating", movie.vote_average)
            intent.putExtra("movie_overview", movie.overview)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

}