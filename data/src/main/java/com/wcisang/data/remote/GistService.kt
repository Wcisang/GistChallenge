package com.wcisang.data.remote

import com.wcisang.domain.model.Gist
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object NetworkConstants {
    const val BASE_URL = "https://api.github.com/"

    const val GIST_LIST_ENDPOINT = "gists/public"
    const val GIST_LIST_USER_ENDPOINT = "users/{username}/gists"
}

interface GistService {

    @GET(NetworkConstants.GIST_LIST_ENDPOINT)
    suspend fun getGistList(@Query("page") page: Int) : List<Gist>

    @GET(NetworkConstants.GIST_LIST_USER_ENDPOINT)
    suspend fun getGistListByOwnerName(@Path("username") query: String, @Query("page") page: Int) : List<Gist>
}