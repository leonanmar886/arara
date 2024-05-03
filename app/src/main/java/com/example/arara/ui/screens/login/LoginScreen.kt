package com.example.arara.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arara.R
import com.example.arara.ui.AppViewModelProvider
import com.example.arara.ui.components.InputField
import com.example.arara.ui.navigation.NavigationDestination
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily


object LoginDestination: NavigationDestination {
  override val route = "login"
  override val titleRes = R.string.login_title
}

@Composable
fun LoginScreen(
  navigateToHome: () -> Unit,
  navigateToUserRegister: () -> Unit,
  navigateToLoginGoogle: () -> Unit,
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
    onSignUpRequest = navigateToUserRegister,
    onGoogleAccount = navigateToLoginGoogle,
    modifier = modifier,
  )
  
}

@Composable
fun LoginContent(
  loginDetails: LoginDetails,
  onLoginInfoChange: (LoginDetails) -> Unit,
  onLoginClick: () -> Unit,
  onSignUpRequest: () -> Unit,
  onGoogleAccount: () -> Unit,
  modifier: Modifier,
) {
  
  Box(
    modifier = modifier
      .fillMaxSize()
      .background(
        color = Color(0xFFAEE0DD)
      ),
        contentAlignment = Alignment.Center,
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier
        .padding(start = 50.dp, end = 50.dp)
    ) {
      Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Logo",
        modifier = Modifier
          .size(width = 100.dp, height = 160.dp)
          .fillMaxWidth()
      )
      Text(
        text = "MINHA ARARA",
        color = Color.Black,
        fontSize = 45.sp,
        fontFamily = FontFamily(Font(R.font.bubbler_one)),
        modifier = Modifier
          .padding(top = 15.dp, bottom = 40.dp)
      )
      Column (
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        )
      {
        Box(
          modifier = Modifier
            .shadow(20.dp, shape = RoundedCornerShape(20.dp))
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(20.dp))
            .padding(start = 0.dp, end = 0.dp, top = 40.dp, bottom = 5.dp)
        ) {
          Column(
            horizontalAlignment = Alignment.CenterHorizontally,
          ) {
            InputForm(
              loginDetails = loginDetails,
              onLoginInfoChange = onLoginInfoChange,
              onLoginSubmit = onLoginClick,
            )
            SingUpRequest(
              onSignUpRequest = onSignUpRequest
            )
          }
        }
        SignUpGoogle(
          onGoogleAccount = onGoogleAccount
        )
      }
    }
  }
}

@Composable
fun InputForm(
  loginDetails: LoginDetails,
  onLoginInfoChange: (LoginDetails) -> Unit,
  onLoginSubmit: () -> Unit
) {
  Column (
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(20.dp),
    modifier = Modifier
      .padding(start = 0.dp, end = 0.dp)
    ) {
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
      colors = ButtonColors(
        containerColor = Color(0xFF176B87),
        contentColor = Color(0xFFFFFFFF),
        disabledContainerColor = Color(0xFF176B87),
        disabledContentColor = Color(0xFFFFFFFF),
      ),
      modifier = Modifier.padding(0.dp)
    ) {
      Text(
        text = "Entrar",
        fontFamily = FontFamily(Font(R.font.quicksand)),

      )
    }
  }
}

@Composable
fun SingUpRequest(
  onSignUpRequest: () -> Unit
) {
  Box (
    contentAlignment = Alignment.Center,
    modifier = Modifier.padding(0.dp)
    ) {
    Button(
      onClick = onSignUpRequest,
      colors = ButtonColors(
        containerColor = Color.Transparent,
        contentColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        disabledContentColor = Color.Transparent,
      )
    ) {
      Text(
        text = "Não possuo cadastro",
        color = Color(0xFF176B87),
        fontFamily = FontFamily(Font(R.font.quicksand))
      )
    }
  }
}

@Composable
fun SignUpGoogle (
  onGoogleAccount: () -> Unit,
) {
  Button(
    onClick = onGoogleAccount,
    colors = ButtonColors(
      containerColor = Color.Transparent,
      contentColor = Color.Transparent,
      disabledContainerColor = Color.Transparent,
      disabledContentColor = Color.Transparent
    ),
    modifier = Modifier
      .fillMaxWidth()
      .shadow(20.dp, shape = RoundedCornerShape(20.dp))
      .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(20.dp))
  ) {

    Row (
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp)
    ) {
      Text(
        text = "Continuar com Google",
        color = Color.Black,
        fontFamily = FontFamily(Font(R.font.quicksand))
      )
      Image(
        painter = painterResource(id = R.drawable.g_google),
        contentDescription = "Google",
        modifier = Modifier
          .width(40.dp)
          .height(40.dp)
          .padding(start = 0.dp, end = 10.dp)
      )
    }
  }
}

