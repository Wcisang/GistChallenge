package com.wcisang.favorites.di

import com.wcisang.favorites.ui.GistFavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritesFeatureModule = module {

    viewModel {
        GistFavoriteViewModel(
            deleteFavoriteGistUseCase = get(),
            getFavoriteGistListUseCase = get()
        )
    }
}
