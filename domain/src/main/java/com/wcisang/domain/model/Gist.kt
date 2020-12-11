package com.wcisang.domain.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
data class Gist(
    @JsonProperty("created_at") val createdAt: String? = null,
    @JsonProperty("description") val description: String? = null,
    @JsonProperty("html_url") val htmlUrl: String? = null,
    @JsonProperty("id") val id: String,
    @JsonProperty("owner") val owner: Owner,
    @JsonProperty("url") val url: String? = null,
    @JsonProperty("files") val files: LinkedHashMap<String, File>? = null
) : Parcelable {

    fun getFirstFileType() = files
        ?.entries
        ?.iterator()
        ?.takeIf { it.hasNext() }
        ?.next()
        ?.value
        ?.type
}
