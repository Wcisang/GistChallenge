package com.wcisang.gistchallenge.di

import com.wcisang.data.di.dataModule
import com.wcisang.detail.di.detailFeatureModule
import com.wcisang.domain.di.domainModule
import com.wcisang.favorites.di.favoritesFeatureModule
import com.wcisang.gistchallenge.navigation.navigationLinks
import com.wcisang.list.di.gistListFeatureModule
import com.wcisang.navigation.ui.navigationModule
import org.koin.dsl.module

val navigationLinksModule = module {
    single { navigationLinks }
}

val appModule = listOf(
    gistListFeatureModule,
    detailFeatureModule,
    favoritesFeatureModule,
    domainModule,
    dataModule,
    navigationModule,
    navigationLinksModule
)
