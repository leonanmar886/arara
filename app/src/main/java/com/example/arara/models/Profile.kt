package com.example.arara.models

data class Profile (
  val id: String,
  val name: String,
  val userName: String,
  val fullName: String,
  var imageUri: String,
  val birthDate: String,
  val user: User
)