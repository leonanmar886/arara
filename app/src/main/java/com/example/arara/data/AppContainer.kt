package com.example.arara.data

import android.content.Context
import com.example.arara.data.repository.ClothesRepository
import com.example.arara.data.repository.ProfileRepository
import com.example.arara.data.repository.TagsRepository
import com.example.arara.services.CloudStorageService
import com.example.arara.services.ProfileService
import com.example.arara.services.TagsService

interface AppContainer {
  val clothesRepository: ClothesRepository
  val cloudStorageService: CloudStorageService
  val profileRepository: ProfileRepository
  val profileService: ProfileService
  val tagsRepository: TagsRepository
  val tagsService: TagsService
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
  
  override val tagsRepository: TagsRepository by lazy {
    TagsRepository()
  }
  
  override val tagsService: TagsService by lazy {
    TagsService(tagsRepository)
  }
}