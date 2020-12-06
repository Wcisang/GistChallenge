package com.wcisang.data.repository

import com.wcisang.data.local.dao.GistDao
import com.wcisang.data.local.mapper.mapToGist
import com.wcisang.data.local.mapper.mapToGistLocal
import com.wcisang.data.remote.GistService
import com.wcisang.domain.model.Gist
import com.wcisang.domain.repository.GistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class GistRepositoryImpl(
    private val service: GistService,
    private val dao: GistDao,
) : GistRepository {

    override suspend fun getGistList(page: Int, query: String): List<Gist> {
        return if (query.isEmpty()) {
            service.getGistList(page)
        }else {
            service.getGistListByOwnerName(query, page)
        }
    }

    override fun getFavoriteList(): Flow<List<Gist>> {
        return dao.getAllGist().distinctUntilChanged()
            .map { list -> list.map { it.mapToGist() } }
    }

    override suspend fun insertFavoriteGist(gist: Gist) {
        dao.insertAll(gist.mapToGistLocal())
    }

    override suspend fun deleteFavoriteGist(gist: Gist) {
        dao.delete(gist.mapToGistLocal())
    }
}