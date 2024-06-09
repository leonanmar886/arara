package com.example.arara.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileRequestDTO (
    val name: String,
    val userName: String,
    @SerialName("full_name")
    val fullName: String,
    val imageUri: String,
    val birthDate: String,
    @SerialName("user_id")
    val userId: String
)