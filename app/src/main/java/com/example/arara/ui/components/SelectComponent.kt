package com.example.arara.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SelectComponent(
	options: List<String>
) {
	var expanded by remember { mutableStateOf(false) }
	var selectedOption by remember { mutableStateOf("Tamanho") }
	
	Box(modifier = Modifier
		.fillMaxWidth()
		.border(2.dp, Color.LightGray, RoundedCornerShape(10.dp))
		.padding(start = 16.dp, top = 16.dp, end = 25.dp, bottom = 16.dp)
		.clickable { expanded = !expanded }
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceBetween,
			modifier = Modifier.fillMaxWidth()
		) {
			Text(
				text = selectedOption,
				style = MaterialTheme.typography.labelMedium
			)
			Icon(
				imageVector = if (expanded) Icons.Outlined.KeyboardArrowUp else Icons.Outlined.KeyboardArrowDown,
				contentDescription = if (expanded) "Menu aberto" else "Menu fechado"
			)
		}
		DropdownMenu(
			expanded = expanded,
			onDismissRequest = { expanded = false },
			modifier = Modifier
				.background(Color.White)
				.height(200.dp),
		) {
			options.forEach { option ->
				DropdownMenuItem(
					onClick = {
						selectedOption = option
						expanded = false
					},
					text = { Text(text = option, style = MaterialTheme.typography.labelMedium) })
			}
		}
	}
}