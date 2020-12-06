package com.wcisang.testutils

import com.wcisang.domain.model.File
import com.wcisang.domain.model.Gist
import com.wcisang.domain.model.Owner

object GistDataFactory {

    fun createGistList() =
        listOf(
            createGist(),
            createGist()
        )

    fun createGist() =
        Gist(
            createdAt = "2020-12-03T22:47:42Z",
            description = "Advent of Code - Day 2",
            owner = createOwner(),
            htmlUrl = "https://gist.github.com/f4954e311361746dea37d0d3d003b93b",
            id = "f4954e311361746dea37d0d3d003b93b",
            url = "https://api.github.com/gists/f4954e311361746dea37d0d3d003b93b",
            files = createFiles()
        )

    fun createOwner() =
        Owner(
            avatarUrl = "https://avatars0.githubusercontent.com/u/11981152?v=4",
            htmlUrl = "https://github.com/wcisang",
            id = 11981152,
            login = "wCisang",
            url = "https://api.github.com/users/wcisang"
        )

    fun createFiles() =
        LinkedHashMap<String, File>().apply {
            put("file1", createFile())
        }

    fun createFile() =
        File(
            filename = "01.scm",
            language = "Text",
            rawUrl = "https://gist.githubusercontent.com/dutradda/c7315d3a6e08dce073fb24f95b1be4bd/raw/b9e89610a9d9a56a6e16f5aaa19e7efdb8a0fe25/publish_output",
            size =  244,
            type = "text/plain"
        )
}