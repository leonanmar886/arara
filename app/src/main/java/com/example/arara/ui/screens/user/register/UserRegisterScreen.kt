package com.example.arara.ui.screens.user.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.arara.R
import com.example.arara.ui.components.InputField
import com.example.arara.ui.navigation.NavigationDestination
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object UserRegisterDestination: NavigationDestination {
  override val route = "user_register"
  override val titleRes = R.string.user_register_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserRegisterContent() {
  
  val openAlertDialog = remember { mutableStateOf(false) }
  when {
    openAlertDialog.value -> {
      ConfirmDialog(
        onConfirm = { /*TODO*/ },
        onDismiss = { openAlertDialog.value = false }
      )
    }
  }
  
  val focusManager = LocalFocusManager.current
  var showDatePickerDialog by remember {
    mutableStateOf(false)
  }
  val datePickerState = rememberDatePickerState()
  var selectedDate by remember {
    mutableStateOf("")
  }
  if (showDatePickerDialog) {
    DatePickerDialog(
      onDismissRequest = { showDatePickerDialog = false },
      confirmButton = {
        Button(
          onClick = {
            datePickerState
              .selectedDateMillis?.let { millis ->
                selectedDate = millis.toBrazilianDateFormat()
              }
            showDatePickerDialog = false
          }) {
          Text(text = "Escolher data")
        }
      }) {
      DatePicker(state = datePickerState)
    }
  }
  
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
          value = selectedDate,
          onValueChange = {},
          label = "Data de Nascimento",
          errorMessage = "",
          modifier = Modifier.fillMaxWidth(),
          onClick = {
            showDatePickerDialog = true
            focusManager.clearFocus(force = true)
          },
        )
        
        InputField(
          value = "",
          onValueChange = {},
          label = "Email",
          errorMessage = "",
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
          modifier = Modifier.fillMaxWidth()
        )
      }
      
      Button(onClick = { openAlertDialog.value = true }) {
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

@Composable
fun ConfirmDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
  Dialog(onDismissRequest = { onDismiss() }) {
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .padding(16.dp)
    ) {
      Column {
        Text(text = "Deseja confirmar o cadastro?",
          modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center),
          fontFamily = FontFamily(Font(R.font.quicksand)),
          fontWeight = FontWeight.W600,
          fontSize = 12.sp,
          textAlign = TextAlign.Center
        )
        
        TextButton(onClick = { onConfirm()}) {
          Text(text = "Confirmar",
            fontFamily = FontFamily(Font(R.font.quicksand)),
            fontWeight = FontWeight.W600,
            fontSize = 12.sp)
        }
      }
    }
  }
}

fun Long.toBrazilianDateFormat(
  pattern: String = "dd/MM/yyyy"
): String {
  val date = Date(this)
  val formatter = SimpleDateFormat(
    pattern, Locale("pt-br")
  ).apply {
    timeZone = TimeZone.getTimeZone("GMT")
  }
  return formatter.format(date)
}
