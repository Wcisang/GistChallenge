package com.wcisang.list.di

import com.wcisang.list.ui.GistListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val gistListFeatureModule = module {

    viewModel {
        GistListViewModel(
            pagingSource = get(),
            insertFavoriteGistUseCase = get()
        )
    }
}