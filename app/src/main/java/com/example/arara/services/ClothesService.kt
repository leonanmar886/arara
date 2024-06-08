package com.example.arara.services

import com.example.arara.data.repository.ClothesRepository
import com.example.arara.models.ClotheCreationDTO
import com.example.arara.models.Clothes
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot

class ClothesService(
  private val clothesRepository: ClothesRepository,
  private val cloudStorageService: CloudStorageService
) {
  fun addClothe(item: ClotheCreationDTO): Unit {
    val path = "clothes_images/${item.imageURI.lastPathSegment}"
    cloudStorageService.uploadImage(item.imageURI, path)
    
    clothesRepository.add(item.toClothes(path))
  }
  
  fun getAllClothes(): List<Clothes> {
    var clothes: List<Clothes> = emptyList()
    clothesRepository.getAll().addOnSuccessListener {
      if (it != null && !it.isEmpty) {
        clothes = it.toObjects(Clothes::class.java)
      } else {
        println("No documents found")
      }
    }
    return clothes
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
  
  fun updateClothe(item: Clothes): Unit {
    clothesRepository.update(item)
  }
  
  fun deleteClothe(item: Clothes): Unit {
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