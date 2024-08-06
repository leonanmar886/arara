package com.example.arara.ui.screens.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arara.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

data class LoginErrorMessages(
  val email: Int = -1,
  val password: Int = -1,
  val general: Int = -1
)

data class LoginDetails(
  val email: String = "",
  val password: String = "",
  val isLoading: Boolean = false,
  val isLoggedId: Boolean = false,
  val errorMessages: LoginErrorMessages = LoginErrorMessages()
)

data class LoginState(
  val loginDetails: LoginDetails = LoginDetails(),
  val isLoginValid: Boolean = false,
)

class LoginViewModel: ViewModel() {
  private var auth: FirebaseAuth = FirebaseAuth.getInstance()
  
  var loginUiState by mutableStateOf(LoginState())
    private set
  
  var isLoading by mutableStateOf(false)
    private set
  
  private fun validateInput(uiState: LoginDetails): Pair<Boolean, LoginDetails> {
    var isValid = true
    var newErrorMessages = LoginErrorMessages()
    
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
  
  fun checkUserLoggedIn(): Boolean {
    isLoading = true
    if (auth.currentUser != null) {
      val newLoginDetails: LoginDetails = loginUiState.loginDetails.copy(isLoggedId = true)
      loginUiState = loginUiState.copy(loginDetails = newLoginDetails)
      isLoading = false
      return true
    }
    isLoading = false
    return false
  }
  
  fun login() {
    if (!loginUiState.isLoginValid) {
      return
    }
    isLoading = true
    
    viewModelScope.launch {
      auth.signInWithEmailAndPassword(loginUiState.loginDetails.email, loginUiState.loginDetails.password)
        .addOnSuccessListener {
          val newLoginDetails: LoginDetails = loginUiState.loginDetails.copy(isLoggedId = true)
          loginUiState = loginUiState.copy(loginDetails = newLoginDetails)
        }
        .addOnFailureListener {
          val newLoginDetails: LoginDetails = loginUiState.loginDetails.copy(
            errorMessages = LoginErrorMessages(general = R.string.error_with_credentials)
          )
          loginUiState = loginUiState.copy(loginDetails = newLoginDetails)
        }
        .addOnCompleteListener {
          isLoading = false
        }
    }
    Log.d("LoginViewModel", auth.currentUser.toString())
  }
  
  private fun isValidEmail(email: String): Boolean {
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    return email.matches(emailPattern.toRegex())
  }
  
  
}

