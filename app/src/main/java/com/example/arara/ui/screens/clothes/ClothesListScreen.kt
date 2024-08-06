package com.example.arara.ui.screens.clothes

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.arara.R
import com.example.arara.models.Clothes
import com.example.arara.ui.AppViewModelProvider
import com.example.arara.ui.components.LoadingComponent
import com.example.arara.ui.navigation.NavigationDestination

object ClothesDestination: NavigationDestination {
    override val route = "clothes"
    override val titleRes = R.string.clothes_title
}

@Composable
fun ClothesListScreen(
    navigateToDetails: (id: String) -> Unit,
    navigateToRegister: () -> Unit,
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ClothesListViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.loadData()
            }
        }
        
        lifecycleOwner.lifecycle.addObserver(observer)
        
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    
    val clothesUiState = viewModel.clothesUiState
    val clothesList = clothesUiState.clothes
    
    Log.d("ClothesListScreen", "Tags: ${clothesUiState.tags}")
    
    val isLoading by viewModel::isLoading
    
    if (isLoading) {
        LoadingComponent()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Logo(modifier = modifier)
                IconButton(
                    onClick = {
                        viewModel.logout()
                        navigateToLogin()
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ExitToApp,
                        contentDescription = "Sair da conta"
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp, end = 25.dp)
                ) {
                    var text by remember { mutableStateOf("") }
                    SearchField(
                        value = text,
                        onValueChange = {
                            text = it
                            viewModel.filterClothes(it)
                        },
                        label = "Buscar por tags",
                        errorMessage = "",
                        aboutIcon = {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Busque suas roupas",
                            )
                        },
                        modifier = Modifier,
                        options = clothesUiState.tags
                    )
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF00B8A9))
                    ) {
                        Text(
                            text = "BUSCAR",
                            fontSize = 12.sp
                        )
                    }
                    IconButton(
                        onClick = { navigateToRegister() },
                        modifier = Modifier.size(24.dp),
                        content = {
                            Icon(
                                imageVector = Icons.Outlined.AddBox,
                                contentDescription = "Criar Nova Roupa"
                            )
                        }
                    )
                }
    
                Text(
                    text = "Todas as roupas",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.quicksand)),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 28.dp, top = 10.dp, end = 11.dp, bottom = 10.dp)
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp, 0.dp)
                        .background(color = Color.White)
                ) {
                    items(clothesList) { clothes ->
                        ImageCard(clothes = clothes, modifier = modifier, navigateToDetails = navigateToDetails)
                    }
                }
                Footer(modifier = Modifier)
            }
        }
    }
}


@Composable
fun Logo(
    modifier: Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(start = 10.dp,top = 16.dp, bottom = 20.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = modifier
                .size(45.dp)
                .fillMaxWidth()
        )
        Text(
            text = "Minha Arara",
            color = Color.Black,
            fontSize = 27.sp,
            fontFamily = FontFamily(Font(R.font.bubbler_one))
        )
    }
}


@Composable
fun ImageCard(
    clothes: Clothes,
    modifier: Modifier,
    navigateToDetails: (id: String) -> Unit,
) {
    val context = LocalContext.current
    val imageLoader = remember(context) { ImageLoader(context) }
    val request = remember(clothes.imageURI) {
        ImageRequest.Builder(context)
            .data(clothes.imageURI)
            .build()
    }
    
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Card(
            modifier = modifier
                .shadow(2.dp, shape = RoundedCornerShape(12.dp))
                .size(width = 150.dp, height = 150.dp)
                .clickable { navigateToDetails(clothes.id) }
        ) {
            AsyncImage(
                model = request,
                imageLoader = imageLoader,
                contentDescription = clothes.name,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .background(color = Color.White)
                    .fillMaxSize()
            )
        }
        Text(
            text = clothes.name,
            fontFamily = FontFamily(Font(R.font.quicksand)),
            textAlign = TextAlign.Center,
            modifier = modifier
                .padding(bottom = 8.dp)
            )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    value: String,
    onValueChange: (String) -> Unit,
    aboutIcon: @Composable (() -> Unit)? = {},
    label: String? = null,
    visualTransformation: VisualTransformation? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    errorMessage: String,
    multiline: Boolean? = false,
    options: List<String>,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Tamanho") }
    
    val filteredOptions = remember(value, options) {
        options.filter { it.contains(value, ignoreCase = true) }
    }
    
    Column {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = modifier
                .width(200.dp)
                .background(Color.Transparent)
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = {
                    onValueChange(it)
                    expanded = true
                },
                visualTransformation = visualTransformation ?: VisualTransformation.None,
                keyboardOptions = keyboardOptions,
                isError = errorMessage.isNotEmpty(),
                singleLine = multiline == false || multiline == null,
                label = {
                    if (label != null) {
                        Text(
                            text = label,
                            fontFamily = FontFamily(Font(R.font.quicksand)),
                            fontWeight = FontWeight.W700,
                            fontSize = 12.sp
                        )
                    }
                },
                shape = MaterialTheme.shapes.medium,
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    focusedLabelColor = Color.Black,
                ),
                textStyle = MaterialTheme.typography.labelSmall,
                trailingIcon = aboutIcon?.let { { aboutIcon() } },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .heightIn(max = 200.dp)
            ) {
                filteredOptions.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOption = option
                            expanded = false
                            onValueChange(option)
                        },
                        text = { Text(text = option, style = MaterialTheme.typography.labelMedium) },
                    )
                }
            }
        }
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.labelSmall,
                modifier = modifier
            )
        }
    }
}

@Composable
fun Footer(
    modifier: Modifier
) {
    BottomAppBar(
        containerColor = Color.White,
        content = {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Icone pessoas",
                    modifier = modifier
                        .size(36.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.footer_division),
                    contentDescription = "divisor do rodapé",
                    modifier = modifier
                        .size(36.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.iconepessoas2),
                    contentDescription = "Icone pessoas",
                    modifier = modifier
                        .size(30.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.footer_division),
                    contentDescription = "divisor do rodapé",
                    modifier = modifier
                        .size(36.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.tresbarras),
                    contentDescription = "Icone pessoas",
                    modifier = modifier
                        .size(30.dp)
                )
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    )
}
