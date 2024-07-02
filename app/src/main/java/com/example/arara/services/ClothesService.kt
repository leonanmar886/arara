package com.example.arara.services

import com.example.arara.data.repository.ClothesRepository
import com.example.arara.models.Clothes
import com.example.arara.models.Tag
import com.example.arara.models.dto.ClotheCreationDTO
import kotlinx.coroutines.tasks.await

open class ClothesService(
  private val clothesRepository: ClothesRepository,
  private val cloudStorageService: CloudStorageService,
  private val tagsService: TagsService,
  private val profileService: ProfileService
) {
  suspend fun addClothe(item: ClotheCreationDTO, presentTags: List<String>) {
    val profileLogged = profileService.getLoggedProfile() ?: return
    
    item.profile_id = profileLogged.id
    
    val path = "clothes_images/${item.imageURI.lastPathSegment}"
    cloudStorageService.uploadImage(item.imageURI, path)
    
    item.tags.forEach { tag ->
      if (!presentTags.contains(tag)) {
        tagsService.addTag(Tag(name = tag)).await()
      }
    }
    
    clothesRepository.add(item.toClothes(path)).await()
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
  
  suspend fun getClothe(id: String): Clothes {
    var clothes: Clothes? = null
    val snapshot = clothesRepository.get(id).await()
    
    return snapshot.toObject(Clothes::class.java)?.apply {
      imageURI = cloudStorageService.getDownloadUrl(imageURI).toString()
    } ?: Clothes()
  }
  
  fun updateClothe(item: Clothes) {
    clothesRepository.update(item)
  }
  
  suspend fun deleteClothe(item: Clothes) {
    clothesRepository.delete(item).await()
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