package com.example.arara.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.HelpOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun AboutIconButton(onClick: () -> Unit) {
  return IconButton(onClick = { onClick() }) {
    Icon(
      imageVector = Icons.AutoMirrored.Sharp.HelpOutline,
      contentDescription = "About",
      tint = Color.Gray,
      modifier = Modifier.size(20.dp)
    )
  }
}