package com.wcisang.list.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.wcisang.domain.model.Gist
import com.wcisang.list.R
import com.wcisang.list.ui.adapter.GistLoadStateAdapter
import com.wcisang.list.ui.adapter.GistPagingAdapter
import com.wcisang.navigation.Navigation
import com.wcisang.navigation.Screen
import kotlinx.android.synthetic.main.activity_gist_list.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class GistListActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val viewModel by viewModel<GistListViewModel>()
    private val navigation: Navigation by inject()

    private var gistAdapter = GistPagingAdapter(
        {
            startDetail(it)
        },
        {
            addToFavorite(it)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gist_list)
        setupViews()
        getGistList()
    }

    private fun setupViews() {
        rvGistList.run {
            layoutManager = LinearLayoutManager(this@GistListActivity)
            adapter = gistAdapter.withLoadStateHeaderAndFooter(
                header = GistLoadStateAdapter { gistAdapter.retry() },
                footer = GistLoadStateAdapter { gistAdapter.retry() }
            )
            addItemDecoration(DividerItemDecoration(this.context, LinearLayout.VERTICAL))
        }

        gistAdapter.addLoadStateListener { loadState ->
            rvGistList.isVisible = loadState.refresh is LoadState.NotLoading
            pbMainLoading.isVisible = loadState.refresh is LoadState.Loading

            if (loadState.refresh is LoadState.Error) {
                showMainError()
                tvErrorMessage.text = (loadState.refresh as LoadState.Error).error.message
            }
        }

        searchView.setOnQueryTextListener(this)

        btMainRetry.setOnClickListener {
            hideMainError()
            gistAdapter.retry()
        }
    }

    private fun hideMainError() {
        group_error.isVisible = false
    }

    private fun showMainError() {
        group_error.isVisible = true
    }

    private fun getGistList(query: String? = "") {
        hideMainError()
        lifecycleScope.launch {
            viewModel.getGistList(query).collectLatest {
                gistAdapter.submitData(it)
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        getGistList(query)
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        getGistList(query)
        return false
    }

    private fun startDetail(holder: GistPagingAdapter.GistHolder) {
        val intent = navigation.getIntent(this, Screen.Detail).apply {
            putExtra(Screen.Detail.GIST_ITEM_KEY, holder.gistData)
        }
        val options =
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                *holder.getTransitionPairs()
            )
        startActivity(intent, options.toBundle())
    }

    private fun addToFavorite(gist: Gist) {
        viewModel.insertGistToFavorite(gist)
        Snackbar.make(root, getString(R.string.gist_add), Snackbar.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            startActivity(navigation.getIntent(this, Screen.Favorite))
        }
        return super.onOptionsItemSelected(item)
    }
}
