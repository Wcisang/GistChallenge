package com.wcisang.list.ui

import com.wcisang.testutils.RobolectricUnitTest
import org.junit.Test

class GistListActivityTest : RobolectricUnitTest() {

    private fun robot(func: GistListActivityRobot.() -> Unit) = GistListActivityRobot()
        .apply { func() }

    @Test
    fun `GIVEN success list WHEN load list THEN show list`() {
        robot {
            mockSuccessList()
            launch {
                verifyFirstItem()
            }
        }
    }

    @Test
    fun `GIVEN success list WHEN click in first item THEN call detail`() {
        robot {
            mockSuccessList()
            launch {
                clickInFirstItem()
                verifyNavigationDetailCall()
            }
        }
    }

    @Test
    fun `GIVEN success list WHEN click in favorite item THEN show success message`() {
        robot {
            mockSuccessList()
            launch {
                clickFavoriteInFirstItem()
                verifyFavoriteMessageIsDisplayed()
            }
        }
    }

    @Test
    fun `WHEN click in favorite menu action THEN call favorite`() {
        robot {
            mockSuccessList()
            launch {
                clickInFavoriteActionMenu()
                verifyNavigationFavoriteCall()
            }
        }
    }

    @Test
    fun `GIVEN request error WHEN load list for the first time THEN error group`() {
        robot {
            mockErrorList()
            launch {
                verifyGroupError()
            }
        }
    }

    @Test
    fun `GIVEN request error WHEN load list for the first time and successful retry THEN show list`() {
        robot {
            mockErrorList()
            launch {
                verifyGroupError()
                mockSuccessList()
                clickInMainRetry()
                verifyFirstItem()
            }
        }
    }
}
