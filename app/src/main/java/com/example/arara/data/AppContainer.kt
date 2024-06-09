package com.example.arara.data

import android.content.Context
import com.example.arara.data.repository.ClothesRepository
import com.example.arara.services.CloudStorageService

interface AppContainer {
  val clothesRepository: ClothesRepository
  val cloudStorageService: CloudStorageService
}

class AppDataContainer(private val context: Context) : AppContainer {
  override val clothesRepository: ClothesRepository by lazy {
    ClothesRepository()
  }

  override val cloudStorageService: CloudStorageService by lazy {
    CloudStorageService()
  }
}