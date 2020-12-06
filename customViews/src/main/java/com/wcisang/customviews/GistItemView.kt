package com.wcisang.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import coil.load
import coil.transform.CircleCropTransformation
import kotlinx.android.synthetic.main.view_gist_item.view.*
import androidx.core.util.Pair
import androidx.core.view.isVisible

class GistItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    interface GistItemListener {
        fun onGistClickListener()
        fun onFavoriteGistClickListener()
    }

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.view_gist_item, this, true)
    }

    fun setItem(item: GistItem, listener: GistItemListener) {
        setOnClickListener { listener.onGistClickListener() }
        tvItemGistType.text = item.type
        tvItemOwnerName.text = item.name
        ivItemOwnerPhoto.load(item.imageUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_person)
            error(R.drawable.ic_person)
            transformations(CircleCropTransformation())
        }
        tvItemGistType.isVisible = item.type.isNotEmpty()

        if (item.itemType is GistItemType.Delete) {
            ivAction.setImageResource(R.drawable.ic_delete)
        }
        ivAction.setOnClickListener { listener.onFavoriteGistClickListener() }
    }

    fun getTransitionPairs(): Array<Pair<View, String>> {
        return arrayOf(
            Pair(tvItemOwnerName as View, "ownerName"),
            Pair(ivItemOwnerPhoto as View, "ownerImage")
        )
    }

    sealed class GistItemType {
        object Normal : GistItemType()
        object Delete : GistItemType()
    }

    data class GistItem(
        val imageUrl: String,
        val name: String,
        val type: String,
        val itemType: GistItemType
    )
}