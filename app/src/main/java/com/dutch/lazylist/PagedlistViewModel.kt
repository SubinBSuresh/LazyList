package com.dutch.lazylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class PagedlistViewModel : ViewModel() {
    val pager = Pager(
        config = PagingConfig(
            pageSize = 20, enablePlaceholders = false
        ), pagingSourceFactory = { ListItemPagingSource() }).flow.cachedIn(viewModelScope)
}