package com.example.razmovies

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.razmovies.adapter.MoviesAdapter
import com.example.razmovies.data.ResultsItem
import com.example.razmovies.model.MovieApiStatus

@BindingAdapter("imageUrl")
fun ImageView.bindImage( imageUrl: String?) {
    imageUrl?.let {
        val imgUri = "https://image.tmdb.org/t/p/w500/$imageUrl".toUri().buildUpon().scheme("https").build()
        this.load(imgUri){
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ResultsItem>?){
    val adapter= recyclerView.adapter as MoviesAdapter
    // this tell the RecyclerView new list is available.
    adapter.submitList(data)
}

@BindingAdapter("moviesApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: MovieApiStatus?) {
    when (status) {
        MovieApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MovieApiStatus.ERROR ->{
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MovieApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
