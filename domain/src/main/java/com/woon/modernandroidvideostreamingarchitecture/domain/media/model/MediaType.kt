package com.woon.modernandroidvideostreamingarchitecture.domain.media.model

import kotlin.text.uppercase

enum class MediaType(
    val value: String
) {
    VIDEO("VIDEO"),
    IMAGE("IMAGE");

    companion object {
        fun fromValue(value: String): MediaType {
            return when (value.uppercase()) {
                "VIDEO" -> VIDEO
                "IMAGE" -> IMAGE
                else -> throw kotlin.IllegalArgumentException("Invalid MediaType value: $value")
            }
        }
    }
}