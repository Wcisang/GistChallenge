package com.wcisang.data.repository

import com.wcisang.data.local.dao.GistDao
import com.wcisang.data.local.mapper.mapToGist
import com.wcisang.data.local.mapper.mapToGistLocal
import com.wcisang.data.local.model.GistLocal
import com.wcisang.data.remote.GistService
import com.wcisang.domain.repository.GistRepository
import com.wcisang.testutils.GistDataFactory
import com.wcisang.testutils.coVerifyNever
import com.wcisang.testutils.coVerifyOnce
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GistRepositoryTest {

    private val service: GistService = mockk(relaxed = true)
    private val dao: GistDao = mockk(relaxed = true)
    private lateinit var repository: GistRepository

    @Before
    fun setUp() {
        repository = GistRepositoryImpl(service, dao)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `GIVEN empty query WHEN get list THEN return public list`() = runBlockingTest {
        val list = GistDataFactory.createGistList()
        coEvery { service.getGistList(any()) } returns list

        val returnedList = repository.getGistList(1, "")
        assertEquals(list, returnedList)

        coVerifyNever { service.getGistListByOwnerName(any(), any()) }
    }

    @Test
    fun `GIVEN query WHEN get list THEN return public list`() = runBlockingTest {
        val list = GistDataFactory.createGistList()
        coEvery { service.getGistListByOwnerName(any(), any()) } returns list

        val returnedList = repository.getGistList(1, "123")
        assertEquals(list, returnedList)

        coVerifyNever { service.getGistList(any()) }
    }

    @Test
    fun `GIVEN dao list WHEN load dao gist list THEN return as expected it`() = runBlockingTest {
        val gistLocal = getGistLocal()
        val gist = gistLocal.mapToGist()
        val flow = flow { emit(listOf(gistLocal)) }

        every { dao.getAllGist() } returns flow

        val returnedList = repository.getFavoriteList()

        returnedList.collect {
            assertEquals(it, listOf(gist))
        }
    }

    @Test
    fun `WHEN delete gist THEN call delete dao`() = runBlockingTest {
        val gist = GistDataFactory.createGist()

        repository.deleteFavoriteGist(gist)

        coVerifyOnce { dao.delete(gist.mapToGistLocal()) }
    }

    @Test
    fun `WHEN insert gist THEN call insert dao`() = runBlockingTest {
        val gist = GistDataFactory.createGist()

        repository.insertFavoriteGist(gist)

        coVerifyOnce {
            dao.insertAll(gist.mapToGistLocal())
        }
    }

    private fun getGistLocal() =
        GistLocal(
            uid = "123",
            ownerName = "will",
            ownerImage = "image"
        )
}
