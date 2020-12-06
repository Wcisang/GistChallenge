package com.wcisang.favorites.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.wcisang.domain.model.Gist
import com.wcisang.favorites.R
import com.wcisang.favorites.ui.adapter.GistFavoriteAdapter
import kotlinx.android.synthetic.main.activity_gist_favorite.*
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.viewmodel.ext.android.viewModel

class GistFavoriteActivity : AppCompatActivity() {

    private val viewModel: GistFavoriteViewModel by viewModel()
    private val favoriteAdapter = GistFavoriteAdapter(
        favoriteClickListener = {
            deleteGist(it)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gist_favorite)
        setupToolbar()
        setupView()
        getFavoriteList()
    }

    private fun setupToolbar() {
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.favorite_title)
        }
    }

    private fun setupView() {
        rvGistFavorite.run {
            adapter = favoriteAdapter
            layoutManager = LinearLayoutManager(this@GistFavoriteActivity)
            addItemDecoration(DividerItemDecoration(this.context, LinearLayout.VERTICAL))
        }
    }

    private fun getFavoriteList() {
        lifecycleScope.launchWhenStarted {
            viewModel.getFavoriteGistList().collectLatest {
                favoriteAdapter.submit(it)
            }
        }
    }

    private fun deleteGist(gist: Gist) {
        viewModel.deleteGist(gist)
        Snackbar.make(root, getString(R.string.gist_remove), Snackbar.LENGTH_LONG).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}