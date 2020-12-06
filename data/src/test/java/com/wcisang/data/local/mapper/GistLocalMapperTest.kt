package com.wcisang.data.local.mapper

import com.wcisang.data.local.model.GistLocal
import com.wcisang.testutils.GistDataFactory
import junit.framework.Assert.assertEquals
import org.junit.Test

class GistLocalMapperTest {

    @Test
    fun `GIVEN object gist WHEN map to gist local THEN map correctly`() {
        val gist = GistDataFactory.createGist()

        val gistLocal = gist.mapToGistLocal()

        assertEquals(gist.id, gistLocal.uid)
        assertEquals(gist.owner.login, gistLocal.ownerName)
        assertEquals(gist.owner.avatarUrl, gistLocal.ownerImage)
    }

    @Test
    fun `GIVEN object gist local WHEN map to gist THEN map correctly`() {
        val gistLocal = getGistLocal()

        val gist = gistLocal.mapToGist()

        assertEquals(gist.id, gistLocal.uid)
        assertEquals(gist.owner.login, gistLocal.ownerName)
        assertEquals(gist.owner.avatarUrl, gistLocal.ownerImage)
    }

    private fun getGistLocal() =
        GistLocal(
            uid = "123",
            ownerName = "wcisang",
            ownerImage = "image"
        )
}