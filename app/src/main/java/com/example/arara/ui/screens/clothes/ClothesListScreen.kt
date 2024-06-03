package com.example.arara.ui.screens.clothes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arara.R
import com.example.arara.ui.AppViewModelProvider
import com.example.arara.ui.components.InputField
import com.example.arara.ui.navigation.NavigationDestination
import com.example.arara.ui.screens.login.LoginViewModel

object ClothesDestination: NavigationDestination {
    override val route = "clothes"
    override val titleRes = R.string.clothes_title
}

@Composable
fun ClothesListScreen(
    viewModel: ClothesViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToHome: () -> Unit,
    modifier: Modifier
) {
    val clothesUiState = viewModel.clothesUiState

    val images = listOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image5,
        R.drawable.image6,
        R.drawable.image1,
        R.drawable.image2
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Logo(modifier = modifier)
        Row {
            Button(onClick = navigateToHome) {
                Text(text = "Filtrar")
            }
        }

        Text(
            text = "Todas as roupas",
            color = Color.Black,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.bubbler_one)),
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 10.dp, bottom = 10.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .weight(1f)
                .padding(16.dp, 8.dp)

        ) {
            items(images.size) { index ->
                ImageCard(modifier = modifier, imageRes = images[index])
            }
        }
        Footer(modifier = Modifier)
    }
}

/*
@Composable
fun InputForm (
    clothesDetails: ClothesDetails,
    onClothesInfoChange: (ClothesDetails) -> Unit
) {
    InputField(
        value = clothesDetails.search,
        onValueChange = { onClothesInfoChange(clothesDetails.copy(search = it)) },
        label = "Buscar",
        errorMessage = "",
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 4.dp, 16.dp, 4.dp)
    )
}
*/

@Composable
fun ImageCard(
    modifier: Modifier,
    imageRes: Int
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 1.dp, shape = RoundedCornerShape(12.dp), clip = true)
            .aspectRatio(1f)
            .padding(15.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(12.dp))
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent)
                //.clip(shape = RoundedCornerShape(12.dp)) não tá fazendo nada
        )
        }
    }


@Composable
fun Logo(
    modifier: Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 10.dp,top = 16.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(40.dp)
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
fun Footer(
    modifier: Modifier
) {
    BottomAppBar(
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
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
            .background(color = Color(0xFFFFFFFF))
    )
}