package com.example.arara.services

import android.content.ContentResolver
import android.net.Uri
import androidx.core.net.toUri
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.io.File

class CloudStorageService {
  private val storage = FirebaseStorage.getInstance()
  private val storageRef = storage.reference
  
  fun uploadImage(imageURI: Uri?, path: String){
    val imageRef = storageRef.child(path)
    val uploadTask = imageURI?.let { imageRef.putFile(it) }
    
    uploadTask?.addOnFailureListener{
      println(it.message)
    }
  }
  
  suspend fun getDownloadUrl(path: String): Uri {
    val imageRef = storageRef.child(path)
    return imageRef.downloadUrl.await().normalizeScheme()
  }
}