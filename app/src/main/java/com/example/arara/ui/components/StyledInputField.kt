package com.example.arara.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

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
  Column (verticalArrangement = Arrangement.spacedBy(8.dp)) {
    TextField(
      value = value,
      onValueChange = onValueChange,
      label = { Text(text = label) },
      visualTransformation =  visualTransformation ?: VisualTransformation.None,
      keyboardOptions = keyboardOptions,
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
