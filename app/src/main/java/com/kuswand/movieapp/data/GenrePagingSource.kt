package com.kuswand.movieapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kuswand.movieapp.model.Movie

class GenrePagingSource(
    private val genreId: Int
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return try {
            val response = RetrofitInstance.api.getMovieByGenre(genreId, page)
            LoadResult.Page(
                data = response.results,
                prevKey = page -1,
                nextKey = page +1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }
}