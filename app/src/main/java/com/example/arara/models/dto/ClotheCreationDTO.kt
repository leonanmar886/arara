package com.example.arara.models.dto

import android.net.Uri
import com.example.arara.models.Clothes
import java.util.UUID

data class ClotheCreationDTO(
    val name: String,
    val color: String,
    val size: String,
    val description: String,
    val tags: List<String>,
    val imageURI: Uri,
    var profile_id: String
) {
    fun toClothes(uri: String): Clothes {
        return Clothes(
            id = UUID.randomUUID().toString(),
            name = name,
            color = color,
            size = size,
            description = description,
            tags = tags,
            imageURI = uri,
            profile_id = profile_id
        )
    }
}