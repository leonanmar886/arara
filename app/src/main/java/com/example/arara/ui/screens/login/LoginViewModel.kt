package com.example.arara.ui.screens.login

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class LoginState(
  val email: String = "",
  val password: String = "",
  val isLoading: Boolean = false,
  val isLoggedId: Boolean = false,
  val errorMessage: String = ""
)
class LoginViewModel: ViewModel() {
  private val _uiState = MutableStateFlow(LoginState())
  private lateinit var auth: FirebaseAuth
  
  val uiState: StateFlow<LoginState> = _uiState.asStateFlow()
  
  fun onPasswordChanged(password: String) {
    _uiState.value = _uiState.value.copy(password = password)
  }
  
  fun onEmailChanged(email: String) {
    if (isValidEmail(email)) {
      _uiState.value = _uiState.value.copy(email = email)
    } else {
      _uiState.value = _uiState.value.copy(errorMessage = "Invalid email format")
    }
  }
  
  fun checkUserLoggedIn() {
    auth = FirebaseAuth.getInstance()
    if (auth.currentUser != null) {
      _uiState.value = _uiState.value.copy(isLoggedId = true)
    }
  }
  
  fun login() {
    auth.signInWithEmailAndPassword(_uiState.value.email, _uiState.value.password)
      .addOnCompleteListener { task ->
        if (task.isSuccessful) {
          _uiState.value = _uiState.value.copy(isLoggedId = true)
        } else {
          _uiState.value = _uiState.value.copy(errorMessage = "Login failed")
        }
      }
  }
  
  private fun isValidEmail(email: String): Boolean {
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    return email.matches(emailPattern.toRegex())
  }
  
  
}

