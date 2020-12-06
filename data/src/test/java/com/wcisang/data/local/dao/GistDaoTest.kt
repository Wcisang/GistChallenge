package com.wcisang.data.local.dao

import android.content.Context

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wcisang.data.local.database.GistDataBase
import com.wcisang.data.local.model.GistLocal
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import org.junit.After
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.io.IOException
import kotlin.jvm.Throws

@FlowPreview
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(sdk = [28])
class GistDaoTest {

    private lateinit var gistDao: GistDao
    private lateinit var db: GistDataBase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, GistDataBase::class.java
        ).allowMainThreadQueries().build()

        gistDao = db.gistDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun `GIVEN gist data in database WHEN load gist list THEN get correctly list`() = runBlocking {

        val gist = getGistLocal()
        gistDao.insertAll(gist)

        val gists = async { gistDao.getAllGist().take(1).toList() }

        assertThat(gists.await()[0]).contains(gist)
    }

    @Test
    @Throws(Exception::class)
    fun `GIVEN gist data in database WHEN insert same gist and load list THEN get correctly list`() {
        runBlocking {
            val gist = getGistLocal()
            gistDao.insertAll(gist)
            gistDao.insertAll(gist)

            val gists = async { gistDao.getAllGist().take(1).toList() }

            assertThat(gists.await()[0]).containsExactly(gist)
        }
    }

    @Test
    @Throws(Exception::class)
    fun `GIVEN gist data in database WHEN delete gist and load list THEN get a list without gist`() {
        runBlocking {
            val gist = getGistLocal()
            gistDao.insertAll(gist)

            val gists = async { gistDao.getAllGist().take(1).toList() }
            gistDao.delete(gist)

            assertThat(gists.await()[0]).isEmpty()
        }
    }

    private fun getGistLocal() =
        GistLocal(
            uid = "123",
            ownerName = "will",
            ownerImage = "image"
        )
}