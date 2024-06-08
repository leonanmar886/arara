package com.example.arara.ui.screens.clothes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arara.R
import com.example.arara.models.Clothes
import com.example.arara.services.ClothesService
import kotlinx.coroutines.launch

data class ErrorMessages(
    val search: Int = -1,
    val listClothes: Int = -1,
    val general: Int = -1
)

data class ClothesDetails(
    val search: String = "",
    val clothes: List<Clothes> = emptyList(),
    val errorMessages: ErrorMessages = ErrorMessages()
)

class ClothesListViewModel(private val clothesService: ClothesService) : ViewModel() {

    var clothesUiState by mutableStateOf(ClothesDetails())
        private set
    
    init {
        getClothes()
    }
    
    private fun getClothes() {
        viewModelScope.launch {
            clothesUiState = try {
                ClothesDetails(clothes = clothesService.getAllClothes())
            } catch (e: Exception) {
                ClothesDetails(errorMessages = ErrorMessages(listClothes = R.string.error_clothes_list))
            }
        }
    }

}