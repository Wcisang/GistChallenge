package com.wcisang.domain.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
data class Owner(
    @JsonProperty("avatar_url") val avatarUrl: String,
    @JsonProperty("html_url") val htmlUrl: String? = null,
    @JsonProperty("id") val id: Int? = null,
    @JsonProperty("login") val login: String,
    @JsonProperty("url") val url: String? = null
) : Parcelable
