package com.dutch.lazylist

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState

class ListItemPagingSource : PagingSource<Int, ListItemData>() {

    val TAG = "ListItemPagingSource"
    override fun getRefreshKey(state: PagingState<Int, ListItemData>): Int? {
        Log.i(TAG, "getRefreshKey()")
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)?:state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListItemData> {
        Log.i(TAG, "load()")
        val page = params.key ?: 0
        val pageSize = params.loadSize

        return try {
            val items = List(pageSize) {
                val index = page * pageSize + it
                ListItemData(
                    title = "Title $index",
                    subtitle = "Subtitle $index",
                    description = "Description $index",
                    date = "2025-05-${(it + 1)}",
                    author = "Author $index",
                    status = if (index %2 == 0) "Active" else "Inactive"
                )
            }
            LoadResult.Page(
                data = items,
                prevKey = if (page == 0) null else page - 1,
                nextKey = page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}