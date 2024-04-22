package com.example.arara.ui.screens.user.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
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
      verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
      Text(text = "Seu Perfil")
      
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
      
      Text(text = "Seus Dados")
      
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
        label = "Data de Nasciemnto",
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
      
      Button(onClick = { /*TODO*/ }) {
        Text(text = "Continuar")
      }
    }
  }
}