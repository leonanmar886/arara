package com.example.arara.ui.screens.clothes

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arara.R
import com.example.arara.models.Clothes
import com.example.arara.models.Tag
import com.example.arara.services.ClothesService
import com.example.arara.services.TagsService
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

data class ClothesListErrorMessages(
    val search: Int = -1,
    val listClothes: Int = -1,
    val listTags: Int = -1,
    val general: Int = -1
)

data class ClothesDetails(
    val search: String = "",
    val dbClothes: List<Clothes> = emptyList(),
    val clothes: List<Clothes> = emptyList(),
    val tags: List<String> = emptyList(),
    val errorMessages: ClothesListErrorMessages = ClothesListErrorMessages()
)

class ClothesListViewModel(
    private val clothesService: ClothesService,
    private val tagsService: TagsService
) : ViewModel() {
    
    var clothesUiState by mutableStateOf(ClothesDetails())
        private set
    
    init {
        loadData()
    }
    
    fun loadData() {
        viewModelScope.launch {
            val getTagsJob = async { getTags() }
            val getClothesJob = async { getClothes() }
            
            try {
                val tags = getTagsJob.await()
                val clothes = getClothesJob.await()
                
                clothesUiState = clothesUiState.copy(
                    tags = tags,
                    clothes = clothes,
                    dbClothes = clothes
                )
                
                Log.d("ClothesListViewModel", "Tags: ${clothesUiState.tags}")
                Log.d("ClothesListViewModel", "Clothes: ${clothesUiState.clothes}")
            } catch (e: Exception) {
                clothesUiState = clothesUiState.copy(
                    errorMessages = ClothesListErrorMessages(
                        listClothes = R.string.error_clothes_list,
                        listTags = -1
                    )
                )
            }
        }
    }
    
    fun filterClothes(search: String) {
        val filteredClothes = clothesUiState.dbClothes.filter { clothes ->
            clothes.tags.any { tag ->
                tag.contains(search, ignoreCase = true)
            }
        }
        
        clothesUiState = clothesUiState.copy(
            clothes = filteredClothes
        )
    }
    
    private suspend fun getTags(): List<String> {
        return try {
            tagsService.getAllTags().map(Tag::name)
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    private suspend fun getClothes(): List<Clothes> {
        return try {
            clothesService.getAllClothes()
        } catch (e: Exception) {
            emptyList()
        }
    }
}
