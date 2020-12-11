package com.wcisang.domain.usecase

import com.wcisang.domain.model.Gist
import com.wcisang.domain.repository.GistRepository

class GetGistListUseCase(
    private val repository: GistRepository
) : UseCase<List<Gist>, GetGistListUseCase.Params> {

    data class Params(val page: Int, var query: String)

    override suspend fun execute(params: Params): List<Gist> {
        return repository.getGistList(params.page, params.query)
    }
}
