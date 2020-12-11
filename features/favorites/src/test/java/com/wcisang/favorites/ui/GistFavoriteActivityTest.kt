package com.wcisang.favorites.ui

import com.wcisang.testutils.RobolectricUnitTest
import org.junit.Test

class GistFavoriteActivityTest : RobolectricUnitTest() {

    private fun robot(func: GistFavoriteActivityRobot.() -> Unit) = GistFavoriteActivityRobot()
        .apply { func() }

    @Test
    fun `GIVEN local list WHEN load list THEN show list`() {
        robot {
            mockSuccessList()
            launch {
                verifyFirstItem()
            }
        }
    }

    @Test
    fun `GIVEN local list WHEN delete gist THEN delete message`() {
        robot {
            mockSuccessList()
            launch {
                clickDeleteInFirstItem()
                verifyDeleteIsCalled()
                verifyDeleteMessageIsDisplayed()
            }
        }
    }
}
