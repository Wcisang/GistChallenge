package com.wcisang.domain.usecase

import com.wcisang.domain.model.Gist
import com.wcisang.domain.repository.GistRepository

class InsertFavoriteGistUseCase(
    private val repository: GistRepository
) : UseCase<Unit, InsertFavoriteGistUseCase.Params> {

    data class Params(val gist: Gist)

    override suspend fun execute(params: Params) {
        repository.insertFavoriteGist(params.gist)
    }
}