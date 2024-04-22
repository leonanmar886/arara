package com.example.arara.ui.screens.user.register

data class ErrorMessages(
  val email: Int = -1,
  val password: Int = -1,
  val name: Int = -1,
  val bio: Int = -1,
  val photo: Int = -1,
  val general: Int = -1
)

data class UserRegisterDetails(
  val email: String = "",
  val password: String = "",
  val name: String = "",
  val bio: String = "",
  val photo: String = "",
  val isLoading: Boolean = false,
  val isRegistered: Boolean = false,
  val errorMessages: ErrorMessages = ErrorMessages()
)

class UserRegisterViewModel {

}