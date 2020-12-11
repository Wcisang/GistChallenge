package com.wcisang.detail.ui

import android.content.Intent
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.core.app.launchActivity
import com.wcisang.navigation.Screen
import com.wcisang.testutils.GistDataFactory
import com.wcisang.testutils.interactions.isDisplayed

class GistDetailActivityRobot {

    private val gist = GistDataFactory.createGist()

    fun launch(robotCommands: GistDetailActivityRobot.() -> Unit) {
        val intent = createIntent()
        val scenario = launchActivity<GistDetailActivity>(intent)
        scenario.onActivity {
            apply(robotCommands)
        }
    }

    private fun createIntent(): Intent {
        return Intent(getApplicationContext(), GistDetailActivity::class.java).apply {
            putExtra(Screen.Detail.GIST_ITEM_KEY, gist)
        }
    }

    fun verifyOwnerName() {
        gist.owner.login.isDisplayed()
    }

    fun verifyFileName() {
        val file = gist.files?.entries?.iterator()?.next()?.value
        file?.filename?.isDisplayed()
    }
}
