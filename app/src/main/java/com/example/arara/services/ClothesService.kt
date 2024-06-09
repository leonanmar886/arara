package com.example.arara.services

import com.example.arara.data.repository.ClothesRepository
import com.example.arara.models.dto.ClotheCreationDTO
import com.example.arara.models.Clothes
import kotlinx.coroutines.tasks.await

class ClothesService(
  private val clothesRepository: ClothesRepository,
  private val cloudStorageService: CloudStorageService,
  private val profileService: ProfileService
) {
  fun addClothe(item: ClotheCreationDTO) {
    val path = "clothes_images/${item.imageURI.lastPathSegment}"
    cloudStorageService.uploadImage(item.imageURI, path)
    
    clothesRepository.add(item.toClothes(path))
  }
  
  suspend fun getAllClothes(): List<Clothes> {
    val profileLogged = profileService.getLoggedProfile() ?: return emptyList()
    val snapshot = clothesRepository.getAll(profileLogged.id).await()
    
    return snapshot.toObjects(Clothes::class.java).mapNotNull { clothes ->
      clothes?.apply {
        imageURI = cloudStorageService.getDownloadUrl(imageURI).toString()
      }
    }
  }
  
  fun getClothe(id: String): Clothes {
    var clothes: Clothes? = null
    clothesRepository.get(id).addOnSuccessListener {
      if (it != null) {
        clothes = it.toObject(Clothes::class.java)
      } else {
        println("No document found")
      }
    }.addOnFailureListener { exception ->
      println("Error getting document: $exception")
    }
    return clothes!!
  }
  
  fun updateClothe(item: Clothes) {
    clothesRepository.update(item)
  }
  
  fun deleteClothe(item: Clothes) {
    clothesRepository.delete(item)
  }
  
  fun searchByTags(tags: List<String>): List<Clothes> {
    var clothes: List<Clothes> = emptyList()
    clothesRepository.searchByTags(tags).addOnSuccessListener {
      if (it != null && !it.isEmpty) {
        clothes = it.toObjects(Clothes::class.java)
      } else {
        println("No documents found")
      }
    }
    return clothes
  }
}