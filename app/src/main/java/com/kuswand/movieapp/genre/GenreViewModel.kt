package com.kuswand.movieapp.genre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kuswand.movieapp.data.GenrePagingSource
import com.kuswand.movieapp.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GenreViewModel : ViewModel() {

    val movies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())

    init {
        getMovieByGenre(28)
    }

    fun getMovieByGenre(genreId: Int) {
        val pager = Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                GenrePagingSource(genreId)
            }
        ).flow

        viewModelScope.launch {
            pager.collect { result ->
                movies.value = result
            }
        }
    }
}