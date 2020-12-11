package com.wcisang.detail.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.CircleCropTransformation
import com.wcisang.customviews.GistCodeContentView
import com.wcisang.detail.R
import com.wcisang.domain.model.Gist
import com.wcisang.navigation.Screen
import kotlinx.android.synthetic.main.activity_gist_detail.*

class GistDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gist_detail)
        setupToolbar()
        getGistItem()
    }

    private fun setupToolbar() {
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.detail_title)
        }
    }

    private fun getGistItem() {
        val gist = intent.getParcelableExtra<Gist>(Screen.Detail.GIST_ITEM_KEY)
        tvItemOwnerName.text = gist?.owner?.login
        ivItemOwnerPhoto.load(gist?.owner?.avatarUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_person)
            error(R.drawable.ic_person)
            transformations(CircleCropTransformation())
        }

        gist?.files?.entries?.iterator()?.forEach {
            container.addView(
                GistCodeContentView(this).apply {
                    setGistCode(
                        GistCodeContentView.GistCode(
                            it.value.rawUrl,
                            it.value.filename
                        )
                    )
                }
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            onBackPressed()

        return super.onOptionsItemSelected(item)
    }
}
