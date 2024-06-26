package com.example.arara.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Clothes (
    val id: String,
    val name: String,
    val color: String,
    val size: String,
    val description: String,
    val tags: List<String>,
    var imageURI: String,
    var profile_id: String
) {
    constructor() : this(
        id = "",
        name = "",
        color = "",
        size = "",
        description = "",
        tags = emptyList(),
        imageURI = "",
        profile_id = ""
    )
}