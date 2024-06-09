package com.example.arara.models.dto

import com.example.arara.models.Profile
import com.example.arara.models.User
import com.example.arara.utils.TimestampSerializer
import com.google.firebase.Timestamp
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileRequestDTO (
    val id: String,
    val name: String,
    val username: String,
    val fullName: String,
    val imageURI: String,
    @Serializable(with = TimestampSerializer::class)
    val birthdate: Timestamp,
    val userId: String
) {
    constructor() : this(
        id = "",
        name = "",
        username = "",
        fullName = "",
        imageURI = "",
        birthdate = Timestamp.now(),
        userId = ""
    )
    
    fun toProfile(user: User): Profile {
        return Profile(
            id = id,
            name = name,
            userName = username,
            fullName = fullName,
            imageUri = imageURI,
            birthDate = birthdate.toString(),
            user = user
        )
    }
}