package com.wcisang.list.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.wcisang.data.remote.GistDataPagingSource
import com.wcisang.domain.usecase.GetGistListUseCase
import com.wcisang.domain.usecase.InsertFavoriteGistUseCase
import com.wcisang.list.R
import com.wcisang.list.ui.adapter.GistPagingAdapter
import com.wcisang.navigation.Navigation
import com.wcisang.navigation.Screen
import com.wcisang.testutils.GistDataFactory
import com.wcisang.testutils.interactions.click
import com.wcisang.testutils.interactions.isDisplayed
import com.wcisang.testutils.matcher.RecyclerViewMatcher
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.CoreMatchers.allOf
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.robolectric.Shadows
import org.robolectric.shadows.ShadowActivity
import java.io.IOException

class GistListActivityRobot {

    private val useCase: GetGistListUseCase = mockk(relaxed = true)
    private val insertFavoriteGistUseCase: InsertFavoriteGistUseCase = mockk(relaxed = true)
    private val navigation: Navigation = mockk(relaxed = true)
    private lateinit var recyclerView: RecyclerView
    private val list = GistDataFactory.createGistList()
    private lateinit var shadowActivity: ShadowActivity

    fun launch(robotCommands: GistListActivityRobot.() -> Unit) {
        loadKoin()
        val scenario = launchActivity<GistListActivity>()
        scenario.onActivity {
            shadowActivity = Shadows.shadowOf(it)
            it.findViewById<RecyclerView>(R.id.rvGistList).apply {
                recyclerView = this
                measure(0, 0)
                layout(0, 0, 400, 800)
            }
            apply(robotCommands)
        }
    }

    private fun loadKoin() {
        loadKoinModules(
            module {
                factory { useCase }
                factory { navigation }
                factory { insertFavoriteGistUseCase }
                factory { GistDataPagingSource(get()) }
                viewModel { GistListViewModel(get(), get()) }
            }
        )
    }

    fun mockSuccessList() {
        coEvery { useCase.execute(any()) } returns list
    }

    fun mockErrorList() {
        coEvery { useCase.execute(any()) } throws IOException(ERROR_MESSAGE)
    }

    fun clickInMainRetry() {
        R.id.btMainRetry.click()
    }

    fun verifyFirstItem() {
        val name = list[0].owner.login
        val type = list[0].getFirstFileType() ?: ""
        getViewHolderReference(0).check(descendantAssertion(name))
        getViewHolderReference(0).check(descendantAssertion(type))
    }

    fun clickInFirstItem() {
        Espresso.onView(withId(R.id.rvGistList))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<GistPagingAdapter.GistHolder>(
                    0, ViewActions.click()
                )
            )
    }

    fun clickFavoriteInFirstItem() {
        getFavoriteViewHolderReference(0).perform(click())
    }

    fun clickInFavoriteActionMenu() {
        shadowActivity.clickMenuItem(R.id.action_favorite)
    }

    fun verifyFavoriteMessageIsDisplayed() {
        "Gist adicionado aos favoritos!".isDisplayed()
    }

    fun verifyGroupError() {
        ERROR_MESSAGE.isDisplayed()
        R.id.btMainRetry.isDisplayed()
    }

    fun verifyNavigationDetailCall() {
        verify { navigation.getIntent(any(), Screen.Detail) }
    }

    fun verifyNavigationFavoriteCall() {
        verify { navigation.getIntent(any(), Screen.Favorite) }
    }

    private fun getFavoriteViewHolderReference(position: Int) =
        onView(
            allOf(
                isDescendantOfA(
                    RecyclerViewMatcher.withRecyclerView(R.id.rvGistList).atPosition(position)
                ),
                withId(R.id.ivAction)
            )
        )

    private fun getViewHolderReference(position: Int) =
        onView(RecyclerViewMatcher.withRecyclerView(R.id.rvGistList).atPosition(position))

    private fun descendantAssertion(testingText: String): ViewAssertion? {
        return ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText(testingText)))
    }

    companion object {
        private const val ERROR_MESSAGE = "ERRO AO CARREGAR"
    }
}
