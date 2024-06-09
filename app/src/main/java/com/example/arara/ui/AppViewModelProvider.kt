package com.example.arara.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.arara.AraraApplication
import com.example.arara.services.ClothesService
import com.example.arara.ui.screens.clothes.ClothesDetailsViewModel
import com.example.arara.ui.screens.login.LoginViewModel
import com.example.arara.ui.screens.user.register.UserRegisterViewModel
import com.example.arara.ui.screens.clothes.ClothesListViewModel

object AppViewModelProvider {
  val Factory = viewModelFactory {
    initializer {
      LoginViewModel()
    }
    initializer {
      UserRegisterViewModel()
    }
    initializer {
      ClothesListViewModel(
        ClothesService(
          clothesRepository = araraApplication().container.clothesRepository,
          cloudStorageService = araraApplication().container.cloudStorageService,
          profileService = araraApplication().container.profileService,
        )
      )
    }
    initializer {
      ClothesDetailsViewModel()
    }
  }
}

fun CreationExtras.araraApplication(): AraraApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AraraApplication)