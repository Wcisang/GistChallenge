package com.wcisang.list.ui

import com.wcisang.data.remote.GistDataPagingSource
import com.wcisang.domain.usecase.InsertFavoriteGistUseCase
import com.wcisang.testutils.GistDataFactory
import com.wcisang.testutils.coVerifyOnce
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class GistListViewModelTest {

    private lateinit var viewModel: GistListViewModel
    private val pagingSource: GistDataPagingSource = mockk(relaxed = true)
    private val insertFavoriteGistUseCase: InsertFavoriteGistUseCase = mockk(relaxed = true)

    @Before
    fun setUp() {
        viewModel = GistListViewModel(pagingSource, insertFavoriteGistUseCase)
    }

    @Test
    fun `GIVEN a gist WHEN insert to favorite THEN call insert useCase`() = runBlockingTest {
        val gist = GistDataFactory.createGist()

        viewModel.insertGistToFavorite(gist)

        coVerifyOnce { insertFavoriteGistUseCase.execute(InsertFavoriteGistUseCase.Params(gist)) }
    }
}
