package com.wcisang.favorites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wcisang.domain.model.Gist
import com.wcisang.domain.usecase.DeleteFavoriteGistUseCase
import com.wcisang.domain.usecase.GetFavoriteGistListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class GistFavoriteViewModel(
    private val getFavoriteGistListUseCase: GetFavoriteGistListUseCase,
    private val deleteFavoriteGistUseCase: DeleteFavoriteGistUseCase
) : ViewModel() {

    fun getFavoriteGistList() : Flow<List<Gist>> {
        return getFavoriteGistListUseCase.execute()
    }

    fun deleteGist(gist: Gist) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavoriteGistUseCase.execute(DeleteFavoriteGistUseCase.Params(gist))
        }
    }
}