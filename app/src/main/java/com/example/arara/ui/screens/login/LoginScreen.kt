package com.example.arara.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arara.R
import com.example.arara.ui.AppViewModelProvider
import com.example.arara.ui.components.InputField
import com.example.arara.ui.navigation.NavigationDestination
import com.example.compose.md_theme_light_background
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

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
  val loginUiState = viewModel.loginUiState
  
  viewModel.checkUserLoggedIn()
  
  if(loginUiState.loginDetails.isLoggedId) {
    navigateToHome()
  }
  
  LoginContent(
    loginDetails = loginUiState.loginDetails,
    onLoginInfoChange = viewModel::updateUiState,
    onLoginClick = { viewModel.login() },
    modifier = modifier,
  )
  
}

@Composable
fun LoginContent(
  loginDetails: LoginDetails,
  onLoginInfoChange: (LoginDetails) -> Unit,
  onLoginClick: () -> Unit,
  modifier: Modifier,
) {
  
  Box(
    modifier = modifier
      .fillMaxSize()
      .background(color = Color(0xFFAEE0DD)),
    contentAlignment = Alignment.Center,
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Text(
        text = "Minha Arara",
        fontSize = 40.sp,
        //fontFamily =
      )
      Box(
        modifier = Modifier
          .background(color = Color(0xFFD9D9D9))
      ) {
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
        ) {

          InputForm(
            loginDetails = loginDetails,
            onLoginInfoChange = onLoginInfoChange,
            onLoginSubmit = onLoginClick,
          )
        }
      }
    }
  }
}

@Composable
fun InputForm(
  loginDetails: LoginDetails,
  onLoginInfoChange: (LoginDetails) -> Unit,
  onLoginSubmit: () -> Unit,
) {
  Column (verticalArrangement = Arrangement.spacedBy(8.dp)) {
    InputField(
      value = loginDetails.email,
      onValueChange = { onLoginInfoChange(loginDetails.copy(email = it)) },
      label = "Usuário/Email",
      errorMessage = if (loginDetails.errorMessages.email == -1) {""} else {stringResource(id = loginDetails.errorMessages.email)},
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp, 4.dp, 16.dp, 4.dp)
    )
    
    InputField(
      value = loginDetails.password,
      onValueChange = { onLoginInfoChange(loginDetails.copy(password = it)) },
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
      visualTransformation = PasswordVisualTransformation(),
      label = "Senha",
      errorMessage = if (loginDetails.errorMessages.password == -1) {""} else {stringResource(id = loginDetails.errorMessages.password)},
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp, 4.dp, 16.dp, 4.dp)
    )
    
    Button(
      onClick = onLoginSubmit,
      modifier = Modifier.padding(16.dp, 4.dp, 16.dp, 4.dp)
    ) {
      Text(text = "Entrar")
    }
  }
}
