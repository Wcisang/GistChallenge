package com.wcisang.gistchallenge.navigation

import android.app.Activity
import com.wcisang.detail.ui.GistDetailActivity
import com.wcisang.favorites.ui.GistFavoriteActivity
import com.wcisang.list.ui.GistListActivity
import com.wcisang.navigation.Screen

val navigationLinks: Map<Screen, Class<out Activity>> =
    hashMapOf<Screen, Class<out Activity>>(
        Screen.Detail to GistDetailActivity::class.java,
        Screen.List to GistListActivity::class.java,
        Screen.Favorite to GistFavoriteActivity::class.java
    )
