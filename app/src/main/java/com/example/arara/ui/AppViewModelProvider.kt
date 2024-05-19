package com.example.arara.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.arara.AraraApplication
import com.example.arara.ui.screens.login.LoginViewModel
import com.example.arara.ui.screens.user.register.UserRegisterViewModel

object AppViewModelProvider {
  val Factory = viewModelFactory {
    initializer {
      LoginViewModel()
      UserRegisterViewModel()
    }
  }
}

fun CreationExtras.araraApplication(): AraraApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AraraApplication)