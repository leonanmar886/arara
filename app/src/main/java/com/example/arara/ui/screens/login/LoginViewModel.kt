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
  
  fun updateUiState(loginDetails: LoginDetails) {
    loginUiState = LoginState(loginDetails = loginDetails, isLoginValid = validateInput(loginDetails))
  }
  
  private fun validateInput(uiState: LoginDetails): Boolean {
    fun updateLoginState(field: (ErrorMessages) -> ErrorMessages): Boolean {
      val newErrorMessages = field(uiState.errorMessages.copy())
      val newLoginDetails = uiState.copy(errorMessages = newErrorMessages)
      loginUiState = loginUiState.copy(loginDetails = newLoginDetails)
      return false
    }
    
    if (!isValidEmail(uiState.email)) {
      return updateLoginState{ it.copy(email = "Invalid email") }
    }
    
    if (uiState.password.length < 6) {
      return updateLoginState { it.copy(password = "Password must be at least 6 characters") }
    }
    
    return true
  }
  
  fun checkUserLoggedIn() {
    auth = FirebaseAuth.getInstance()
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

