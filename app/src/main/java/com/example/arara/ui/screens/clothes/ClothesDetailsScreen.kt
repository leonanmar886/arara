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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arara.R
import com.example.arara.ui.AppViewModelProvider
import com.example.arara.ui.components.InputField
import com.example.arara.ui.navigation.NavigationDestination
import com.example.arara.ui.screens.login.LoginViewModel
import com.example.arara.ui.screens.clothes.Logo
import com.example.arara.ui.screens.clothes.Footer

/*
object ClothesDestination: NavigationDestination {
    override val route = "clothes"
    override val titleRes = R.string.clothes_title
}
*/

@Composable
fun ClothesDetailsScreen(
    viewModel: ClothesViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToHome: () -> Unit,
    modifier: Modifier
) {
    val clothesUiState = viewModel.clothesUiState

    val images = listOf(
        R.drawable.calcabege1,
        R.drawable.calcabege2,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Logo(modifier = modifier)


        Text(
            text = "Calça Jeans Bege",
            color = Color.Black,
            fontSize = 25.sp,
            fontFamily = FontFamily(Font(R.font.quicksand)),
            fontWeight = FontWeight.Black,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 10.dp, bottom = 10.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(16.dp, 2.dp)
            ) {
                items(images.size) { index ->
                    ImageCard2(modifier = modifier, imageRes = images[index])
                }
            }

            Text(
                text = "Características",
                color = Color.Black,
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.quicksand)),
                fontWeight = FontWeight.Black,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                color = Color.Black,
                fontSize = 11.sp,
                fontFamily = FontFamily(Font(R.font.quicksand)),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = navigateToHome) {
                Text(text = "Anunciar")
            }
        }


        Footer(modifier = Modifier)
    }
}

@Composable
fun ImageCard2(
    modifier: Modifier,
    imageRes: Int
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(15.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(16.dp))
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(16.dp))
        )
    }
}



//ver isso aqui que deve ta errado
/*
@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonText: String
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = onClick) {
            Text(text = buttonText)
        }
    }
}

*/