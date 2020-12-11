package com.wcisang.domain.repository

import com.wcisang.domain.model.Gist
import kotlinx.coroutines.flow.Flow

interface GistRepository {

    suspend fun getGistList(page: Int, query: String): List<Gist>

    fun getFavoriteList(): Flow<List<Gist>>

    suspend fun insertFavoriteGist(gist: Gist)

    suspend fun deleteFavoriteGist(gist: Gist)
}
