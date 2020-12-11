package com.wcisang.domain.usecase

import com.wcisang.domain.repository.GistRepository
import com.wcisang.testutils.GistDataFactory
import com.wcisang.testutils.coVerifyOnce
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class DeleteFavoriteGistUseCaseTest {

    private val repository: GistRepository = mockk(relaxed = true)
    private lateinit var useCase: DeleteFavoriteGistUseCase

    @Before
    fun setUp() {
        useCase = DeleteFavoriteGistUseCase(repository)
    }

    @Test
    fun `WHEN delete gist THEN call repository delete`() = runBlockingTest {
        val gist = GistDataFactory.createGist()

        useCase.execute(DeleteFavoriteGistUseCase.Params(gist))

        coVerifyOnce { repository.deleteFavoriteGist(gist) }
    }
}
