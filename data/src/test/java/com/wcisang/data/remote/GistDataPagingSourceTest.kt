package com.wcisang.data.remote

import androidx.paging.PagingSource
import com.wcisang.domain.usecase.GetGistListUseCase
import com.wcisang.testutils.GistDataFactory
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GistDataPagingSourceTest {

    private val useCase: GetGistListUseCase = mockk(relaxed = true)
    private lateinit var gistDataPagingSource: GistDataPagingSource

    @Before
    fun setUp() {
        gistDataPagingSource = GistDataPagingSource(useCase)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `GIVEN gist list WHEN load data THEN return load page`() = runBlockingTest {
        val list = GistDataFactory.createGistList()
        coEvery { useCase.execute(any()) } returns list

        val load = gistDataPagingSource.load(PagingSource.LoadParams.Refresh(null, 1, false, 1))
        val loadPageData = (load as? PagingSource.LoadResult.Page)?.data

        assertEquals(loadPageData, list)
    }

    @Test
    fun `GIVEN IOException WHEN load data THEN return load error`() = runBlockingTest {
        val message = "error IOException"
        val exception = IOException(message)
        coEvery { useCase.execute(any()) }.throws(exception)

        val load = gistDataPagingSource.load(PagingSource.LoadParams.Refresh(null, 1, false, 1))
        val loadErrorMessage = (load as? PagingSource.LoadResult.Error)?.throwable?.message

        assertEquals(loadErrorMessage, message)
    }
}
