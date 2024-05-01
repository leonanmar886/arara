package com.example.arara.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.arara.R

@Composable
fun CustomDialog(
  title: String,
  content: @Composable() (() -> Unit),
  onDismiss: () -> Unit
) {
  return Dialog(
    onDismissRequest = { onDismiss() },
    properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true),
  ) {
    Card(
      colors = CardDefaults.cardColors(
        containerColor = Color.White,
      ),
      modifier = Modifier.shadow(5.dp)
    ){
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .wrapContentHeight()
          .padding(25.dp, 15.dp, 5.dp, 30.dp)
          .background(Color.White)
      ) {
       Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
       ) {
         Text(
           text = title,
           fontFamily = FontFamily(Font(R.font.quicksand)),
           fontWeight = FontWeight.W600,
           fontSize = 20.sp,
           modifier = Modifier.padding(0.dp, 12.dp, 0.dp, 0.dp)
         )
         IconButton(onClick = { onDismiss() }) {
           Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
         }
       }
        content()
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomDialog() {
  CustomDialog(
    title = "Dialog Title",
    onDismiss = {},
    content = {
      Text("This is a preview of the CustomDialog.")
    }
  )
}