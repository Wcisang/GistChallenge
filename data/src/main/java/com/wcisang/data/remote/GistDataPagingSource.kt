package com.wcisang.data.remote

import androidx.paging.PagingSource
import com.wcisang.domain.model.Gist
import com.wcisang.domain.usecase.GetGistListUseCase
import retrofit2.HttpException
import java.io.IOException

private const val CHARACTER_STARTING_PAGE_INDEX = 1

class GistDataPagingSource(
    private val getGistListUseCase: GetGistListUseCase
) : PagingSource<Int, Gist>() {

    var search: String = ""
        set(value) {
            refreshSearch = true
            field = value
        }

    private var refreshSearch = false

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gist> {
        val page = if (refreshSearch)
            CHARACTER_STARTING_PAGE_INDEX
        else params.key ?: CHARACTER_STARTING_PAGE_INDEX
        refreshSearch = false
        return try {
            val result = getGistListUseCase.execute(GetGistListUseCase.Params(page, search))
            LoadResult.Page(
                data = result,
                prevKey = if (page == CHARACTER_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (result.isNullOrEmpty()) null else page + 1
            )
        } catch (io: IOException) {
            LoadResult.Error(io)
        } catch (http: HttpException) {
            LoadResult.Error(http)
        }
    }
}
