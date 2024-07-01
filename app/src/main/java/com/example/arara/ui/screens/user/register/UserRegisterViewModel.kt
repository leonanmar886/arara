package com.example.arara.ui.screens.user.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arara.R
import com.example.arara.models.Profile
import com.example.arara.models.User
import com.example.arara.services.ProfileService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class UserRegisterErrorMessages(
  val email: Int = -1,
  val password: Int = -1,
  val name: Int = -1,
  val profileName: Int = -1,
  val userName: Int = -1,
  val birthDate: Int = -1,
  val general: Int = -1
)

data class UserRegisterDetails(
  val email: String = "",
  val password: String = "",
  val confirmPassword: String = "",
  val profileName: String = "",
  val userName: String = "",
  val name: String = "",
  val birthDate: String = "",
  val isLoading: Boolean = false,
  val isRegistered: Boolean = false,
  val errorMessages: UserRegisterErrorMessages = UserRegisterErrorMessages()
)

data class UserRegisterState(
  val userRegisterDetails: UserRegisterDetails = UserRegisterDetails(),
  val isRegisterValid: Boolean = false
)

class UserRegisterViewModel(
  private val profileService: ProfileService
): ViewModel() {
  private var auth: FirebaseAuth = FirebaseAuth.getInstance()
  
  var registerUiState by mutableStateOf(UserRegisterState())
    private set
  
  private fun validateInput(uiState: UserRegisterDetails): Pair<Boolean, UserRegisterDetails> {
    var isValid = true
    var newErrorMessages = UserRegisterErrorMessages()
    
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
    
    if (uiState.profileName.isBlank()) {
      newErrorMessages = newErrorMessages.copy(profileName = R.string.error_invalid_profile_name_format)
      isValid = false
    }
    
    if (uiState.confirmPassword != uiState.password) {
      newErrorMessages = newErrorMessages.copy(password = R.string.error_passwords_do_not_match)
      isValid = false
    }
    
    if (uiState.userName.isBlank()) {
      newErrorMessages = newErrorMessages.copy(userName = R.string.error_invalid_user_name_format)
      isValid = false
    }
    
    if (uiState.birthDate.isBlank()) {
      newErrorMessages = newErrorMessages.copy(birthDate = R.string.error_invalid_birth_date_format)
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
    
    viewModelScope.launch {
      try {
        val result = auth.createUserWithEmailAndPassword(
          registerUiState.userRegisterDetails.email,
          registerUiState.userRegisterDetails.password
        ).await()
        
        if (result.user != null) {
          registerUiState = UserRegisterState(
            userRegisterDetails = registerUiState.userRegisterDetails.copy(isRegistered = true)
          )
          profileService.addProfile(
            Profile(
              id = result.user!!.uid,
              name = registerUiState.userRegisterDetails.name,
              userName = registerUiState.userRegisterDetails.userName,
              birthDate = registerUiState.userRegisterDetails.birthDate,
              imageUri = "",
              fullName = registerUiState.userRegisterDetails.profileName,
              user = result.user!!.let { User(it.uid, it.email ?: "") }
            )
          ).await()
          
        } else {
          UserRegisterState(
            userRegisterDetails = registerUiState.userRegisterDetails.copy(
              errorMessages = UserRegisterErrorMessages(general = R.string.error_register_failed)
            )
          )
        }
      } catch (e: Exception) {
        UserRegisterState(
          userRegisterDetails = registerUiState.userRegisterDetails.copy(
            errorMessages = UserRegisterErrorMessages(general = R.string.error_register_failed)
          )
        )
      }
    }
  }
  
  private fun isValidEmail(email: String): Boolean {
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    return email.matches(emailPattern.toRegex())
  }
}