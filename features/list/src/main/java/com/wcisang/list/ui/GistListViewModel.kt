package com.wcisang.list.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wcisang.data.remote.GistDataPagingSource
import com.wcisang.domain.model.Gist
import com.wcisang.domain.usecase.InsertFavoriteGistUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class GistListViewModel(
    private val pagingSource: GistDataPagingSource,
    private val insertFavoriteGistUseCase: InsertFavoriteGistUseCase
) : ViewModel() {

    private val limit = 20

    private val pageConfig = PagingConfig(
        pageSize = limit,
        initialLoadSize = 15,
        enablePlaceholders = false
    )

    fun getGistList(query: String?): Flow<PagingData<Gist>> {
        pagingSource.search = query?: ""
        return Pager(
            config = pageConfig,
            pagingSourceFactory = { pagingSource }
        ).flow.cachedIn(viewModelScope)
    }

    fun insertGistToFavorite(gist: Gist) {
        viewModelScope.launch {
            insertFavoriteGistUseCase.execute(InsertFavoriteGistUseCase.Params(gist))
        }
    }

}
