package com.example.arara

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.arara.ui.navigation.AraraNavHost

@Composable
fun AraraApp(
  navController: NavHostController = rememberNavController(),
) {
  AraraNavHost(navController = navController)
}