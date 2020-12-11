package com.wcisang.data.di

import androidx.room.Room
import com.wcisang.data.local.database.GistDataBase
import com.wcisang.data.remote.GistDataPagingSource
import com.wcisang.data.remote.GistServiceFactory
import com.wcisang.data.repository.GistRepositoryImpl
import com.wcisang.domain.repository.GistRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single {
        GistServiceFactory.makeOkHttpClient()
    }

    single {
        GistServiceFactory.makeGistService(
            okHttpClient = get()
        )
    }

    single<GistRepository> {
        GistRepositoryImpl(
            service = get(),
            dao = get()
        )
    }

    factory {
        GistDataPagingSource(
            getGistListUseCase = get()
        )
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            GistDataBase::class.java, "database-gist"
        ).build().gistDao()
    }
}
