package com.wcisang.domain.usecase

import com.wcisang.domain.model.Gist
import com.wcisang.domain.repository.GistRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteGistListUseCase(private val repository: GistRepository) {

    fun execute(): Flow<List<Gist>> {
        return repository.getFavoriteList()
    }
}
