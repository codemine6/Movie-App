package com.kuswand.movieapp.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("poster_path")
    val posterPath: String
)