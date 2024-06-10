package com.example.arara.data.repository

import com.example.arara.data.FirestoreRepository
import com.example.arara.models.Profile
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class ProfileRepository: FirestoreRepository<Profile> {
  private val db = FirebaseFirestore.getInstance()
  private val profileCollection = db.collection("profile")
    override fun get(id: String): Task<DocumentSnapshot> {
      return profileCollection.document(id).get()
    }
  
    override fun add(item: Profile): Task<Void> {
      return profileCollection.document(item.id).set(item)
    }
  
    override fun update(item: Profile): Task<Void> {
      return profileCollection.document(item.id).set(item)
    }
  
    override fun delete(item: Profile): Task<Void> {
      return profileCollection.document(item.id).delete()
    }
  
    fun getAll(): Task<QuerySnapshot> {
      return profileCollection.get()
    }
  
    fun getByUserId(userId: String): Task<QuerySnapshot> {
      return profileCollection.whereEqualTo("userId", userId).get()
    }
}