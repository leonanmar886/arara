package com.example.arara.ui.screens.user.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.arara.R
import com.example.arara.ui.AppViewModelProvider
import com.example.arara.ui.components.AboutIconButton
import com.example.arara.ui.components.CustomDialog
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

@Composable
fun RegisterScreen(
  navigateToHome: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: UserRegisterViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
  val registerUiState = viewModel.registerUiState
  
  fun navigateToHomeIfRegistered() {
    viewModel.register()
    if (viewModel.registerUiState.userRegisterDetails.errorMessages.general == -1) {
      navigateToHome()
    }
  }
  
  UserRegisterContent(
    registerDetails = registerUiState.userRegisterDetails,
    onRegisterInfoChange = viewModel::updateUiState,
    onRegisterClick = { navigateToHomeIfRegistered() },
    modifier = modifier
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserRegisterContent(
  registerDetails: UserRegisterDetails,
  onRegisterInfoChange: (UserRegisterDetails) -> Unit,
  onRegisterClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  val openAlertDialog = remember { mutableStateOf(false) }
  when {
    openAlertDialog.value -> {
      ConfirmDialog(
        onConfirm = { onRegisterClick() },
        onDismiss = { openAlertDialog.value = false }
      )
    }
  }
  
  val openProfileDialog = remember { mutableStateOf(false) }
  when {
    openProfileDialog.value -> {
      CustomDialog(
        title = "Nome do Perfil",
        content = {
          Column(
            modifier = Modifier
              .padding(0.dp, 5.dp, 0.dp, 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Text(
              text = stringResource(R.string.profile_dialog_description),
              fontFamily = FontFamily(Font(R.font.quicksand)),
              fontWeight = FontWeight.W600,
              fontSize = 12.sp,
            )
            /*Image(
              painter = painterResource(id = R.drawable.profile_dialog_image),
              contentDescription = "Perfil",
              modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
            )*/
          }
        },
        onDismiss = { openProfileDialog.value = false }
      )
    }
  }
  
  val openPasswordDialog = remember { mutableStateOf(false) }
  when {
    openPasswordDialog.value -> {
      CustomDialog(
        title = "Senha",
        content = {
          Column(
            modifier = Modifier
              .padding(0.dp, 5.dp, 0.dp, 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Text(
              text = stringResource(R.string.password_dialog_description),
              fontFamily = FontFamily(Font(R.font.quicksand)),
              fontWeight = FontWeight.W600,
              fontSize = 12.sp,
            )
            Column {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
              ) {
                BlueCircle()
                Text(
                  text = "Mínimo de 8 caracteres",
                  fontFamily = FontFamily(Font(R.font.quicksand)),
                  fontWeight = FontWeight.W600,
                  fontSize = 12.sp,
                )
              }
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
              ) {
                BlueCircle()
                Text(
                  text = "Pelo menos um número",
                  fontFamily = FontFamily(Font(R.font.quicksand)),
                  fontWeight = FontWeight.W600,
                  fontSize = 12.sp,
                )
              }
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
              ) {
                BlueCircle()
                Text(
                  text = "Pelo menos um caractere especial",
                  fontFamily = FontFamily(Font(R.font.quicksand)),
                  fontWeight = FontWeight.W600,
                  fontSize = 12.sp,
                )
              }
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
              ) {
                BlueCircle()
                Text(
                  text = "Pelo menos uma letra maiúscula",
                  fontFamily = FontFamily(Font(R.font.quicksand)),
                  fontWeight = FontWeight.W600,
                  fontSize = 12.sp,
                )
              }
            }
            
          }
        },
        onDismiss = { openPasswordDialog.value = false }
      )
    }
  }
  
  val openDataDialog = remember { mutableStateOf(false) }
  when {
    openDataDialog.value -> {
      CustomDialog(
        title = "Seus Dados",
        content = {
          Box(
            modifier = Modifier
              .padding(0.dp, 5.dp, 0.dp, 16.dp)
              .width(280.dp),
          ) {
            Text(
              text = stringResource(R.string.data_dialog_description),
              fontFamily = FontFamily(Font(R.font.quicksand)),
              fontWeight = FontWeight.W600,
              fontSize = 12.sp,
            )
          }
        },
        onDismiss = { openDataDialog.value = false }
      )
    }
  }
  
  val focusManager = LocalFocusManager.current
  var showDatePickerDialog by remember {
    mutableStateOf(false)
  }
  val datePickerState = rememberDatePickerState()
  var selectedDate by remember {
    mutableStateOf(registerDetails.birthDate)
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
                onRegisterInfoChange(registerDetails.copy(birthDate = selectedDate))
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
          fontSize = 24.sp
        )
        
        InputField(
          value = registerDetails.profileName,
          onValueChange = {onRegisterInfoChange(registerDetails.copy(profileName = it))},
          label = "Nome do Perfil",
          errorMessage = if (registerDetails.errorMessages.profileName == -1) {""} else {stringResource(id = registerDetails.errorMessages.profileName)},
          aboutIcon = { AboutIconButton(onClick = { openProfileDialog.value = true }) },
          modifier = Modifier.fillMaxWidth()
        )
        
        InputField(
          value = registerDetails.userName,
          onValueChange = {onRegisterInfoChange(registerDetails.copy(userName = it))},
          label = "Usuário",
          errorMessage = if (registerDetails.errorMessages.userName == -1) {""} else {stringResource(id = registerDetails.errorMessages.userName)},
          modifier = Modifier.fillMaxWidth()
        )
        
        InputField(
          value = registerDetails.password,
          onValueChange = {onRegisterInfoChange(registerDetails.copy(password = it))},
          label = "Senha",
          errorMessage = if (registerDetails.errorMessages.password == -1) {""} else {stringResource(id = registerDetails.errorMessages.password)},
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
          visualTransformation = PasswordVisualTransformation(),
          aboutIcon = { AboutIconButton(onClick = { openPasswordDialog.value = true }) },
          modifier = Modifier.fillMaxWidth()
        )
        
        InputField(
          value = registerDetails.confirmPassword,
          onValueChange = {onRegisterInfoChange(registerDetails.copy(confirmPassword = it))},
          label = "Confirmar a Senha",
          errorMessage = "",
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
          visualTransformation = PasswordVisualTransformation(),
          modifier = Modifier.fillMaxWidth()
        )
        
        Column(
          verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Text(
              text = "Seus Dados",
              fontFamily = FontFamily(Font(R.font.quicksand)),
              fontWeight = FontWeight.Bold,
              fontSize = 24.sp
            )
            AboutIconButton(onClick = { openDataDialog.value = true })
          }
          
          InputField(
            value = registerDetails.name,
            onValueChange = {onRegisterInfoChange(registerDetails.copy(name = it))},
            label = "Nome Completo",
            errorMessage = if (registerDetails.errorMessages.name == -1) {""} else {stringResource(id = registerDetails.errorMessages.name)},
            modifier = Modifier.fillMaxWidth()
          )
          
          InputField(
            value = selectedDate,
            onValueChange = {onRegisterInfoChange(registerDetails.copy(birthDate = it))},
            label = "Data de Nascimento",
            errorMessage = if (registerDetails.errorMessages.birthDate == -1) {""} else {stringResource(id = registerDetails.errorMessages.birthDate)},
            modifier = Modifier.fillMaxWidth(),
            onClick = {
              showDatePickerDialog = true
              focusManager.clearFocus(force = true)
            },
          )
          
          InputField(
            value = registerDetails.email,
            onValueChange = {onRegisterInfoChange(registerDetails.copy(email = it))},
            label = "Email",
            errorMessage = if (registerDetails.errorMessages.email == -1) {""} else {stringResource(id = registerDetails.errorMessages.email)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
          )
        }
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
  val acceptTerms = remember { mutableStateOf(false) }
  
  Dialog(onDismissRequest = { onDismiss() }) {
    Card(
      colors = CardDefaults.cardColors(
        containerColor = Color.White,
      ),
      modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(5.dp)
    ) {
      Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
          .padding(8.dp)
      ) {
        Text(text = "Termos e Condições",
          modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center),
          fontFamily = FontFamily(Font(R.font.quicksand)),
          fontWeight = FontWeight.W700,
          fontSize = 20.sp,
          textAlign = TextAlign.Center
        )
        
        Row {
          RadioButton(selected = acceptTerms.value, onClick = { acceptTerms.value = !acceptTerms.value })
          Text(text = "Declaro que li e estou de acordo com os termos e condições de uso do aplicativo.",
            fontFamily = FontFamily(Font(R.font.quicksand)),
            fontWeight = FontWeight.W600,
            fontSize = 12.sp)
        }
        
        Button(onClick = { onConfirm()}) {
          Text(text = "Continuar",
            fontFamily = FontFamily(Font(R.font.quicksand)),
            fontWeight = FontWeight.W600,
            fontSize = 12.sp)
        }
      }
    }
  }
}

@Composable
fun BlueCircle() {
  Box(
    modifier = Modifier
      .size(8.dp)
      .clip(CircleShape)
      .background(Color(0xFFAEE0DD))
  )
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
