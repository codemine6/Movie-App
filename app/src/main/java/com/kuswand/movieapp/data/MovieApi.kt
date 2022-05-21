package com.kuswand.movieapp.data

import com.kuswand.movieapp.model.Result
import com.kuswand.movieapp.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie?api_key=$API_KEY")
    suspend fun getMovieByGenre(
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int
    ): Result
}