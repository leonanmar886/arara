package com.example.arara.ui.screens.user.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.arara.R
import com.google.firebase.auth.FirebaseAuth

data class ErrorMessages(
  val email: Int = -1,
  val password: Int = -1,
  val name: Int = -1,
  val bio: Int = -1,
  val general: Int = -1
)

data class UserRegisterDetails(
  val email: String = "",
  val password: String = "",
  val name: String = "",
  val bio: String = "",
  val isLoading: Boolean = false,
  val isRegistered: Boolean = false,
  val errorMessages: ErrorMessages = ErrorMessages()
)

data class UserRegisterState(
  val userRegisterDetails: UserRegisterDetails = UserRegisterDetails(),
  val isRegisterValid: Boolean = false
)

class UserRegisterViewModel: ViewModel() {
  private var auth: FirebaseAuth = FirebaseAuth.getInstance()
  
  var registerUiState by mutableStateOf(UserRegisterState())
    private set
  
  private fun validateInput(uiState: UserRegisterDetails): Pair<Boolean, UserRegisterDetails> {
    var isValid = true
    var newErrorMessages = ErrorMessages()
    
    if (uiState.email.isNotBlank() && !isValidEmail(uiState.email)) {
      newErrorMessages = newErrorMessages.copy(email = R.string.error_invalid_email_format)
      isValid = false
    }
    
    if (uiState.password.isNotBlank() && uiState.password.length < 6) {
      newErrorMessages = newErrorMessages.copy(password = R.string.error_invalid_password_format)
      isValid = false
    }
    
    if (uiState.name.isBlank()) {
      newErrorMessages = newErrorMessages.copy(name = R.string.error_invalid_name_format)
      isValid = false
    }
    
    if (uiState.bio.length > 100) {
      newErrorMessages = newErrorMessages.copy(bio = R.string.error_invalid_bio_format)
      isValid = false
    }
    
    val newUserRegisterDetails = uiState.copy(errorMessages = newErrorMessages)
    return Pair(isValid, newUserRegisterDetails)
  }
  
  fun updateUiState(userRegisterDetails: UserRegisterDetails) {
    val (isValid, newUserRegisterDetails) = validateInput(userRegisterDetails)
    registerUiState = UserRegisterState(userRegisterDetails = newUserRegisterDetails, isRegisterValid = isValid)
  }
  
  fun register() {
    validateInput(registerUiState.userRegisterDetails)
    if (!registerUiState.isRegisterValid) return
    
    auth.createUserWithEmailAndPassword(registerUiState.userRegisterDetails.email, registerUiState.userRegisterDetails.password)
      .addOnCompleteListener { task ->
        registerUiState = if (task.isSuccessful) {
          UserRegisterState(userRegisterDetails = registerUiState.userRegisterDetails.copy(isRegistered = true))
        } else {
          UserRegisterState(userRegisterDetails = registerUiState.userRegisterDetails.copy(errorMessages = ErrorMessages(general = R.string.error_register_failed)))
        }
      }
  }
  
  private fun isValidEmail(email: String): Boolean {
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    return email.matches(emailPattern.toRegex())
  }
}