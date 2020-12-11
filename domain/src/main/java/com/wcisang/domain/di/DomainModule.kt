package com.wcisang.domain.di

import com.wcisang.domain.usecase.DeleteFavoriteGistUseCase
import com.wcisang.domain.usecase.GetFavoriteGistListUseCase
import com.wcisang.domain.usecase.GetGistListUseCase
import com.wcisang.domain.usecase.InsertFavoriteGistUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        GetGistListUseCase(
            repository = get()
        )
    }

    factory {
        GetFavoriteGistListUseCase(
            repository = get()
        )
    }

    factory {
        InsertFavoriteGistUseCase(
            repository = get()
        )
    }

    factory {
        DeleteFavoriteGistUseCase(
            repository = get()
        )
    }
}
