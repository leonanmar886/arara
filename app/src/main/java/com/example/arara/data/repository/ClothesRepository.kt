package com.example.arara.data.repository

import com.example.arara.data.FirestoreRepository
import com.example.arara.models.Clothes
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class ClothesRepository: FirestoreRepository<Clothes> {
  private val db = FirebaseFirestore.getInstance()
  private val clothesCollection = db.collection("clothes")
  
  override fun get(id: String): Task<DocumentSnapshot> {
    return clothesCollection.document(id).get()
  }
  
  override fun add(item: Clothes): Task<Void> {
    return clothesCollection.document(item.id.toString()).set(item)
  }
  
  override fun update(item: Clothes): Task<Void> {
    return clothesCollection.document(item.id.toString()).set(item)
  }
  
  override fun delete(item: Clothes): Task<Void> {
    return clothesCollection.document(item.id.toString()).delete()
  }
  
  fun getAll(): Task<QuerySnapshot> {
    return clothesCollection.get()
  }
  
  fun searchByName(name: String): Task<QuerySnapshot> {
    return clothesCollection.whereEqualTo("name", name).get()
  }
  
  
  
}