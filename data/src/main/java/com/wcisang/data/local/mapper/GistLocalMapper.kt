package com.wcisang.data.local.mapper

import com.wcisang.data.local.model.GistLocal
import com.wcisang.domain.model.Gist
import com.wcisang.domain.model.Owner

fun Gist.mapToGistLocal() =
    GistLocal(
        uid = id,
        ownerName = owner.login,
        ownerImage = owner.avatarUrl
    )

fun GistLocal.mapToGist() =
    Gist(
        id = uid,
        owner = Owner(login = ownerName, avatarUrl = ownerImage)
    )