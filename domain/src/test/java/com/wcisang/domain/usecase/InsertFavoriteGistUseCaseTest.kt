package com.wcisang.domain.usecase

import com.wcisang.domain.repository.GistRepository
import com.wcisang.testutils.GistDataFactory
import com.wcisang.testutils.coVerifyOnce
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class InsertFavoriteGistUseCaseTest {

    private val repository: GistRepository = mockk(relaxed = true)
    private lateinit var useCase: InsertFavoriteGistUseCase

    @Before
    fun setUp() {
        useCase = InsertFavoriteGistUseCase(repository)
    }

    @Test
    fun `WHEN insert gist THEN call repository insert`() = runBlockingTest {
        val gist = GistDataFactory.createGist()

        useCase.execute(InsertFavoriteGistUseCase.Params(gist))

        coVerifyOnce { repository.insertFavoriteGist(gist) }
    }
}