package com.wcisang.domain.usecase

interface UseCase<T, in Params> {

    suspend fun execute(params: Params) : T
}