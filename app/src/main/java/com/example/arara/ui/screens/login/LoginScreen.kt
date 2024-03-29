package com.example.arara.ui.screens.login

import androidx.compose.runtime.Composable
import com.example.arara.R
import com.example.arara.ui.navigation.NavigationDestination

object LoginDestination: NavigationDestination {
    override val route = "login"
  override val titleRes = R.string.login_title
}

@Composable
fun LoginScreen() {

}