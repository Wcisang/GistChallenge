package com.wcisang.favorites.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.wcisang.domain.usecase.DeleteFavoriteGistUseCase
import com.wcisang.domain.usecase.GetFavoriteGistListUseCase
import com.wcisang.favorites.R
import com.wcisang.testutils.GistDataFactory
import com.wcisang.testutils.coVerifyOnce
import com.wcisang.testutils.interactions.isDisplayed
import com.wcisang.testutils.matcher.RecyclerViewMatcher
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import org.hamcrest.CoreMatchers.allOf
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class GistFavoriteActivityRobot {

    private lateinit var recyclerView: RecyclerView
    private val getFavoriteGistListUseCase: GetFavoriteGistListUseCase = mockk(relaxed = true)
    private val deleteFavoriteGistUseCase: DeleteFavoriteGistUseCase = mockk(relaxed = true)
    private val list = GistDataFactory.createGistList()

    fun launch(robotCommands: GistFavoriteActivityRobot.() -> Unit) {
        loadKoin()
        val scenario = launchActivity<GistFavoriteActivity>()
        scenario.onActivity {
            it.findViewById<RecyclerView>(R.id.rvGistFavorite).apply {
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
                factory { getFavoriteGistListUseCase }
                factory { deleteFavoriteGistUseCase }
                viewModel { GistFavoriteViewModel(get(), get()) }
            }
        )
    }

    fun mockSuccessList() {
        coEvery { getFavoriteGistListUseCase.execute() } returns flow { emit(list) }
    }

    fun clickDeleteInFirstItem() {
        getDeleteViewHolderReference(0).perform(ViewActions.click())
    }

    fun verifyDeleteIsCalled() {
        coVerifyOnce { deleteFavoriteGistUseCase.execute(any()) }
    }

    fun verifyDeleteMessageIsDisplayed() {
        "Gist removido dos favoritos!".isDisplayed()
    }

    fun verifyFirstItem() {
        val name = list[0].owner.login
        getViewHolderReference(0).check(descendantAssertion(name))
    }

    private fun getDeleteViewHolderReference(position: Int) =
        onView(
            allOf(
                isDescendantOfA(
                    RecyclerViewMatcher.withRecyclerView(
                        R.id.rvGistFavorite
                    ).atPosition(position)
                ),
                withId(R.id.ivAction)
            )
        )

    private fun getViewHolderReference(position: Int) =
        onView(RecyclerViewMatcher.withRecyclerView(R.id.rvGistFavorite).atPosition(position))

    private fun descendantAssertion(testingText: String): ViewAssertion? {
        return ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText(testingText)))
    }
}
