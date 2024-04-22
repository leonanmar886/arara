package com.example.arara.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
  value: String,
  onValueChange: (String) -> Unit,
  label: String,
  visualTransformation: VisualTransformation? = null,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  errorMessage: String,
  modifier: Modifier
) {
  Column {
    TextField(
      value = value,
      onValueChange = onValueChange,
      label = { Text(text = label) },
      visualTransformation =  visualTransformation ?: VisualTransformation.None,
      keyboardOptions = keyboardOptions,
      isError = errorMessage.isNotEmpty(),
      singleLine = true,
      shape = MaterialTheme.shapes.small,
      colors = TextFieldDefaults.colors(
        cursorColor = Color.Black,
        disabledLabelColor = Color.LightGray,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent
      ),
      modifier = modifier
    )
    if(errorMessage.isNotEmpty()) {
      Text(
        text = errorMessage,
        color = Color.Red,
        style = MaterialTheme.typography.labelSmall,
        modifier = modifier
      )
    }
  }
}
