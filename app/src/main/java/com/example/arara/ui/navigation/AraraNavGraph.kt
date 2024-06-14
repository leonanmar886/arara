package com.example.arara.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.arara.ui.screens.clothes.ClothesDestination
import com.example.arara.ui.screens.clothes.ClothesListScreen
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
      ClothesListScreen(navigateToHome = { navController.navigate(LoginDestination.route) }, navigateToDetails = {  })
    }
//    composable(ClothesDetailsDestination.route) {
//      ClothesDetailsScreen(navigateToHome = { navController.navigate(LoginDestination.route) })
//    }
  }
}

