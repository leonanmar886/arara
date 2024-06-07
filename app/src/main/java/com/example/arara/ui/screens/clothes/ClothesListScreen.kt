package com.example.arara.ui.screens.clothes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arara.R
import com.example.arara.data.clothesList
import com.example.arara.models.Clothes
import com.example.arara.ui.AppViewModelProvider
import com.example.arara.ui.components.InputField
import com.example.arara.ui.navigation.NavigationDestination

object ClothesDestination: NavigationDestination {
    override val route = "clothes"
    override val titleRes = R.string.clothes_title
}

@Composable
fun ClothesListScreen(
    navigateToHome: () -> Unit,
    navigateToDetails: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ClothesViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    //val clothesUiState = viewModel.clothesUiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Logo(modifier = modifier)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
            ) {
                //InputForm(modifier = Modifier)
                var text by remember { mutableStateOf("") }
                InputField(
                    value = text,
                    onValueChange = { text = it },
                    label = "Buscar",
                    errorMessage = "",
                    modifier = Modifier
                        .background(color = Color(0xFFD9D9D9), shape = RoundedCornerShape(10.dp))
                        .width(189.dp)
                        .height(40.dp)

                )
                Image(
                    painter = painterResource(id = R.drawable.lupa),
                    contentDescription = "Lupa de busca",
                    modifier = Modifier
                        .size(50.dp)
                )
                Button(
                    onClick = navigateToHome,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00B8A9))
                ) {
                    Text(
                        text = "FILTRAR",
                        fontSize = 12.sp
                    )
                }
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

            ) {
                items(clothesList) { clothes ->
                    ImageCard(clothes = clothes, modifier = modifier)
                }
            }
            Footer(modifier = Modifier)
        }
    }
}


@Composable
fun Logo(
    modifier: Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 10.dp,top = 16.dp, bottom = 20.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
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
    modifier: Modifier
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Card(
            modifier = Modifier
                .shadow(15.dp, shape = RoundedCornerShape(12.dp))
                .size(width = 150.dp, height = 150.dp)

        ) {
            Image(
                painter = painterResource(id = clothes.imageResId),
                contentDescription = clothes.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .background(color = Color.White)
                    .fillMaxSize()
            )
        }
        Text(
            text = "${clothes.name}",
            fontFamily = FontFamily(Font(R.font.quicksand)),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 8.dp)
            )
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
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Icone pessoas",
                    modifier = Modifier
                        .size(36.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.footer_division),
                    contentDescription = "divisor do rodapé",
                    modifier = Modifier
                        .size(36.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.iconepessoas2),
                    contentDescription = "Icone pessoas",
                    modifier = Modifier
                        .size(30.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.footer_division),
                    contentDescription = "divisor do rodapé",
                    modifier = Modifier
                        .size(36.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.tresbarras),
                    contentDescription = "Icone pessoas",
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    )
}
