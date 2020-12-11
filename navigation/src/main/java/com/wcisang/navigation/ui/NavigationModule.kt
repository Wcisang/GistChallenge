package com.wcisang.navigation.ui

import com.wcisang.navigation.Navigation
import org.koin.dsl.module

val navigationModule = module {

    single {
        Navigation(
            links = get()
        )
    }
}
