package com.example.arara.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.arara.ui.screens.login.LoginDestination
import com.example.arara.ui.screens.login.LoginScreen

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
        navigateToHome = { /*TODO*/ }
      )
    }
  }
}