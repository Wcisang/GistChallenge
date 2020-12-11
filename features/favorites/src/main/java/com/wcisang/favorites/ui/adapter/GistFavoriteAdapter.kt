package com.wcisang.favorites.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wcisang.customviews.GistItemView
import com.wcisang.domain.model.Gist
import com.wcisang.favorites.R
import kotlinx.android.synthetic.main.item_gist_favorite_list.view.*

class GistFavoriteAdapter(
    private val favoriteClickListener: (Gist) -> Unit
) : RecyclerView.Adapter<GistFavoriteAdapter.GistViewHolder>() {

    var list = listOf<Gist>()

    fun submit(gistList: List<Gist>) {
        list = gistList
        notifyDataSetChanged()
    }

    class GistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(
            gist: Gist,
            favoriteClickListener: (Gist) -> Unit
        ) = with(itemView) {
            itemGist.setItem(
                gist.mapToGistItem(),
                object : GistItemView.GistItemListener {
                    override fun onGistClickListener() {}

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
                type = "",
                itemType = GistItemView.GistItemType.Delete
            )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistViewHolder {
        return GistViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_gist_favorite_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GistViewHolder, position: Int) {
        val item = list[position]
        holder.bindView(item, favoriteClickListener)
    }
}
