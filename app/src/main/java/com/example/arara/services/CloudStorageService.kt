package com.example.arara.services

import android.net.Uri
import androidx.core.net.toUri
import com.google.firebase.storage.FirebaseStorage
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
  
  fun downloadImage(path: String){
    val imageRef = storageRef.child(path)
    val localFile = File.createTempFile("images", "jpg")
    
    imageRef.getFile(localFile).addOnSuccessListener {
      println("Image downloaded")
    }.addOnFailureListener{
      println(it.message)
    }
  }
}