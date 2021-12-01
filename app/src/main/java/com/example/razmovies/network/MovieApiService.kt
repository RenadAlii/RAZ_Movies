package com.example.razmovies.network

import com.example.razmovies.data.DetailsOfMovie
import com.example.razmovies.data.Response
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org"
private const val API_KEY = "0583f9ff20a39159f9d5114e1319ee2c"



var loggingInterceptor =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
var client: OkHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
// this is Retrofit builder to build and create a Retrofit object.
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface MovieApiService {

    // get the movies.
    @GET("/3/movie/{movieSort}?api_key=${API_KEY}")
    suspend fun getMovie(@Path("movieSort")movieSort:String): Response

    // get the movie details from the Url.
    @GET("/3/movie/{id}?api_key=${API_KEY}")
    suspend fun getSingleMovieDetails(@Path("id")movieId:String) :DetailsOfMovie

    @GET("/3/discover/movie?api_key=${API_KEY}")
    suspend fun getdiscover(@Query("sort_by")discover:String):Response

    @GET("/3/movie/popular?api_key=${API_KEY}")
    suspend fun filterMovie(@Query("with_genres")generId: Int):Response

}

object MovieApi {
    val retrofitService : MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java) }
}
