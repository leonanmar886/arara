package com.example.arara.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

data class ErrorMessages(
  val email: String = "",
  val password: String = "",
  val general: String = ""
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
  private lateinit var auth: FirebaseAuth
  var loginUiState by mutableStateOf(LoginState())
    private set
  
  private fun validateInput(uiState: LoginDetails): Pair<Boolean, LoginDetails> {
    var isValid = true
    var newErrorMessages = ErrorMessages()
    
    if (uiState.email.isNotBlank() && !isValidEmail(uiState.email)) {
      newErrorMessages = newErrorMessages.copy(email = "Formato de email inválido")
      isValid = false
    }
    
    if (uiState.password.isNotBlank() && uiState.password.length < 6) {
      newErrorMessages = newErrorMessages.copy(password = "A senha deve ter no mínimo 6 caracteres")
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
          LoginState(loginDetails = loginUiState.loginDetails.copy(isLoggedId = true))
        } else {
          LoginState(loginDetails = loginUiState.loginDetails.copy(errorMessages = ErrorMessages(general = "Login failed")))
        }
      }
  }
  
  private fun isValidEmail(email: String): Boolean {
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    return email.matches(emailPattern.toRegex())
  }
  
  
}

