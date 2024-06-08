package com.example.arara.data

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

interface FirestoreRepository<T> {
  fun add(item: T): Task<Void>
  fun update(item: T): Task<Void>
  fun delete(item: T): Task<Void>
  fun get(id: String): Task<DocumentSnapshot>
}