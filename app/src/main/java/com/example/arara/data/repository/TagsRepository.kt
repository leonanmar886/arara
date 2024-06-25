package com.example.arara.data.repository

import com.example.arara.data.FirestoreRepository
import com.example.arara.models.Tag
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class TagsRepository: FirestoreRepository<Tag> {
	private val db = FirebaseFirestore.getInstance()
	private val tagsCollection = db.collection("tags")
	
	override fun get(id: String): Task<DocumentSnapshot> {
		return tagsCollection.document(id).get()
	}
	
	override fun add(item: Tag): Task<Void> {
		return tagsCollection.document(item.name).set(item)
	}
	
	override fun update(item: Tag): Task<Void> {
		return tagsCollection.document(item.name).set(item)
	}
	
	override fun delete(item: Tag): Task<Void> {
		return tagsCollection.document(item.name).delete()
	}
	
	fun getAll(): Task<QuerySnapshot> {
		return tagsCollection.get()
	}
	
	fun searchByName(name: String): Task<QuerySnapshot> {
		return tagsCollection.whereEqualTo("name", name).get()
	}
}