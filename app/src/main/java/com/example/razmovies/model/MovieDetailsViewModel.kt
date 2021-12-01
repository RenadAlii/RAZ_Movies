package com.example.razmovies.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.razmovies.data.DetailsOfMovie
import com.example.razmovies.network.MovieApi
import kotlinx.coroutines.launch

class MovieDetailsViewModel : ViewModel() {


    private var _movieId = MutableLiveData<String?>()
    val movieId: LiveData<String?> = _movieId
    private val _movieDetails = MutableLiveData<DetailsOfMovie?>()
    val movieDetails: LiveData<DetailsOfMovie?> = _movieDetails




    // fun to set the movie id.
    fun setMovieId(id: String){
        _movieId.value = id
        getMovieDetails()
    }



    // fun to get the movie details.
     private fun getMovieDetails() {
        viewModelScope.launch {
            try {
                _movieDetails.value = MovieApi.retrofitService.getSingleMovieDetails(_movieId.value!!)
            } catch (e: Exception) {
            }
        }
    }



}
