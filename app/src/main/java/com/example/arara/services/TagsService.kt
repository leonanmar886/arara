package com.example.arara.services

import com.example.arara.data.repository.TagsRepository
import com.example.arara.models.Tag
import kotlinx.coroutines.tasks.await

class TagsService(
	private val tagsRepository: TagsRepository
) {
		fun getTag(id: String) = tagsRepository.get(id)
		fun addTag(tag: Tag) = tagsRepository.add(tag)
		fun updateTag(tag: Tag) = tagsRepository.update(tag)
		fun deleteTag(tag: Tag) = tagsRepository.delete(tag)
		suspend fun getAllTags(): List<Tag> {
				val snapshot = tagsRepository.getAll().await()
				return snapshot.toObjects(Tag::class.java)
		}
		fun searchTagByName(name: String) = tagsRepository.searchByName(name)
}