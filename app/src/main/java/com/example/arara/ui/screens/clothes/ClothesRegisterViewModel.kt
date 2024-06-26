package com.example.arara.ui.screens.clothes

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arara.models.Tag
import com.example.arara.models.dto.ClotheCreationDTO
import com.example.arara.services.ClothesService
import com.example.arara.services.TagsService
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class ClothesRegisterErrorMessages(
  val image: Int = -1,
  val title: Int = -1,
  val description: Int = -1,
  val size: Int = -1,
  val tags: Int = -1,
  val general: Int = -1
)

data class ClothesRegisterDetails(
  val image: String = "",
  val title: String = "",
  val description: String = "",
  val color: String = "",
  val size: String = "",
  val tags: List<String> = emptyList(),
  val tagsFromDb: List<String> = emptyList(),
  val tagsOptions: List<String> = emptyList(),
  val isLoading: Boolean = false,
  val isRegistered: Boolean = false,
  val errorMessages: ClothesRegisterErrorMessages = ClothesRegisterErrorMessages()
)

data class ClothesRegisterState(
  val clothesDetails: ClothesRegisterDetails = ClothesRegisterDetails(),
  val isRegisterValid: Boolean = false
)

class ClothesRegisterViewModel(
  private val clothesService: ClothesService,
  private val tagsService: TagsService
): ViewModel() {
  var clothesUiState by mutableStateOf(ClothesRegisterState())
    private set
  
  init {
    loadTags()
  }
  
  private fun validateInput(uiState: ClothesRegisterDetails): Pair<Boolean, ClothesRegisterDetails> {
    var isValid = true
    var newErrorMessages = ClothesRegisterErrorMessages()
    
    if (uiState.image.isBlank()) {
      newErrorMessages = newErrorMessages.copy(image = -1)
      isValid = false
    }
    
    if (uiState.title.isBlank()) {
      newErrorMessages = newErrorMessages.copy(title = -1)
      isValid = false
    }
    
    if (uiState.description.isBlank()) {
      newErrorMessages = newErrorMessages.copy(description = -1)
      isValid = false
    }
    
    if (uiState.size.isBlank()) {
      newErrorMessages = newErrorMessages.copy(size = -1)
      isValid = false
    }
    
    if (uiState.tags.isEmpty()) {
      newErrorMessages = newErrorMessages.copy(tags = -1)
      isValid = false
    }
    
    return Pair(isValid, uiState.copy(errorMessages = newErrorMessages))
  }
  
  fun updateUiState(uiState: ClothesRegisterDetails) {
    val (isValid, newUiState) = validateInput(uiState)
    clothesUiState = ClothesRegisterState(newUiState, isValid)
  }
  
  private fun loadTags(): Unit {
    viewModelScope.launch {
      val tags = tagsService.getAllTags().map(Tag::name)
      clothesUiState = ClothesRegisterState(
        clothesUiState.clothesDetails.copy(tagsOptions = tags, tagsFromDb = tags)
      )
    }
  }
  
  fun registerClothes(context: Context) {
    clothesUiState = ClothesRegisterState(
      clothesUiState.clothesDetails.copy(isLoading = true)
    )
    
    viewModelScope.launch {
      try {
        clothesService.addClothe(
          item = ClotheCreationDTO(
            name = clothesUiState.clothesDetails.title,
            color = clothesUiState.clothesDetails.color,
            size = clothesUiState.clothesDetails.size,
            description = clothesUiState.clothesDetails.description,
            tags = clothesUiState.clothesDetails.tags,
            imageURI = Uri.parse(clothesUiState.clothesDetails.image),
            profile_id = "profileId"
          ),
          presentTags = clothesUiState.clothesDetails.tagsFromDb
        )
        
        clothesUiState = ClothesRegisterState(
          clothesUiState.clothesDetails.copy(isLoading = false, isRegistered = true)
        )
        
      } catch (e: Exception) {
        Log.e("ClothesRegisterViewModel", "Error registering clothes", e)
        clothesUiState = ClothesRegisterState(
          clothesUiState.clothesDetails.copy(
            isLoading = false,
            errorMessages = ClothesRegisterErrorMessages(general = -1)
          )
        )
      }
    }
  }
}