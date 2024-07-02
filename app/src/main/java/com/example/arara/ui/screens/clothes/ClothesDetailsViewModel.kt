package com.example.arara.ui.screens.clothes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.arara.models.Clothes
import com.example.arara.services.ClothesService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

data class ClothesState(
    val clothes: Clothes = Clothes(),
    val isLoading: Boolean = false,
    val error: String = ""
)

class ClothesDetailsViewModel(
    private val clothesService: ClothesService,
) : ViewModel() {
    
    var clothesUiState by mutableStateOf(ClothesState())
        private set
    
    fun loadClothesDetails(clothesId: String) {
        viewModelScope.launch {
            clothesUiState = clothesUiState.copy(isLoading = true)
            clothesUiState = clothesUiState.copy(
                clothes = clothesService.getClothe(clothesId),
                isLoading = false
            )
        }
    }
    
    fun deleteClothes() {
        viewModelScope.launch {
            clothesService.deleteClothe(clothesUiState.clothes)
        }
    }
}


