package com.example.arara.ui.screens.clothes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.arara.ui.screens.login.ErrorMessages
import com.example.arara.ui.screens.login.LoginDetails
import com.example.arara.ui.screens.login.LoginState

data class ErrorMessages(
    val search: Int = -1
)

data class ClothesDetails(
    val search: String = "",
    val errorMessages: ErrorMessages = ErrorMessages()
)

data class ClothesState(
    val clothesDetails: ClothesDetails = ClothesDetails(),
)

class ClothesViewModel : ViewModel() {

    var clothesUiState by mutableStateOf(ClothesState())
        private set

}