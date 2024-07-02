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
import com.example.arara.ui.screens.clothes.ClothesRegisterViewModel

object AppViewModelProvider {
  val Factory = viewModelFactory {
    initializer {
      LoginViewModel()
    }
    initializer {
      UserRegisterViewModel(
        araraApplication().container.profileService
      )
    }
    initializer {
      ClothesListViewModel(
        ClothesService(
          clothesRepository = araraApplication().container.clothesRepository,
          cloudStorageService = araraApplication().container.cloudStorageService,
          tagsService = araraApplication().container.tagsService,
          profileService = araraApplication().container.profileService,
        ),
        araraApplication().container.tagsService
      )
    }
    initializer {
      ClothesDetailsViewModel(
        ClothesService(
          clothesRepository = araraApplication().container.clothesRepository,
          cloudStorageService = araraApplication().container.cloudStorageService,
          tagsService = araraApplication().container.tagsService,
          profileService = araraApplication().container.profileService,
        ),
      )
    }
    initializer {
      ClothesRegisterViewModel(
        ClothesService(
          clothesRepository = araraApplication().container.clothesRepository,
          cloudStorageService = araraApplication().container.cloudStorageService,
          profileService = araraApplication().container.profileService,
          tagsService = araraApplication().container.tagsService
        ),
        araraApplication().container.tagsService
      )
    
    }
  }
}

fun CreationExtras.araraApplication(): AraraApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AraraApplication)