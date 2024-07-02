package com.example.arara.services

import com.example.arara.data.repository.ProfileRepository
import com.example.arara.models.Profile
import com.example.arara.models.User
import com.example.arara.models.dto.ProfileRequestDTO
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class ProfileService(
  private val profileRepository: ProfileRepository,
  private val cloudStorageService: CloudStorageService
) {
  fun getProfile(id: String) = profileRepository.get(id)
  suspend fun addProfile(profile: Profile) = profileRepository.add(profile).await()
  fun updateProfile(profile: Profile) = profileRepository.update(profile)
  fun deleteProfile(profile: Profile) = profileRepository.delete(profile)
  fun getAllProfiles() = profileRepository.getAll()
  fun logout() = FirebaseAuth.getInstance().signOut()
  suspend fun getLoggedProfile(): Profile? {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val userId = auth.currentUser?.uid ?: return null
    
    val snapshot = profileRepository.getByUserId(userId).await()
    return snapshot.toObjects(ProfileRequestDTO::class.java).firstOrNull()
      ?.toProfile(
        User(
          id = userId,
          email = auth.currentUser?.email ?: ""
        )
      )
//      ?.apply {
//        imageUri = cloudStorageService.getDownloadUrl(imageUri).toString()
//      }
    }
}