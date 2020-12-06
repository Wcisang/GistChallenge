package com.wcisang.domain.usecase

import com.wcisang.domain.model.Gist
import com.wcisang.domain.repository.GistRepository

class DeleteFavoriteGistUseCase(
    private val repository: GistRepository
) : UseCase<Unit, DeleteFavoriteGistUseCase.Params> {

    data class Params(val gist: Gist)

    override suspend fun execute(params: Params) {
        repository.deleteFavoriteGist(params.gist)
    }
}