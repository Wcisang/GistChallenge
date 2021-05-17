package com.wcisang.domain.usecase

import com.wcisang.domain.repository.GistRepository
import com.wcisang.domain.utils.UseCaseTest
import com.wcisang.testutils.GistDataFactory
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetFavoriteGistListUseCaseTest : UseCaseTest {

    private val repository: GistRepository = mockk(relaxed = true)
    private lateinit var useCase: GetFavoriteGistListUseCase

    @Before
    fun setUp() {
        useCase = GetFavoriteGistListUseCase(repository)
    }

    @Test
    fun `WHEN get favorite list THEN call repository favorite list`() = runBlockingTest {
        val gists = GistDataFactory.createGistList()
        val flow = flow { emit(gists) }
        every { repository.getFavoriteList() } returns flow

        val flowReturned = useCase.execute()

        flowReturned.collect {
            assertEquals(it, gists)
        }
    }
}
