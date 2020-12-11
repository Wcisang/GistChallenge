package com.wcisang.favorites.ui

import com.wcisang.domain.usecase.DeleteFavoriteGistUseCase
import com.wcisang.domain.usecase.GetFavoriteGistListUseCase
import com.wcisang.testutils.GistDataFactory
import com.wcisang.testutils.coVerifyOnce
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GistFavoriteViewModelTest {

    private lateinit var viewModel: GistFavoriteViewModel
    private val getFavoriteGistListUseCase: GetFavoriteGistListUseCase = mockk(relaxed = true)
    private val deleteFavoriteGistUseCase: DeleteFavoriteGistUseCase = mockk(relaxed = true)

    @Before
    fun setUp() {
        viewModel = GistFavoriteViewModel(getFavoriteGistListUseCase, deleteFavoriteGistUseCase)
    }

    @Test
    fun `GIVEN a gist WHEN delete it THEN call delete useCase`() {
        val gist = GistDataFactory.createGist()

        viewModel.deleteGist(gist)

        coVerifyOnce { deleteFavoriteGistUseCase.execute(DeleteFavoriteGistUseCase.Params(gist)) }
    }

    @Test
    fun `WHEN get favorite list THEN return as expected it`() = runBlockingTest {
        val list = GistDataFactory.createGistList()
        val flow = flow { emit(list) }

        coEvery { getFavoriteGistListUseCase.execute() } returns flow
        val flowList = viewModel.getFavoriteGistList()

        flowList.collect {
            assertEquals(it, list)
        }
    }
}
