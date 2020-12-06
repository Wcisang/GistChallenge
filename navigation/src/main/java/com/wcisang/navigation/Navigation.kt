package com.wcisang.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import java.lang.IllegalArgumentException

class Navigation(
    private val links: Map<Screen, Class<out Activity>>
) {

    fun getIntent(context: Context, destination: Screen) : Intent{
        return Intent(context, find(destination))
    }

    private fun find(target: Screen) =
        links[target]
            ?.let { it }
            ?: throw IllegalArgumentException(target.toString())
}