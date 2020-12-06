package com.wcisang.list.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wcisang.customviews.GistItemView
import com.wcisang.domain.model.Gist
import com.wcisang.list.R
import kotlinx.android.synthetic.main.item_gist_list.view.*

class GistPagingAdapter(
    private val clickListener: (GistHolder) -> Unit,
    private val favoriteClickListener: (Gist) -> Unit,
) : PagingDataAdapter<Gist, GistPagingAdapter.GistHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: GistHolder, position: Int) {
        val gist = getItem(position)
        gist?.let { holder.bindView(it, clickListener, favoriteClickListener) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistHolder {
        return GistHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_gist_list,
                parent,
                false
            )
        )
    }

    class GistHolder(view: View) : RecyclerView.ViewHolder(view) {

        lateinit var gistData: Gist

        fun bindView(
            gist: Gist,
            clickListener: (GistHolder) -> Unit,
            favoriteClickListener: (Gist) -> Unit,
        ) = with(itemView) {
            gistData = gist
            itemGist.setItem(
                gist.mapToGistItem(),
                object : GistItemView.GistItemListener {
                    override fun onGistClickListener() {
                        clickListener(this@GistHolder)
                    }

                    override fun onFavoriteGistClickListener() {
                        favoriteClickListener(gist)
                    }
                }
            )
        }

        private fun Gist.mapToGistItem() =
            GistItemView.GistItem(
                imageUrl = owner.avatarUrl,
                name = owner.login,
                type = getFirstFileType() ?: "",
                itemType = GistItemView.GistItemType.Normal
            )

        fun getTransitionPairs(): Array<Pair<View, String>> {
            return itemView.itemGist.getTransitionPairs()
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Gist>() {
            override fun areItemsTheSame(
                oldConcert: Gist,
                newConcert: Gist
            ) = oldConcert.id == newConcert.id

            override fun areContentsTheSame(
                oldConcert: Gist,
                newConcert: Gist
            ) = oldConcert == newConcert
        }
    }

}