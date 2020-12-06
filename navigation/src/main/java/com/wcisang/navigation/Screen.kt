package com.wcisang.navigation

sealed class Screen {
    object List : Screen()
    object Detail : Screen() {
        const val GIST_ITEM_KEY = "GIST_ITEM_KEY"
    }
    object Favorite : Screen()
}