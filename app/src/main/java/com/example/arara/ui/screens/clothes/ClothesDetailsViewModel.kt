package com.example.arara.ui.screens.clothes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import android.util.Log
import com.example.arara.models.Clothes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ClothingApi {
    @PUT("clothing/{id}")
    fun putClothing(@Path("id") id: Int, @Body clotheUpdate: Clothes): Call<Clothes>

    @DELETE("clothing/{id}")
    fun deleteClothing(@Path("id") id: Int): Call<Clothes>

    @GET("clothing/{id}")
    fun getClothingByID(@Path("id") id: Int): Call<Clothes>
}

val api: ClothingApi = Retrofit.Builder()
    .baseUrl(" COLOCAR AQUI A URL DO BANCO ")
    .build()
    .create(ClothingApi::class.java)

fun updateClothing(clothes: Clothes) {
    clothingExists(clothes.id) { exists ->
        if (exists) {
            val call = api.putClothing(clothes.id, clothes)
            call.enqueue(object : Callback<Clothes> {
                override fun onResponse(call: Call<Clothes>, response: Response<Clothes>) {
                    if (response.isSuccessful) {
                        Log.d("UPDATE", "Peça de roupa atualizada com sucesso")
                    } else {
                        Log.e("UPDATE", "Erro ao atualizar peça de roupa")
                    }
                }

                override fun onFailure(call: Call<Clothes>, t: Throwable) {
                    Log.e("UPDATE", "Erro ao atualizar peça de roupa", t)
                }
            })
        } else {
            Log.e("UPDATE", "Peça de roupa não encontrada")
        }
    }
}

fun deleteClothing(id: Int) {
    clothingExists(id) { exists ->
        if (exists) {
            val call = api.deleteClothing(id)
            call.enqueue(object : Callback<Clothes> {
                override fun onResponse(call: Call<Clothes>, response: Response<Clothes>) {
                    if (response.isSuccessful) {
                        Log.d("DELETE", "Peça de roupa deletada com sucesso")
                    } else {
                        Log.e("DELETE", "Erro ao deletar peça de roupa")
                    }
                }

                override fun onFailure(call: Call<Clothes>, t: Throwable) {
                    Log.e("DELETE", "Erro ao deletar peça de roupa", t)
                }
            })
        } else {
            Log.e("DELETE", "Peça de roupa não encontrada")
        }
    }
}

fun clothingExists(id: Int, onResult: (Boolean) -> Unit) {
    val call = api.getClothingByID(id)

    call.enqueue(object : Callback<Clothes> {
        override fun onResponse(call: Call<Clothes>, response: Response<Clothes>) {
            onResult(response.isSuccessful)
        }

        override fun onFailure(call: Call<Clothes>, t: Throwable) {
            onResult(false)
        }
    })
}

class ClothesDetailsViewModel : ViewModel() {

    var clothesUiState by mutableStateOf(ClothesState())
        private set

}

