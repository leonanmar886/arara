package com.example.arara.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.arara.ui.screens.clothes.ClothesDestination
import com.example.arara.ui.screens.clothes.ClothesDetailsDestination
import com.example.arara.ui.screens.clothes.ClothesDetailsScreen
import com.example.arara.ui.screens.clothes.ClothesListScreen
import com.example.arara.ui.screens.clothes.ClothesRegisterDestination
import com.example.arara.ui.screens.clothes.ClothesRegisterScreen
import com.example.arara.ui.screens.login.LoginDestination
import com.example.arara.ui.screens.login.LoginScreen
import com.example.arara.ui.screens.user.register.RegisterScreen
import com.example.arara.ui.screens.user.register.UserRegisterDestination

@Composable
fun AraraNavHost(
  navController: NavHostController,
  modifier: Modifier = Modifier
) {
  NavHost(
    navController = navController,
    startDestination = LoginDestination.route,
    modifier = modifier
  ) {
    composable(LoginDestination.route) {
      LoginScreen(
        navigateToHome = { navController.navigate(ClothesDestination.route) },
        navigateToUserRegister = { navController.navigate(UserRegisterDestination.route) },
        navigateToLoginGoogle = { /*TODO*/ }
      )
    }
    composable(UserRegisterDestination.route) {
      RegisterScreen(navigateToHome = { navController.navigate(LoginDestination.route) })
    }
    composable(ClothesDestination.route) {
      ClothesListScreen(
        navigateToDetails = { id -> navController.navigate("${ClothesDetailsDestination.route}/$id") },
        navigateToRegister = { navController.navigate(ClothesRegisterDestination.route) },
        navigateToLogin = { navController.navigate(LoginDestination.route) }
      )
    }
    
    composable(ClothesRegisterDestination.route){
      ClothesRegisterScreen(navigateToList = { navController.navigate(ClothesDestination.route) })
    }
    composable(
      route = "${ClothesDetailsDestination.route}/{${ClothesDetailsDestination.argClothesId}}",
      arguments = listOf(navArgument(ClothesDetailsDestination.argClothesId) { type = NavType.StringType })
    ) { backStackEntry ->
      val clothesId = backStackEntry.arguments?.getString(ClothesDetailsDestination.argClothesId)
      requireNotNull(clothesId) { "Clothes ID is required" }
      ClothesDetailsScreen(clothesId = clothesId, navigateToHome = { navController.navigate(LoginDestination.route) })
    }
  }
}
