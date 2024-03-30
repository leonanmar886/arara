package com.example.arara.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arara.R
import com.example.arara.ui.AppViewModelProvider
import com.example.arara.ui.navigation.NavigationDestination
import com.example.compose.md_theme_dark_background

object LoginDestination: NavigationDestination {
  override val route = "login"
  override val titleRes = R.string.login_title
}

@Composable
fun LoginScreen(
  navigateToHome: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: LoginViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
  val loginUiState by viewModel.uiState.collectAsState()
  
  if(loginUiState.isLoggedId) {
    navigateToHome()
  }
  
  LoginContent(
    loginState = loginUiState,
    onChangeEmail = { viewModel.onEmailChanged(it) },
    onChangePassword = { viewModel.onPasswordChanged(it) },
    onLoginClick = { viewModel.login() },
    modifier = modifier,
    errorMessage = loginUiState.errorMessage
  )
  
}

@Composable
fun LoginContent(
  loginState: LoginState,
  onChangeEmail: (String) -> Unit,
  onChangePassword: (String) -> Unit,
  onLoginClick: () -> Unit,
  modifier: Modifier,
  errorMessage: String
) {
  Box(
    modifier = modifier
      .fillMaxSize()
      .background(color = md_theme_dark_background),
    contentAlignment = Alignment.Center
  ) {
    Box(modifier = Modifier) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        InputForm(
          value = loginState.email,
          onValueChange = onChangeEmail,
          label = "Email",
          errorMessage = errorMessage,
          modifier = Modifier.padding(16.dp)
        )
        InputForm(
          value = loginState.password,
          onValueChange = onChangePassword,
          label = "Password",
          errorMessage = "",
          modifier = Modifier.padding(16.dp)
        )
        Button(
          onClick = onLoginClick,
          modifier = Modifier.padding(16.dp)
        ) {
          Text(text = "Login")
        }
      }
    }
  }
}

@Composable
fun InputForm(
  value: String,
  onValueChange: (String) -> Unit,
  label: String,
  errorMessage: String,
  modifier: Modifier
) {
  Column (verticalArrangement = Arrangement.spacedBy(8.dp)) {
    TextField(
      value = value,
      onValueChange = onValueChange,
      label = { Text(text = label) },
      isError = errorMessage.isNotEmpty(),
      singleLine = true,
      modifier = modifier
    )
    if(errorMessage.isNotEmpty()) {
      Text(
        text = errorMessage,
        color = Color.Red,
        style = MaterialTheme.typography.labelSmall
      )
    }
  }
}
