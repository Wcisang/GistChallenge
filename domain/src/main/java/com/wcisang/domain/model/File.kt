package com.wcisang.domain.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
data class File(
    @JsonProperty("filename") val filename: String,
    @JsonProperty("language") val language: String?,
    @JsonProperty("raw_url") val rawUrl: String,
    @JsonProperty("size") val size: Int,
    @JsonProperty("type") val type: String
) : Parcelable
