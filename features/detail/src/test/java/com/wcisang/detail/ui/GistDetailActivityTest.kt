package com.wcisang.detail.ui

import com.wcisang.testutils.RobolectricUnitTest
import org.junit.Test

class GistDetailActivityTest : RobolectricUnitTest() {

    private fun robot(func: GistDetailActivityRobot.() -> Unit) = GistDetailActivityRobot()
        .apply { func() }

    @Test
    fun `GIVEN gist intent WHEN load screen THEN show fields correctly`() {
        robot {
            launch {
                verifyOwnerName()
                verifyFileName()
            }
        }
    }
}
