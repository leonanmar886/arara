package com.example.arara.models

data class Profile (
  val name: String,
  val userName: String,
  val fullName: String,
  val imageUri: String,
  val birthDate: String,
  val user: User
)