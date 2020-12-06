package com.wcisang.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.view_gist_code_content.view.*

class GistCodeContentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.view_gist_code_content, this, true)
    }

    fun setGistCode(gistCode: GistCode) {
        tvGistCodeName.text= gistCode.fileName
        wvGistCode.settings.run {
            loadWithOverviewMode = true
        }
        wvGistCode.loadUrl(gistCode.url)
    }

    data class GistCode(val url: String, val fileName: String)
}