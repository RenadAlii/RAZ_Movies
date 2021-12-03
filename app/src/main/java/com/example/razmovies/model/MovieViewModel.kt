package com.example.razmovies.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.razmovies.data.ResultsItem
import com.example.razmovies.network.MovieApi
import kotlinx.coroutines.launch


enum class MovieApiStatus { LOADING, ERROR, DONE }

// enum to filter the movies on the category.
enum class MoviesOptions(val value :Int){
    ACTION(28),
    COMEDY(12),
    DRAMA(18),
    FAMILY(10751)
}
class MovieViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request.
    private val _status = MutableLiveData<MovieApiStatus>()
    // The external immutable LiveData for the request status.
    val status: LiveData<MovieApiStatus> = _status

    private val _moviesList = MutableLiveData<List<ResultsItem?>>()
    val moviesList: LiveData<List<ResultsItem?>> = _moviesList









    // fun to get the popular movie.
     fun getPopularMovies() {
        viewModelScope.launch {
            _status.value = MovieApiStatus.LOADING
            try {
                _moviesList.value = MovieApi.retrofitService.getMovie("popular").results!!
                _status.value = MovieApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MovieApiStatus.ERROR
                // to clear the RecyclerView.

            }
        }
    }


    // fun to get the now playing movie.
     fun getNowPlayingMovies() {
        viewModelScope.launch {
            _status.value = MovieApiStatus.LOADING
            try {
                _moviesList.value = MovieApi.retrofitService.getMovie("now_playing").results!!
                _status.value = MovieApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MovieApiStatus.ERROR
                // to clear the RecyclerView.

            }
        }
    }


    // fun to get the top_rated movie.
     fun getTopRatedMovies() {
        viewModelScope.launch {
            _status.value = MovieApiStatus.LOADING
            try {
                _moviesList.value = MovieApi.retrofitService.getMovie("top_rated").results!!
                _status.value = MovieApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MovieApiStatus.ERROR
                // to clear the RecyclerView.

            }
        }
    }

    // fun to get the upcoming movie.
     fun getUpcomingMovies() {
        viewModelScope.launch {
            _status.value = MovieApiStatus.LOADING
            try {

                _moviesList.value = MovieApi.retrofitService.getMovie("upcoming").results!!
                _status.value = MovieApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MovieApiStatus.ERROR
                // to clear the RecyclerView.

            }
        }

    }




    fun getMoviesdiscover(genreId: Int) {

        viewModelScope.launch {
            _status.value = MovieApiStatus.LOADING
            try {
                _moviesList.value = MovieApi.retrofitService.filterMovie(genreId).results!!
                _status.value = MovieApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MovieApiStatus.ERROR
            }
        }
    }

}
