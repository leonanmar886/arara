package com.example.arara.models

import android.net.Uri
import java.util.UUID

data class ClotheCreationDTO(
    val name: String,
    val color: String,
    val size: String,
    val description: String,
    val tags: List<String>,
    val imageURI: Uri
) {
    fun toClothes(uri: String): Clothes {
        return Clothes(
            id = UUID.randomUUID().toString(),
            name = name,
            color = color,
            size = size,
            description = description,
            tags = tags,
            imageURI = uri
        )
    }
}