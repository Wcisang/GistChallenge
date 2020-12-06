package com.wcisang.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object GistServiceFactory {

    fun makeOkHttpClient() =
        OkHttpClient
            .Builder()
            .build()

    fun makeGistService(okHttpClient: OkHttpClient): GistService {
        val retrofit = Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
        return retrofit.create(GistService::class.java)
    }
}