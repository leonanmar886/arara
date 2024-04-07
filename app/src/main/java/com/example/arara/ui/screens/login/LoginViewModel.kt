package com.example.arara.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.arara.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException

data class ErrorMessages(
  val email: Int = -1,
  val password: Int = -1,
  val general: Int = -1
)

data class LoginDetails(
  val email: String = "",
  val password: String = "",
  val isLoading: Boolean = false,
  val isLoggedId: Boolean = false,
  val errorMessages: ErrorMessages = ErrorMessages()
)

data class LoginState(
  val loginDetails: LoginDetails = LoginDetails(),
  val isLoginValid: Boolean = false
)

class LoginViewModel: ViewModel() {
  private var auth: FirebaseAuth = FirebaseAuth.getInstance()
  
  var loginUiState by mutableStateOf(LoginState())
    private set
  
  private fun validateInput(uiState: LoginDetails): Pair<Boolean, LoginDetails> {
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
    
    val newLoginDetails = uiState.copy(errorMessages = newErrorMessages)
    return Pair(isValid, newLoginDetails)
  }
  
  fun updateUiState(loginDetails: LoginDetails) {
    val (isValid, newLoginDetails) = validateInput(loginDetails)
    loginUiState = LoginState(loginDetails = newLoginDetails, isLoginValid = isValid)
  }
  
  fun checkUserLoggedIn() {
    if (auth.currentUser != null) {
      val newLoginDetails: LoginDetails = loginUiState.loginDetails.copy(isLoggedId = true)
      loginUiState = loginUiState.copy(loginDetails = newLoginDetails)
    }
  }
  
  fun login() {
    auth.signInWithEmailAndPassword(loginUiState.loginDetails.email, loginUiState.loginDetails.password)
      .addOnCompleteListener { task ->
        loginUiState = if (task.isSuccessful) {
          LoginState(loginDetails = loginUiState.loginDetails.copy(isLoggedId = true), isLoginValid = true)
        } else {
          val exception = task.exception
          if (exception is FirebaseAuthInvalidUserException) {
            LoginState(loginDetails = loginUiState.loginDetails.copy(errorMessages = ErrorMessages(email = R.string.error_not_registered_email)), isLoginValid = false)
          } else {
            LoginState(loginDetails = loginUiState.loginDetails.copy(errorMessages = ErrorMessages(general = R.string.error_with_credentials)), isLoginValid = false)
          }
        }
      }
  }
  
  private fun isValidEmail(email: String): Boolean {
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    return email.matches(emailPattern.toRegex())
  }
  
  
}

