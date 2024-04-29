package com.example.arara.ui.screens.user.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.arara.ui.navigation.NavigationDestination

import com.example.arara.R
import com.example.arara.ui.components.InputField

object UserRegisterDestination: NavigationDestination {
  override val route = "user_register"
  override val titleRes = R.string.user_register_title
}

@Composable
fun UserRegisterContent() {
  
  
  Box(
    modifier = Modifier.padding(16.dp)
  ){
    Column(
      verticalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier
        .fillMaxHeight(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
      ) {
        Text(
          text = "Seu Perfil",
          fontFamily = FontFamily(Font(R.font.quicksand)),
          fontWeight = FontWeight.Bold,
          fontSize = 18.sp
        )
        
        InputField(
          value = "",
          onValueChange = {},
          label = "Nome do Perfil",
          errorMessage = "",
          modifier = Modifier.fillMaxWidth()
        )
        
        InputField(
          value = "",
          onValueChange = {},
          label = "Usuário",
          errorMessage = "",
          modifier = Modifier.fillMaxWidth()
        )
        
        InputField(
          value = "",
          onValueChange = {},
          label = "Senha",
          errorMessage = "",
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
          visualTransformation = PasswordVisualTransformation(),
          modifier = Modifier.fillMaxWidth()
        )
        
        InputField(
          value = "",
          onValueChange = {},
          label = "Confirmar a Senha",
          errorMessage = "",
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
          visualTransformation = PasswordVisualTransformation(),
          modifier = Modifier.fillMaxWidth()
        )
      }
      
      Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
      ) {
        Text(
          text = "Seus Dados",
          fontFamily = FontFamily(Font(R.font.quicksand)),
          fontWeight = FontWeight.Bold,
          fontSize = 18.sp
        )
        
        InputField(
          value = "",
          onValueChange = {},
          label = "Nome Completo",
          errorMessage = "",
          modifier = Modifier.fillMaxWidth()
        )
        
        InputField(
          value = "",
          onValueChange = {},
          label = "Data de Nascimento",
          errorMessage = "",
          modifier = Modifier.fillMaxWidth()
        )
        
        InputField(
          value = "",
          onValueChange = {},
          label = "Email",
          errorMessage = "",
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
          modifier = Modifier.fillMaxWidth()
        )
        
        InputField(
          value = "",
          onValueChange = {},
          label = "CPF",
          errorMessage = "",
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
          modifier = Modifier.fillMaxWidth()
        )
      }
      
      Button(onClick = { /*TODO*/ }) {
        Text(
          text = "Continuar",
          fontFamily = FontFamily(Font(R.font.quicksand)),
          fontWeight = FontWeight.W600,
          fontSize = 12.sp
        )
      }
    }
  }
}