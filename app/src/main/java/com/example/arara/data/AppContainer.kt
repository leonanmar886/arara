package com.example.arara.data

import android.content.Context
import com.example.arara.data.repository.ClothesRepository
import com.example.arara.data.repository.ProfileRepository
import com.example.arara.services.CloudStorageService
import com.example.arara.services.ProfileService

interface AppContainer {
  val clothesRepository: ClothesRepository
  val cloudStorageService: CloudStorageService
  val profileRepository: ProfileRepository
  val profileService: ProfileService
}

class AppDataContainer(private val context: Context) : AppContainer {
  override val clothesRepository: ClothesRepository by lazy {
    ClothesRepository()
  }

  override val cloudStorageService: CloudStorageService by lazy {
    CloudStorageService()
  }
  
  override val profileRepository: ProfileRepository by lazy {
    ProfileRepository()
  }
  
  override val profileService: ProfileService by lazy {
    ProfileService(profileRepository, cloudStorageService)
  }
}