package com.example.arara.ui.screens.clothes

import ScrollableRow
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.DeleteForever
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.arara.R
import com.example.arara.ui.AppViewModelProvider
import com.example.arara.ui.navigation.NavigationDestination


object ClothesDetailsDestination: NavigationDestination {
    override val route = "details"
    override val titleRes = R.string.clothesdetails_title
    const val routeWithArgs = "clothesDetails/{clothesId}"
    const val argClothesId = "clothesId"
}


@Composable
fun ClothesDetailsScreen(
    clothesId: String,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ClothesDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val clothesUiState = viewModel.clothesUiState
    
    viewModel.loadClothesDetails(clothesId)
    
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Logo(modifier = modifier)
                IconButton(
                    onClick = { navigateToHome() },
                    content = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                )
            }
        },
        bottomBar = {
            Row {
                Footer(modifier = modifier)
            }
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = clothesUiState.clothes.name,
                            color = Color.Black,
                            fontSize = 25.sp,
                            fontWeight = FontWeight(900),
                            fontFamily = FontFamily(Font(R.font.outfit_eb)),
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = {
                                    // TODO
                                },
                                modifier = Modifier.size(24.dp),
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Edit,
                                    contentDescription = "Editar"
                                )
                            }
                            IconButton(
                                onClick = {
                                    viewModel.deleteClothes()
                                    navigateToHome()
                                },
                                modifier = Modifier.size(24.dp),
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.DeleteForever,
                                    contentDescription = "Deletar"
                                )
                            }
                        }
                    }
                    val context = LocalContext.current
                    val imageLoader = remember(context) { ImageLoader(context) }
                    val request = remember(clothesUiState.clothes.imageURI) {
                        ImageRequest.Builder(context)
                            .data(clothesUiState.clothes.imageURI)
                            .build()
                    }
                    
                    AsyncImage(
                        model = request,
                        imageLoader = imageLoader,
                        contentDescription = clothesUiState.clothes.name,
                        modifier = modifier
                            .height(400.dp)
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = "Detalhes da Peça",
                            color = Color.Black,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Start,
                            fontFamily = FontFamily(Font(R.font.outfit_eb)),
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = "Descrição: ",
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight(900),
                            textAlign = TextAlign.Start,
                            fontFamily = FontFamily(Font(R.font.outfit_eb)),
                        )
                        Text(
                            text = clothesUiState.clothes.description,
                            color = Color.Black,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Start,
                            fontFamily = FontFamily(Font(R.font.outfit)),
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ){
                        Row(
                            modifier = Modifier.fillMaxWidth(0.5f),
                        ) {
                            Text(
                                text = "Tamanho: ",
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight(900),
                                textAlign = TextAlign.Start,
                                fontFamily = FontFamily(Font(R.font.outfit_eb)),
                            )
                            Text(
                                text = clothesUiState.clothes.size,
                                color = Color.Black,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Start,
                                fontFamily = FontFamily(Font(R.font.outfit)),
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Tags: ",
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight(900),
                            textAlign = TextAlign.Start,
                            fontFamily = FontFamily(Font(R.font.outfit_eb)),
                        )
                        Log.d("ClothesDetailsScreen", "Tags: ${clothesUiState.clothes.tags}")
                        ScrollableRow(
                            items = clothesUiState.clothes.tags
                        )
                    }
                }
            }
        }
    )
}