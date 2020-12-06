package com.wcisang.domain.usecase

import com.wcisang.domain.repository.GistRepository
import com.wcisang.testutils.GistDataFactory
import com.wcisang.domain.utils.UseCaseTest
import io.mockk.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetGistListUseCaseTest : UseCaseTest {

    private val repository: GistRepository = mockk(relaxed = true)
    private lateinit var useCase: GetGistListUseCase

    @Before
    fun setUp() {
        useCase = GetGistListUseCase(repository)
    }

    @Test
    fun `GIVEN a list WHEN get a list THEN return a list`() = runBlockingTest {
        val list = GistDataFactory.createGistList()
        coEvery { repository.getGistList(any(), any()) } returns list

        val returnedList = useCase.execute(GetGistListUseCase.Params(1, ""))

        assertEquals(list, returnedList)
    }

}